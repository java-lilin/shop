package com.common.config;

import org.springframework.stereotype.Component;
import com.common.util.SnowflakeIdWorkerUtil;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;

/**
 * 自定义ID生成器
 *
 * @projectName: shop
 * @author: lin
 * @description:TODO
 * @date: 2024/2/2  15:54
 */
@Component
public class CustomIdGenerator implements IdentifierGenerator {

    @Override
    public Long nextId(Object entity) {

        return SnowflakeIdWorkerUtil.generateId();
    }
}