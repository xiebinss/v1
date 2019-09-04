package com.qianfeng.v1webindex.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qianfeng.v1.api.IProductTypeService;
import com.qianfeng.v1.entity.TProductType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("index")
public class IndexController {

    @Reference
    private IProductTypeService productTypeService;

    @RequestMapping("home")
    public String getIndex(Model model){
        List<TProductType> list= productTypeService.getList();
        model.addAttribute("list",list);
        return "home";
    }


}
