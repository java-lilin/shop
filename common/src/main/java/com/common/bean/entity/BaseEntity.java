package com.common.bean.entity;

import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {
    /**
     * 主键
     */
    private String Id;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 修改人
     */
    private String updateUser;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 逻辑删除 0：未删除，1：已删除
     */
    private Integer deleteFlag;

}
