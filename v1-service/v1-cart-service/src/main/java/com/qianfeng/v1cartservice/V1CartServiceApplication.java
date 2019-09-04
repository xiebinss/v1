package com.qianfeng.v1cartservice;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class V1CartServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(V1CartServiceApplication.class, args);
    }

}
