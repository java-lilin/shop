package com.manager.bean.vo;

import lombok.Data;

import java.util.Date;

/**
 * @version 1.0
 * @projectName: shop
 * @author: lin
 * @description:TODO
 * @date: 2024/1/11  17:20
 */
@Data
public class TabUserVo {
    /**
     * 姓名
     */
    private String name;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 手机号
     */
    private Integer telephone;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 地址
     */
    private String address;
    /**
     * 身份证号
     */
    private String idCode;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 出生日期
     */
    private Date birthDate;
    /**
     * 头像
     */
    private String headPortrait;
    /**
     * 启用状态 0：启用，1停用
     */
    private Integer startStatus;
}
