package com.common.util;

import com.common.bean.entity.UserToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @version 1.0
 * @projectName: shop
 * @author: lin
 * @description:TODO
 * @date: 2024/1/12  10:09
 */
public class TokenUtil {

    private static final Logger logger = LoggerFactory.getLogger(TokenUtil.class);

    public static String getUserId(HttpServletRequest req){
        String token = req.getHeader("token");
        logger.info("TokenUtil token ========= " + token);
        UserToken userToken = JWT.unSign(token,UserToken.class);
        logger.info("TokenUtil userToken ========= " + userToken);
        assert userToken != null;
        return userToken.getUserId();
    }

    /**
     * 登录后确认的角色id
     * 如果不传，就使用解析token的第一个
     */
    public static String getRoleId(HttpServletRequest req){
        String roleId = req.getHeader("roleId");
        logger.info("TokenUtil getRoleId ========= " + roleId);
        return roleId;
    }
}