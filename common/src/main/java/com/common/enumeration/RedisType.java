package com.common.enumeration;

import lombok.Getter;

/**
 * @version 1.0
 * @projectName: shop
 * @author: lin
 * @date: 2024/1/14  21:17
 */
@Getter
public enum RedisType {

    TOKEN("token"),
    ADD_USER("add_user");
    RedisType(String name) {
        this.name = name;
    }

    /**
     * 类型
     */
    private final String name;


}
