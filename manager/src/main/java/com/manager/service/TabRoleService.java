package com.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.common.util.Json;
import com.manager.bean.entity.TabRole;
import com.manager.bean.vo.TabRoleVo;

/**
 * @version 1.0
 * @projectName: shop
 * @author: lin
 * @description:TODO
 * @date: 2024/1/14  10:48
 */
public interface TabRoleService extends IService<TabRole> {

    Json addRoleInfo(TabRoleVo vo);
}
