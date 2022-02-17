package com.example.details;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
@ServletComponentScan("com.example.details.filter")
@ComponentScan(basePackages = {"com.example.common", "com.example.details"})
@SpringBootApplication
@EnableEurekaClient
public class DetailsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DetailsApplication.class, args);
    }

}
