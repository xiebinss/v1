package com.qianfeng.v1webindex;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class V1WebIndexApplication {

    public static void main(String[] args) {
        SpringApplication.run(V1WebIndexApplication.class, args);
    }

}
