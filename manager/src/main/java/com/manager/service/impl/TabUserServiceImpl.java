package com.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.common.config.CustomIdGenerator;
import com.common.enumeration.CommonType;
import com.common.enumeration.RedisType;
import com.common.util.IDCardUtil;
import com.common.util.Json;
import com.common.util.JwtUtil;
import com.common.util.RedisCache;
import com.common.valid.FormatValidator;
import com.manager.bean.entity.TabUser;
import com.manager.bean.vo.TabUserBatchVo;
import com.manager.bean.vo.TabUserVo;
import com.manager.enumeration.UserType;
import com.manager.mapper.TabUserMapper;
import com.manager.service.TabUserService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @version 1.0
 * @projectName: shop
 * @author: lin
 * @description:TODO
 * @date: 2024/1/11  17:08
 */
@Service
public class TabUserServiceImpl extends ServiceImpl<TabUserMapper, TabUser> implements TabUserService {

    @Resource
    private CustomIdGenerator customIdGenerator;
    @Resource
    private TabUserMapper userMapper;
    @Resource
    private FormatValidator formatValidator;
    @Resource
    private RedisCache redisCache;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Json register(TabUserVo vo) {
        String operate = "register";
        //校验用户信息
        validUserInfo(vo);

        TabUser user = new TabUser();
        BeanUtils.copyProperties(vo,user);

        //根据身份证  计算性别、年龄、出生日期
        user.setAge(IDCardUtil.getAge(user.getIdCode()));
        user.setSex(IDCardUtil.getSex(user.getIdCode()));
        try {
            user.setBirthDate(IDCardUtil.getBirthday(user.getIdCode()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        //将待审核的数据存到redis
        user.setApprove(0);
        user.setId(customIdGenerator.nextId(TabUser.class));
        //取出审核用户列表
        List<TabUser> userList = new ArrayList<>();
        userList.add(user);

        //添加新用户
        redisCache.setCacheList(RedisType.ADD_USER.getName(),userList);

        return Json.success(operate,UserType.SUCCESS_OPERATE.getValue());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Json approveUser(TabUserVo vo) {
        String operate = "approveUser";

        if(vo.getApprove() == null){
            return Json.fail(operate,UserType.ERROR_APPROVE_NOT_NULL.getValue());
        }

        //更新redis审核状态
        List<TabUser> userList = redisCache.getCacheList(RedisType.ADD_USER.getName());

        userList.forEach(e->{
            e.setApprove(vo.getApprove());
            e.setReason(vo.getReason());
        });

        TabUser user =  userList.stream().filter(obj-> Objects.equals(obj.getId(), vo.getId())).findFirst().orElse(null);
        redisCache.deleteObject(RedisType.ADD_USER.getName());
        assert user != null;
        redisCache.setCacheList(RedisType.ADD_USER.getName(),userList);
        //将审核通过的用户添加到mysql
        if(vo.getApprove() == 1){
            //对密码进行加密
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            this.saveOrUpdate(user);
        }
        return Json.success(operate,UserType.SUCCESS_OPERATE.getValue());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Json approveBatchUser(TabUserBatchVo vo) {
        String operate = "approveBatchUser";

        try (Jedis jedis = new Jedis(host, Integer.parseInt(port))) {
            Pipeline pipeline = jedis.pipelined();
            // 批量更新用户的审核状态
            vo.getUserIds().forEach(e->{
                pipeline.hset(RedisType.ADD_USER.getName() + e,"approve", vo.getApprove());
            });
            // 提交管道中的所有命令
            pipeline.sync();
        }

        if(!UserType.APPROVE_ALREADY.getValue().equals(vo.getApprove())){
            return Json.success(operate,UserType.SUCCESS_OPERATE.getValue());
        }

        List<TabUser> userList = new ArrayList<>();

        vo.getUserIds().forEach(e->{
            TabUser user = redisCache.getCacheObject(RedisType.ADD_USER.getName() + e);
            userList.add(user);
        });

        //将审核通过的用户添加到mysql
        this.saveBatch(userList);

        return Json.success(operate,UserType.SUCCESS_OPERATE.getValue());
    }

    @Override
    public Json approveUserList(Integer approve) {
        String operate = "approveUserList";

        List<TabUser> userList;
        try (Jedis jedis = new Jedis(host, Integer.parseInt(port))) {
            jedis.auth("123456");
            userList = redisCache.getCacheList(RedisType.ADD_USER.getName());
        }
        if (!CollectionUtils.isEmpty(userList)) {
            userList = userList.stream().filter(x-> Objects.equals(x.getApprove(), approve)).collect(Collectors.toList());
        }

        return Json.success(operate,userList);
    }

    @Override
    public Json login(TabUserVo vo) {
        String operate= "login";

        LambdaQueryWrapper<TabUser> lambda = new LambdaQueryWrapper<>();
        lambda.eq(TabUser::getDeleteFlag,CommonType.UN_DELETE.getValue());
        lambda.eq(TabUser::getUserName,vo.getUserName());
        lambda.last("LIMIT 1");
        TabUser user = userMapper.selectOne(lambda);

        if(ObjectUtils.isEmpty(user)){
            return Json.fail(operate,UserType.ERROR_USER_NAME_NOT_EXIST.getValue());
        }
        //判断密码是否正确
        boolean mate = passwordEncoder.matches(vo.getPassword(),user.getPassword());

        if (!mate){
            return Json.fail(operate,UserType.ERROR_PASSWORD_NOT_MATE.getValue());
        }

        //根据用户id和用户名 生成token
        String token = JwtUtil.getToken(user.getId(),user.getUserName());

        //将用户id存到redis
        redisCache.setCacheObject(RedisType.TOKEN.getName() + user.getId(),token,1, TimeUnit.DAYS);

        String refresh_token = JwtUtil.getRefreshToken(user.getId(),user.getUserName());

        HashMap<String,String> map = new HashMap<>();
        map.put("token",token);
        map.put("refresh_token",refresh_token);
        return Json.success(operate,map);
    }

    public void validUserInfo(TabUserVo vo){

        //校验手机号、身份证、email 格式是否正确
        if(!formatValidator.isPhoneNumberValid(String.valueOf(vo.getTelephone()))){
            throw new RuntimeException(UserType.ERROR_TELEPHONE_INCORRECT_FORMAT.getValue());
        }
        if(!formatValidator.isEmailAddressValid(vo.getEmail())){
            throw new RuntimeException(UserType.ERROR_EMAIL_INCORRECT_FORMAT.getValue());
        }
        if(!formatValidator.isIdCardNumberValid(vo.getIdCode())){
            throw new RuntimeException(UserType.ERROR_ID_CODE_INCORRECT_FORMAT.getValue());
        }

        //查询所有用户列表
        List<TabUser> userDBList = userMapper.selectList(new LambdaQueryWrapper<TabUser>().eq(TabUser::getDeleteFlag, CommonType.UN_DELETE.getValue()));
        //用户名
        List<String> userNameDBList = userDBList.stream().map(TabUser::getUserName).collect(Collectors.toList());
        //手机号
        List<String> telephoneDBList = userDBList.stream().map(TabUser::getTelephone).collect(Collectors.toList());
        //身份证
        List<String> idCodeDBList = userDBList.stream().map(TabUser::getIdCode).collect(Collectors.toList());
        //email
        List<String> emailDBList = userDBList.stream().map(TabUser::getEmail).collect(Collectors.toList());

        if(CollectionUtils.isNotEmpty(userNameDBList) && userNameDBList.contains(vo.getUserName())){
            throw new RuntimeException(UserType.ERROR_USER_NAME_EXIST.getValue());
        }
        if(CollectionUtils.isNotEmpty(telephoneDBList) && telephoneDBList.contains(vo.getTelephone())){
            throw new RuntimeException(UserType.ERROR_TELEPHONE_EXIST.getValue());
        }
        if(CollectionUtils.isNotEmpty(idCodeDBList) && idCodeDBList.contains(vo.getIdCode())){
            throw new RuntimeException(UserType.ERROR_ID_CODE_EXIST.getValue());
        }
        if(CollectionUtils.isNotEmpty(emailDBList) && emailDBList.contains(vo.getEmail())){
            throw new RuntimeException(UserType.ERROR_EMAIL_EXIST.getValue());
        }
    }

}
