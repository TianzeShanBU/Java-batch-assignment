package com.example.details.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
    }

//    private ApiInfo apiInfo(){
//        return  new ApiInfo("Weather Api Documentation",
//                "Homework3",
//                "1.0",
//                "https://github.com/TianzeShanBU",
//                new Contact("Tianze Shan","https://github.com/TianzeShanBU","shant2@bu.edu"),
//                "Apache 2.0",
//                "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList());
//    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("JavaInUse API")
                .description("JavaInUse API reference for developers")
                .termsOfServiceUrl("http://javainuse.com")
                .contact("javainuse@gmail.com").license("JavaInUse License")
                .licenseUrl("javainuse@gmail.com").version("1.0").build();
    }

}
