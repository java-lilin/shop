package com.manager.bean.vo;

import lombok.Data;

import java.util.List;

/**
 * @version 1.0
 * @projectName: shop
 * @author: lin
 * @description:TODO
 * @date: 2024/1/16  22:35
 */
@Data
public class TabUserBatchVo {

    private List<String> userIds;

    private String approve;

}
