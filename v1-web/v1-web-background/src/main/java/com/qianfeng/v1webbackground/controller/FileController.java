package com.qianfeng.v1webbackground.controller;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.qianfeng.common.pojo.ResultBean;
import com.qianfeng.common.pojo.WangEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("file")
public class FileController {
    @Value("${image.server}")
    private String imagePath;
    @Autowired
    private FastFileStorageClient client;

    @RequestMapping("upload")
    public ResultBean upload(MultipartFile file){
      String originalFilename= file.getOriginalFilename();
      String extName=originalFilename.substring(originalFilename.lastIndexOf(".")+1);

        try {
            //获取储存路径
            StorePath storePath=  client.uploadImageAndCrtThumbImage(file.getInputStream(),file.getSize(),extName,null);
            String path=imagePath+""+storePath.getFullPath();

            return new ResultBean("200",path);
        } catch (IOException e) {
            e.printStackTrace();
        }
      return new ResultBean("500","图片上传失败");
    }
    @RequestMapping("multiUpload")
    public WangEditor multiUpload(MultipartFile[] files){
        String[] data=new String[files.length];
        for (int i=0;i<files.length;i++) {
            //将文件上传到FastDFS上
            //1.获取后缀名
            MultipartFile file = files[i];
            String originalFilename = file.getOriginalFilename();
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
            //2.上传文件
            try {
                StorePath storePath = client.uploadImageAndCrtThumbImage(
                        file.getInputStream(), file.getSize(), extName, null);
                //获取上传后的保存路径
                String path = storePath.getFullPath();
                //保存到数组中 StringBuilder
                data[i] = imagePath+path;
            } catch (IOException e) {
                e.printStackTrace();
                return new WangEditor("-1",null);
            }
        }
        //传递会给客户端进行展示
        return new WangEditor("0",data);
    }
}
