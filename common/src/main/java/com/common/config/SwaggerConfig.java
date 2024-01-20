package com.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


/**
 * Swagger  配置文件
 *
 * @Author: lin
 * @create: 2019-09-11 14:24
 */
@Configuration
public class SwaggerConfig {

    private static Logger log = LoggerFactory.getLogger(SwaggerConfig.class);


    @Bean
    public Docket controllerApi() {
        log.info("进入到swagger的配置中");
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