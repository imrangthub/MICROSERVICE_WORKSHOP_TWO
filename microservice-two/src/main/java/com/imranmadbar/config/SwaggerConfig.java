package com.imranmadbar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("com.imranmadbar")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.imranmadbar"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(
                        new ApiInfoBuilder()
                                .title("Swagger2 Application")
                                .description("A simple Spring boot applicaton")
                                .contact(
                                        new Contact(
                                                "MD IMRAN HOSSAIN",
                                                "imranmadbar@gmail.com",
                                                "imranmadbar@gmail.com"))
                                .build());
    }



}
