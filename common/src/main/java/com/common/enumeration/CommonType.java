package com.common.enumeration;

import lombok.Getter;

/**
 * @version 1.0
 * @projectName: shop
 * @author: lin
 * @date: 2024/1/14  15:19
 */

@Getter
public enum CommonType {

    UN_DELETE("DELETE", 0),
    DELETE("APP", 1);
    CommonType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    /**
     * 类型
     */
    private final String name;
    /**
     * 描述
     */
    private final int value;

}
