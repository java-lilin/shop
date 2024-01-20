package com.manager.bean.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.common.bean.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
/**
 * @version 1.0
 * @projectName: shop
 * @author: lin
 * @description:TODO
 * @date: 2024/1/11  17:08
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TabUser extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
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
    /**
     * 审核状态 0:待审核，1:审核通过，2:拒绝审核
     */
    @TableField(exist = false)
    private Integer approve;
    /**
     * 审核不通过原因
     */
    @TableField(exist = false)
    private String reason;

}
