package com.example.humanresources.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger3Config {

    @Bean
    public Docket   getDocket(){
        return  new Docket(DocumentationType.OAS_30).apiInfo(getApiInfo());
        /*return  new Docket(DocumentationType.OAS_30)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();*/
    }

    @Bean
    public ApiInfo   getApiInfo(){
        return   new ApiInfoBuilder()
                .title("客户关系管理系统-接口文档")
                .description("这是华杉的crm！！！！")
                .contact(new Contact("xiao","http://www.baidu.com","873976729@qq.com"))
                .version("v1.0")
                .build();
    }
}
