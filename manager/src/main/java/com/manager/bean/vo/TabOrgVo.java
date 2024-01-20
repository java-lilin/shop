package com.manager.bean.vo;

import lombok.Data;

/**
 * @version 1.0
 * @projectName: shop
 * @author: lin
 * @description:TODO
 * @date: 2024/1/11  22:05
 */
@Data
public class TabOrgVo {
    /**
     * 组织名称
     */
    private String orgName;
    /**
     * 父id
     */
    private String parentId;
}
