package com.manager.controller;

import com.common.util.Json;
import com.manager.bean.vo.TabOrgVo;
import com.manager.service.TabOrgService;
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
 * @description:TODO
 * @date: 2024/1/11  17:08
 */
@Api("组织信息")
@RestController
@RequestMapping("org")
public class TabOrgController {

    @Resource
    private TabOrgService orgService;

    @ApiOperation("添加组织接口")
    @PostMapping("addOrgInfo")
    public Json addOrgInfo(@RequestBody TabOrgVo vo){
        return orgService.addOrgInfo(vo);
    }

}
