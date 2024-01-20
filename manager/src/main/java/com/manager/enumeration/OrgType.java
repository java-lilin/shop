package com.manager.enumeration;

import lombok.Getter;

/**
 * @version 1.0
 * @projectName: shop
 * @author: lin
 * @description:TODO
 * @date: 2024/1/14  16:20
 */
@Getter
public enum OrgType {

    ERROR_ORG_NAME_EXIST(400001, "组织名称已存在"),

    ERROR_PARENT_ORG_NOT_EXIST(400002, "父级组织不存在"),

    SUCCESS_OPERATE(200, "操作成功");
    OrgType(int code, String value) {
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
