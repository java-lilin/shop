package com.manager.controller;

import com.common.util.Json;
import com.manager.bean.vo.TabUserBatchVo;
import com.manager.bean.vo.TabUserVo;
import com.manager.service.TabUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @version 1.0
 * @projectName: shop
 * @author: lin
 * @description:TODO
 * @date: 2024/1/11  17:08
 */
@Api("注册用户信息")
@RestController
@RequestMapping("user")
public class TabUserController {

    @Resource
    private TabUserService userService;

    @ApiOperation("注册用户接口")
    @PostMapping("/register")
    public Json register(@RequestBody TabUserVo vo){
        return userService.register(vo);
    }

    @ApiOperation("审批用户接口")
    @PostMapping("approveUser")
    public Json approveUser(@RequestBody TabUserVo vo){
        return userService.approveUser(vo);
    }

    @ApiOperation("批量审批用户接口")
    @PostMapping("approveBatchUser")
    public Json approveBatchUser(@RequestBody TabUserBatchVo vo){
        return userService.approveBatchUser(vo);
    }

    @ApiOperation("查询审核列表")
    @GetMapping("approveUserList")
    public Json approveUserList(@RequestParam Integer approve){
        return userService.approveUserList(approve);
    }

    @ApiOperation("用户登录接口")
    @PostMapping("approveUserList")
    public Json login(@RequestBody TabUserVo vo){
        return userService.login(vo);
    }



}
