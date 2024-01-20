package com.manager.controller;

import com.common.util.Json;
import com.manager.bean.vo.TabRoleVo;
import com.manager.service.TabRoleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @version 1.0
 * @projectName: shop
 * @author: lin
 * @description:TODO
 * @date: 2024/1/14  10:44
 */
@RestController
@RequestMapping("role")
public class TabRoleController {

    @Resource
    private TabRoleService roleService;

    @PostMapping("addRoleInfo")
    public Json addRoleInfo(@RequestBody TabRoleVo vo, HttpServletRequest request){
        return roleService.addRoleInfo(vo);
    }

}
