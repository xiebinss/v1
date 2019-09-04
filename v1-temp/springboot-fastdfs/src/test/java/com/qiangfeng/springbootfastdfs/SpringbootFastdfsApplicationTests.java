package com.qiangfeng.springbootfastdfs;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootFastdfsApplicationTests {
    @Autowired
    private FastFileStorageClient client;
    @Test
    public void uploadTest() throws FileNotFoundException {
        File file=new File("D:\\javaEE1904no3\\v1\\v1-temp\\springboot-fastdfs\\src\\main\\resources\\static\\1.jpg");
        FileInputStream fileInputStream=new FileInputStream(file);
        StorePath storePath=client.uploadImageAndCrtThumbImage(fileInputStream,file.length(),"jpg",null);
        System.out.println(storePath.getFullPath());
    }
    @Test
    public void del(){
        client.deleteFile("group1/M00/00/00/rBAaE11MHcuACmqOAAAV_Hl0zOA389.jpg");
        System.out.println("ok");
    }

}
