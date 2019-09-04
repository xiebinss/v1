package com.qianfeng.v1item.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qianfeng.common.pojo.ResultBean;
import com.qianfeng.v1.api.IProductService;
import com.qianfeng.v1.entity.TProduct;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Controller
@RequestMapping("item")
public class ItemController {
    @Reference
    private IProductService productService;
    @Autowired
    private Configuration configuration;
    @Autowired
    private  ThreadPoolExecutor pool;
    @RequestMapping("creatById/{id}")
    @ResponseBody
    public ResultBean creatById(@PathVariable("id") Long id){
        try {
            Template template = configuration.getTemplate("item.ftl");
            TProduct product=productService.selectByPrimaryKey(id);
            Map<String,Object> data=new HashMap<>();
            data.put("product",product);

            FileWriter out=null;

            String serverPath= ResourceUtils.getURL("classpath:static").getPath();
            out=new FileWriter(serverPath+ File.separator+product.getId()+".html");
            template.process(data,out);
            return new ResultBean("200","生成成功");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return new ResultBean("500","生成失败");
    }


    @RequestMapping("batchCreat")
    @ResponseBody
    public ResultBean batchCreat(@RequestParam List<Long> ids){
        //存id集合
        List<Future<Long>> results=new ArrayList<>(ids.size());
        for(Long id:ids){
            Future<Long> future = pool.submit(new CreatTask(id));
            results.add(future);
            List<Long> errors=new ArrayList<>();
            for (Future<Long> result:results){
                try {
                    Long resultNum = result.get();
                    if(resultNum!=0){
                        errors.add(resultNum);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
            //判断批量生成是否成功
            if (errors.size()>0){
                return new ResultBean("500","生成失败");
            }
        }
        return new ResultBean("200","批量生成成功");
    }
    /*
    * 创建静态页面
    * */
  private   class CreatTask implements Callable<Long>{
        private Long id;

        public CreatTask(Long id) { this.id = id; }
        public CreatTask() { }
        @Override
        public Long call() throws Exception {
            try {
                Template template = configuration.getTemplate("item.ftl");
                TProduct product=productService.selectByPrimaryKey(id);
                Map<String,Object> data=new HashMap<>();
                data.put("product",product);

                FileWriter out=null;

                String serverPath= ResourceUtils.getURL("classpath:static").getPath();
                out=new FileWriter(serverPath+ File.separator+product.getId()+".html");
                template.process(data,out);
                return 0L;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TemplateException e) {
                e.printStackTrace();
            }
            return id;
        }
    }

 }

