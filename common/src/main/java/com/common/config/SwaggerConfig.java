package com.common.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Swagger  配置文件
 *
 * @author: tzh
 * @create: 2019-09-11 14:24
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket controllerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("标题：XX公司_XXXXX系统_接口文档")
                        .description("描述：用于管理XXXXX,具体包括XXX,XXX模块...")
                        .contact(new Contact("Socks", null, null))
                        .version("版本号:1.0")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("kgc.cn"))
                .paths(PathSelectors.any())
                .build();
    }

}