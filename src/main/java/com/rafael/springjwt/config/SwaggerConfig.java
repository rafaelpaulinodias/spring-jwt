package com.rafael.springjwt.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.rafael.springjwt"))
                .paths(regex("/api.*"))
                .build()
                .apiInfo(metaInfo());
    }
	
	private ApiInfo metaInfo() {
		
        ApiInfo apiInfo = new ApiInfo(
                "Example of REST API with JWT",
                "Register of Items with JWT access control.",
                "1.0",
                "Free to use",
                new Contact(
                		"Rafael Dias",
                		"https://github.com/rafaelpaulinodias",
                        "rafael.paulinodias@gmail.com"
                	),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html", 
                Collections.emptyList()
        );

        return apiInfo;
	}
}
