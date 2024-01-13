package com.manager.bean.entity;

import com.common.bean.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @version 1.0
 * @projectName: shop
 * @author: lin
 * @description:TODO
 * @date: 2024/1/11  17:08
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TabOrgEntity extends BaseEntity {

    /**
     * 组织名称
     */
    private String orgName;
    /**
     * 父id
     */
    private String parentId;
    /**
     * 组织编码，四位一级
     */
    private Integer code;
    /**
     * 序号
     */
    private Integer sort;

}
