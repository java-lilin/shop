package com.manager.controller;

import com.common.util.Json;
import com.manager.bean.vo.TabRoleVo;
import com.manager.service.TabRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @version 1.0
 * @projectName: shop
 * @author: lin
 * @date: 2024/1/14  10:44
 */
@Api("角色信息")
@RestController
@RequestMapping("role")
public class TabRoleController {

    @Resource
    private TabRoleService roleService;

    @ApiOperation("添加角色接口")
    @PostMapping("addRoleInfo")
    public Json addRoleInfo(@RequestBody TabRoleVo vo){
        return roleService.addRoleInfo(vo);
    }

}
