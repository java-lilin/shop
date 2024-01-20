package com.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.common.util.Json;
import com.manager.bean.entity.TabUser;
import com.manager.bean.vo.TabUserBatchVo;
import com.manager.bean.vo.TabUserVo;

/**
 * @version 1.0
 * @projectName: shop
 * @author: lin
 * @description:TODO
 * @date: 2024/1/11  17:08
 */
public interface TabUserService extends IService<TabUser> {

    Json register(TabUserVo vo);

    Json approveUser(TabUserVo vo);

    Json approveBatchUser(TabUserBatchVo vo);

    Json approveUserList(Integer approve);

    Json login(TabUserVo vo);

}
