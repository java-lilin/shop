package com.manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.common.util.Json;
import com.manager.bean.entity.TabRole;
import com.manager.bean.vo.TabRoleVo;
import com.manager.mapper.TabRoleMapper;
import com.manager.service.TabRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @version 1.0
 * @projectName: shop
 * @author: lin
 * @description:TODO
 * @date: 2024/1/14  10:49
 */
@Service
public class TabRoleServiceImpl extends ServiceImpl<TabRoleMapper, TabRole> implements TabRoleService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Json addRoleInfo(TabRoleVo vo) {
        String operate = "addRoleInfo";

        TabRole role = new TabRole();
        BeanUtils.copyProperties(vo,role);
        role.setId(UUID.randomUUID().toString());
        this.saveOrUpdate(role);

        return Json.success(operate);
    }
}
