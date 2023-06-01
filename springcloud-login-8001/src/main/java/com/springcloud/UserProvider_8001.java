package com.springcloud;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@MapperScan("com.springcloud.mapper")
@EnableEurekaClient
public class UserProvider_8001 {
    public static void main(String[] args) {
        SpringApplication.run(UserProvider_8001.class,args);
    }
}
