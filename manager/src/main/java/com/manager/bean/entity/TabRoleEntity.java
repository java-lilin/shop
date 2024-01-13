package com.manager.bean.entity;

import com.common.bean.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @version 1.0
 * @projectName: shop
 * @author: lin
 * @description:TODO
 * @date: 2024/1/13  14:24
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TabRoleEntity extends BaseEntity {

    private String name;

}
