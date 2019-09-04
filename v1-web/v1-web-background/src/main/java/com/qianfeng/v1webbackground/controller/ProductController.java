package com.qianfeng.v1webbackground.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.qianfeng.api.ISearchService;
import com.qianfeng.common.constant.RabbitMQCofig;
import com.qianfeng.common.pojo.ResultBean;
import com.qianfeng.v1.api.IProductService;
import com.qianfeng.v1.api.vo.ProductVo;
import com.qianfeng.v1.entity.TProduct;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("productor")
public class ProductController {
    @Reference
    private IProductService productService;
    @Reference
    private ISearchService searchService;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @RequestMapping("getById/{id}")
    @ResponseBody
    public TProduct getById(@PathVariable("id")Long id){
        return productService.selectByPrimaryKey(id);
    }

    @RequestMapping("list")
    public String getList(ModelMap map){
        List<TProduct> productList= productService.getList();
        map.put("productList",productList);
        return "product/list";
    }
    //分页获取数据
    @RequestMapping("page/{pageIndex}/{pageSize}")
    public String page(ModelMap map,@PathVariable("pageIndex")Integer pageIndex,@PathVariable("pageSize")Integer pageSize){
        //1.獲取到分頁的信息
        PageInfo<TProduct> pageInfo = productService.page(pageIndex, pageSize);
        map.put("pageInfo",pageInfo);
       /* map.put("url","product/page/{pageIndex}/{pageSzie}");*/
        return "product/list";
    }

    @RequestMapping("add")
    public String add(ProductVo vo){
        Long newId=productService.add(vo);
        rabbitTemplate.convertAndSend(RabbitMQCofig.BACKGROUND_PRODUCT_EXCHANGE,"product.add",newId);


        /*searchService.updateById(newId);*/
        return "redirect:/productor/page/1/1";

    }
    @RequestMapping("delById/{id}")
    @ResponseBody
    public ResultBean delById(@PathVariable("id") Long id){
            ResultBean resultBean=new ResultBean();
            int count = productService.deleteById(id);
            if(count>0){
                resultBean.setStatusCode("200");
                resultBean.setDate("删除成功");
            }else{
                resultBean.setStatusCode("500");
                resultBean.setDate("删除失败");
            }
            return resultBean;
    }
    /*
    * 获取修改对象
    * */
    @RequestMapping("getUserById/{id}")
    @ResponseBody
    public ProductVo getUserById(@PathVariable("id")Long id){
         ProductVo productVo= productService.getUserById(id);
         return productVo;
    }

    /*批量删除对象*/
    @RequestMapping("batchDel")
    @ResponseBody
    public ResultBean batchDel(@RequestParam List<Long> ids){
        Long count = productService.batchDel(ids);
        ResultBean resultBean=new ResultBean();
        if(count>0){
            resultBean.setStatusCode("200");
            resultBean.setDate("删除成功");
        }else{
            resultBean.setStatusCode("500");
            resultBean.setDate("删除失败");
        }
        return resultBean;
    }
}
