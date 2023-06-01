package com.springcloud;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;


@SpringBootApplication
@MapperScan("com.springcloud.mapper")
@EnableHystrix
@EnableEurekaClient
public class UserProvider_8002 {
    public static void main(String[] args) {
        SpringApplication.run(UserProvider_8002.class,args);
    }
}
