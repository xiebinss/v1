package com.qianfeng.v1item;

import com.qianfeng.v1.api.IProductService;
import freemarker.template.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class V1ItemApplicationTests {
    @Autowired
    private Configuration configuration;
    @Autowired
    private IProductService productService;
    @Test
    public void contextLoads() throws IOException {

    }

}
