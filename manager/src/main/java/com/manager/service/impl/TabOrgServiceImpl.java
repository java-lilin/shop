package com.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.common.enumeration.CommonType;
import com.common.util.Json;
import com.manager.bean.entity.TabOrg;
import com.manager.bean.vo.TabOrgVo;
import com.manager.enumeration.OrgType;
import com.manager.mapper.TabOrgMapper;
import com.manager.service.TabOrgService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @version 1.0
 * @projectName: shop
 * @author: lin
 * @date: 2024/1/14  15:07
 */
@Service
public class TabOrgServiceImpl extends ServiceImpl<TabOrgMapper, TabOrg> implements TabOrgService {

    @Resource
    private TabOrgMapper tabOrgMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Json addOrgInfo(TabOrgVo vo) {
        String operate = "addOrgInfo";
        //判断组织是否重名
        TabOrg orgDBForName = tabOrgMapper.selectOne(new LambdaQueryWrapper<TabOrg>().eq(TabOrg::getOrgName,vo.getOrgName())
                .eq(TabOrg::getDeleteFlag, CommonType.UN_DELETE.getValue()).last("LIMIT 1"));
        if(ObjectUtils.isNotEmpty(orgDBForName)){
            return Json.fail(operate,OrgType.ERROR_ORG_NAME_EXIST.getValue());
        }

        TabOrg org = new TabOrg();
        BeanUtils.copyProperties(vo,org);

        //根据父id判断，添加的是否是父级目录来设置code值
        if(StringUtils.isNotEmpty(vo.getParentId())){
            //=============================================添加组织子级=============================================
            //判断父级组织是否存在
            TabOrg orgDB = getOrgById(vo.getParentId());
            if(ObjectUtils.isEmpty(orgDB)){
                return Json.fail(operate, OrgType.ERROR_PARENT_ORG_NOT_EXIST.getValue());
            }
            //根据父级id查询子级列表
            List<TabOrg> orgDBList = this.getChildOrgListByParentId(vo.getParentId());
            //如果子集合为空
            if(CollectionUtils.isEmpty(orgDBList)){
                org.setCode(orgDB.getCode()+"0001");
                org.setSort(1);
            }else {
                //设置code
                String codeDBMax = orgDBList.stream().max(Comparator.comparing(TabOrg::getCode)).get().getCode();
                org.setCode(String.valueOf(Integer.parseInt(codeDBMax)+1));
                //设置sort
                Integer maxDBSort = orgDBList.stream().max(Comparator.comparing(TabOrg::getSort)).get().getSort();
                org.setSort(maxDBSort+1);
            }
        }else {
            //=============================================添加组织父级=============================================
            //查询所有组织列表 根据parentId分组
            Map<String,List<TabOrg>> orgDBMap = this.getOrgDBMap();
            if(MapUtils.isEmpty(orgDBMap) || CollectionUtils.isEmpty(orgDBMap.get(null))){
                org.setCode("0001");
                org.setSort(1);
            }
            //设置code
            String codeDBMax = orgDBMap.get(null).stream().max(Comparator.comparing(TabOrg::getCode)).get().getCode();
            org.setCode(String.valueOf(Integer.parseInt(codeDBMax)+1));
            //设置sort
            Integer maxDBSort = orgDBMap.get(null).stream().max(Comparator.comparing(TabOrg::getSort)).get().getSort();
            org.setSort(maxDBSort+1);
        }
        this.saveOrUpdate(org);

        return Json.success(operate,OrgType.SUCCESS_OPERATE.getValue());
    }


    public Map<String,List<TabOrg>> getOrgDBMap(){
        //查询所有组织列表
        List<TabOrg> orgDBList = tabOrgMapper.selectList(new LambdaQueryWrapper<TabOrg>().eq(TabOrg::getDeleteFlag, CommonType.UN_DELETE.getValue()));
        //根据parentId 分组
        return orgDBList.stream().collect(Collectors.groupingBy(TabOrg::getParentId));
    }

    public List<TabOrg> getChildOrgListByParentId(String parentId){
        return tabOrgMapper.selectList(new LambdaQueryWrapper<TabOrg>().eq(TabOrg::getDeleteFlag, CommonType.UN_DELETE.getValue())
                .eq(TabOrg::getParentId,parentId));
    }

    public TabOrg getOrgById(String Id){
        return tabOrgMapper.selectOne(new LambdaQueryWrapper<TabOrg>().eq(TabOrg::getDeleteFlag, CommonType.UN_DELETE.getValue())
                .eq(TabOrg::getId,Id));
    }

}
