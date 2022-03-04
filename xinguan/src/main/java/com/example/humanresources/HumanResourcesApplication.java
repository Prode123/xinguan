package com.example.humanresources;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author dell
 */
@ServletComponentScan
@SpringBootApplication
@EnableOpenApi
@EnableAspectJAutoProxy
public class HumanResourcesApplication {

    public static void main(String[] args) {
        SpringApplication.run(HumanResourcesApplication.class, args);
    }

}
