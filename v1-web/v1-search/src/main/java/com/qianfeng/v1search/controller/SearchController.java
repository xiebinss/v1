package com.qianfeng.v1search.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qianfeng.api.ISearchService;
import com.qianfeng.common.pojo.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("controller")
public class SearchController {
    @Reference
    private ISearchService searchService;

    @RequestMapping("search")
    public String search(String keywords, Model model){
        ResultBean resultBean=searchService.queryByKeyWords(keywords);
        model.addAttribute("result",resultBean);
        return "list";

    }
}
