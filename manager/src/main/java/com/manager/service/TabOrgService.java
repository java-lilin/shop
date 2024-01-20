package com.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.common.util.Json;
import com.manager.bean.entity.TabOrg;
import com.manager.bean.vo.TabOrgVo;

/**
 * @version 1.0
 * @projectName: shop
 * @author: lin
 * @description:TODO
 * @date: 2024/1/14  15:06
 */
public interface TabOrgService extends IService<TabOrg> {

    Json addOrgInfo(TabOrgVo vo);

}
