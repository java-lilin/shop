package com.manager.enumeration;

import lombok.Getter;

/**
 * @version 1.0
 * @projectName: shop
 * @author: lin
 * @date: 2024/1/14  16:58
 */
@Getter
public enum UserType {
    //=============================基础配置=====================
    APPROVE_WAIT(0,"0"),

    APPROVE_ALREADY(0,"1"),

    APPROVE_REFUSE(0,"2"),

    //=============================异常处理======================
    ERROR_USER_NAME_EXIST(401001, "用户名已存在"),

    ERROR_TELEPHONE_EXIST(401002, "手机号已存在"),

    ERROR_ID_CODE_EXIST(401003, "身份证已存在"),

    ERROR_EMAIL_EXIST(401004, "email已存在"),

    ERROR_TELEPHONE_INCORRECT_FORMAT(401005, "手机号格式不正确"),

    ERROR_ID_CODE_INCORRECT_FORMAT(401006, "身份证格式不正确"),

    ERROR_EMAIL_INCORRECT_FORMAT(401007, "email格式不正确"),

    ERROR_USER_NAME_NOT_EXIST(401008,"用户名不存在"),

    ERROR_PASSWORD_NOT_MATE(401009,"用户名密码不匹配"),

    ERROR_APPROVE_NOT_NULL(401010,"审核状态不能为空"),

    SUCCESS_OPERATE(200, "操作成功");
    UserType(int code, String value) {
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
