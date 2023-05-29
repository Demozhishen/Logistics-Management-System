package com.bjtu.web.spring_boot.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
// @EnableKnife4j
// @EnableSwagger2
public class Knife4jConfig {
    @Bean
    public Docket docket() {
        Docket docket = new Docket(DocumentationType.OAS_30)
                .apiInfo(new ApiInfoBuilder()
                        .title("物流管理系统api文档")
                        .description("对物流管理系统接口进行测试")
                        // .termsOfServiceUrl("http://www.xx.com/")
                        .contact(new Contact("JuBao Cheng", "http://localhost:8080/login", "1448527427@qq.com"))
                        .version("1.0")
                        .build())
                // 分组名称
                .groupName("all")
                .select()
                // 这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.bjtu.web.spring_boot.controller"))
                .paths(PathSelectors.any())
                .build();

        return docket;
    }
}
