package com.common.enumeration;

import lombok.Getter;

/**
 * @version 1.0
 * @projectName: shop
 * @author: lin
 * @date: 2024/1/19  21:53
 */
@Getter
public enum TokenType {

    ERROR_TOKEN_CONCURRENT(402001, "token错误，判为并发登录，挤下线"),
    ERROR_TOKEN_IS_NULL(402002, "requestToken为空"),
    ERROR_TOKEN_CLAIMS_IS_NULL(402003, "解析token中的用户信息claims为null"),
    ERROR_TOKEN_EXPIRE(402004, "token不存在于redis中，已过期");
    TokenType(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 类型
     */
    private final int code;
    /**
     * 描述
     */
    private final String msg;

}
