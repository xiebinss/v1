package com.qianfeng.v1item;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class V1ItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(V1ItemApplication.class, args);
    }

}
