package com.qianfeg.v1cart;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo

public class V1CartApplication {

    public static void main(String[] args) {
        SpringApplication.run(V1CartApplication.class, args);
    }

}
