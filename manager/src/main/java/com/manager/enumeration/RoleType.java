package com.manager.enumeration;

import lombok.Getter;

/**
 * @version 1.0
 * @projectName: shop
 * @author: lin
 * @date: 2024/1/14  16:20
 */
@Getter
public enum RoleType {

    ERROR_ROLE_NAME_EXIST(402001, "角色名称已存在"),

    SUCCESS_OPERATE(200, "操作成功");
    RoleType(int code, String value) {
        this.code = code;
        this.value = value;
    }

    /**
     * 类型
     */
    private final int code;
    /**
     * 描述
     */
    private final String value;

}
