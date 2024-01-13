package com.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Swagger  配置文件
 *
 * @Author: lin
 * @create: 2019-09-11 14:24
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket controllerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("标题：商城系统_接口文档")
                        .description("描述：用于管理商城相关业务,具体包括管理系统，售卖系统，购买系统...")
                        .contact(new Contact("Socks", null, null))
                        .version("版本号:1.0")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("kgc.cn"))
                .paths(PathSelectors.any())
                .build();
    }

}