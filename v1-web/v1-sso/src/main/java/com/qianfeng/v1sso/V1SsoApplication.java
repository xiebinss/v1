package com.qianfeng.v1sso;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class V1SsoApplication {

    public static void main(String[] args) {
        SpringApplication.run(V1SsoApplication.class, args);
    }

}
