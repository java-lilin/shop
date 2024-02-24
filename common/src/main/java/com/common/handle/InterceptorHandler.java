package com.common.handle;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.internal.org.apache.commons.lang3.StringUtils;
import com.common.enumeration.RedisType;
import com.common.enumeration.TokenType;
import com.common.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @version 1.0
 * @projectName: shop
 * @author: lin
 * @date: 2024/1/19  21:49
 */
@Component
public class InterceptorHandler implements HandlerInterceptor {
    // 注入redis
    @Autowired
    StringRedisTemplate redisTemplate;
    // 白名单列表
    @Value("${auth.whiteList}")
    private List<String> whiteList;

    // 处理请求之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setContentType("text/html; charset=UTF-8");
        //取出请求头中Authorization的信息，就是token内容，接下来就是各种判断
        JSONObject jsonObject = new JSONObject();

        String path = request.getRequestURI().substring(request.getContextPath().length()); // 获取请求路径
        if (whiteList.contains(path)) { // 如果请求路径在不拦截的路径列表中，则放行请求
            return true;
        }

        String token = request.getHeader("token");
        String refreshToken = request.getHeader("refresh_token");
        if (StringUtils.isEmpty(token) && StringUtils.isEmpty(refreshToken)) {
            // requestToken为空
            response.setStatus(TokenType.ERROR_TOKEN_IS_NULL.getCode());
            jsonObject.put("code", TokenType.ERROR_TOKEN_IS_NULL.getCode());
            jsonObject.put("msg", TokenType.ERROR_TOKEN_IS_NULL.getMsg());
            response.getWriter().println(jsonObject);
            return false;
        }

        Claims claims = JwtUtil.checkToken(request.getHeader("token"));
        //原token有问题
        if (claims == null) {
            Claims refreshClaims = JwtUtil.checkToken(request.getHeader("refresh_token"));
            if(refreshClaims == null){
                // 解析token中的用户信息claims为null
                response.setStatus(TokenType.ERROR_TOKEN_CLAIMS_IS_NULL.getCode());
                jsonObject.put("code", TokenType.ERROR_TOKEN_CLAIMS_IS_NULL.getCode());
                jsonObject.put("msg", TokenType.ERROR_TOKEN_CLAIMS_IS_NULL.getMsg());
                response.getWriter().println(jsonObject);
                return false;
            }else {
                // 解析token中的用户信息claims为null
                Long userId = Long.valueOf(refreshClaims.get("user_id").toString());
                String userName = refreshClaims.get("user_name").toString();
                String jwtToken = JwtUtil.getToken(userId,userName);
                response.setStatus(TokenType.ERROR_TOKEN_CLAIMS_IS_NULL.getCode());
                jsonObject.put("code", TokenType.ERROR_TOKEN_CLAIMS_IS_NULL.getCode());
                jsonObject.put("msg", TokenType.ERROR_TOKEN_CLAIMS_IS_NULL.getMsg());
                jsonObject.put("data", jwtToken);
                response.getWriter().println(jsonObject);
                return true;
            }

        }

        if (!Boolean.TRUE.equals(redisTemplate.hasKey(RedisType.TOKEN.getName() + claims.get("user_id")))) {
            // token不存在于redis中，已过期
            response.setStatus(TokenType.ERROR_TOKEN_EXPIRE.getCode());
            jsonObject.put("code", TokenType.ERROR_TOKEN_EXPIRE.getCode());
            jsonObject.put("msg", TokenType.ERROR_TOKEN_EXPIRE.getMsg());
            response.getWriter().println(jsonObject);
            return false;
        }

        String tokenRedis = redisTemplate.opsForValue().get(RedisType.TOKEN.getName() + claims.get("user_id"));

        if (!("\""+token+"\"").equals(tokenRedis)) {
            // token错误，判为并发登录，挤下线
            // 对应的修改响应头的状态，用于前端判断做出相应的策略
            response.setStatus(TokenType.ERROR_TOKEN_CONCURRENT.getCode());
            jsonObject.put("code", TokenType.ERROR_TOKEN_CONCURRENT.getCode());
            jsonObject.put("msg", TokenType.ERROR_TOKEN_CONCURRENT.getMsg());
            response.getWriter().println(jsonObject);
            return false;
        }

        return true;
    }


    // 处理请求之后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("处理请求之后执行");
    }

    /**后置处理：在DispatcherServlet完全处理完请求后被调用，可用于清理资源等*/
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        System.out.println("后置处理");
    }
}