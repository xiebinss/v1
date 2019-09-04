package com.qianfeng.v1item.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class CommonConfig {
    @Bean
    public ThreadPoolExecutor initThreadPoolExecutor(){
        Runtime runtime = Runtime.getRuntime();
        int processors = runtime.availableProcessors();
        ThreadPoolExecutor pool=new ThreadPoolExecutor(processors,processors*2,30L, TimeUnit.SECONDS,new LinkedBlockingQueue<>(100));
        return pool;
    }
}
