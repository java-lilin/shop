package com.manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.common.util.Json;
import com.manager.bean.dto.TabUserDto;
import com.manager.bean.entity.TabUserEntity;
import com.manager.mapper.TabUserMapper;
import com.manager.service.TabUserService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version 1.0
 * @projectName: shop
 * @author: lin
 * @description:TODO
 * @date: 2024/1/11  17:08
 */
@Service
public class TabUserServiceImpl extends ServiceImpl<TabUserMapper, TabUserEntity> implements TabUserService {
    @Override
    public Json register(TabUserDto tabUserDto) {
        Map<String,String> map = new ConcurrentHashMap<>();
        return null;
    }
}
