package com.qianfeng.springbootfreemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootFreemarkerApplicationTests {
    @Autowired
    private Configuration configuration;
    @Test
    public void contextLoads() throws IOException, TemplateException {
        //获取模板对象
        Template template = configuration.getTemplate("freemarker.ftl");
        Map<String,Object> data=new HashMap<>();
        data.put("username","java");
        //输出数据
        FileWriter out=new FileWriter("D:\\javaEE1904no3\\v1\\v1-temp\\springboot-freemarker\\src\\main\\resources\\templates\\freemarker.html");
        template.process(data,out);
        System.out.println("成功");
    }
}
