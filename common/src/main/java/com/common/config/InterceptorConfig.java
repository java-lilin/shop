package com.common.config;

import com.common.handle.InterceptorHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

/**
 * @version 1.0
 * @projectName: shop
 * @author: lin
 * @description:TODO
 * @date: 2024/1/19  22:22
 */
@Component
public class InterceptorConfig implements WebMvcConfigurer {
    // 注入自定义拦截器
    @Resource
    private InterceptorHandler interceptor;
    //白名单列表
    @Value("${auth.whiteList}")
    private List<String> whiteList;

    // 重写添加拦截器方法
    @Override
    public void addInterceptors(InterceptorRegistry registry){  // InterceptorRegistry 为拦截器注册对象
        registry.addInterceptor(interceptor)  // 注册自定义拦截器
                .addPathPatterns("/**")// 拦截的路径
                .excludePathPatterns(whiteList); // 不拦截的路径
    }
}