package com.common.enumeration;

import lombok.Getter;


/**
 * 客户端类型
 */
@Getter
public enum ClientType {
    CLIENT_TYPE_EXE("EXE", "EXE"),
    CLIENT_TYPE_APP("APP", "APP"),
    CLIENT_TYPE_APPLET("APPLET", "小程序");
    ClientType(String type, String name) {
        this.type = type;
        this.name = name;
    }

    /**
     * 类型
     */
    private String type;
    /**
     * 描述
     */
    private String name;
}
