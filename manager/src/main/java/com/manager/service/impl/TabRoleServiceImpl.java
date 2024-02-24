package com.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.common.config.CustomIdGenerator;
import com.common.enumeration.CommonType;
import com.common.util.Json;
import com.manager.bean.entity.TabRole;
import com.manager.bean.entity.TabUser;
import com.manager.bean.vo.TabRoleVo;
import com.manager.enumeration.RoleType;
import com.manager.mapper.TabRoleMapper;
import com.manager.service.TabRoleService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @version 1.0
 * @projectName: shop
 * @author: lin
 * @date: 2024/1/14  10:49
 */
@Service
public class TabRoleServiceImpl extends ServiceImpl<TabRoleMapper, TabRole> implements TabRoleService {

    @Resource
    private CustomIdGenerator customIdGenerator;

    @Resource
    private TabRoleMapper roleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Json addRoleInfo(TabRoleVo vo) {
        String operate = "addRoleInfo";

        //校验角色信息
        validRoleInfo(vo);

        TabRole role = new TabRole();

        BeanUtils.copyProperties(vo,role);

        role.setId(customIdGenerator.nextId(TabUser.class));

        this.saveOrUpdate(role);

        return Json.success(operate);
    }


    public void validRoleInfo(TabRoleVo vo){

        List<TabRole> roleDBList = roleMapper.selectList(new LambdaQueryWrapper<TabRole>().eq(TabRole::getDeleteFlag, CommonType.UN_DELETE.getValue()));

        List<String> roleNameDBList = roleDBList.stream().map(TabRole::getName).collect(Collectors.toList());

        if(CollectionUtils.isNotEmpty(roleNameDBList) && roleNameDBList.contains(vo.getName())){
            throw new RuntimeException(RoleType.ERROR_ROLE_NAME_EXIST.getValue());
        }

    }


}
