package com.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.common.util.Json;
import com.manager.bean.dto.TabUserDto;
import com.manager.bean.entity.TabUserEntity;

/**
 * @version 1.0
 * @projectName: shop
 * @author: lin
 * @description:TODO
 * @date: 2024/1/11  17:08
 */
public interface TabUserService extends IService<TabUserEntity> {

    Json register(TabUserDto tabUserDto);

}
