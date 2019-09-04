package com.qianfeng.v1searchservice;

import com.qianfeng.api.ISearchService;
import com.qianfeng.common.pojo.ResultBean;
import com.qianfeng.v1.entity.TProduct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class V1SearchServiceApplicationTests {
    @Autowired
    private ISearchService searchService;
    @Test
    public void contextLoads() {
        ResultBean resultBean = searchService.sysalldata();
        System.out.println(resultBean.getDate());
    }

    @Test
    public void queryTest(){
       ResultBean resultBean= searchService.queryByKeyWords("小米");
        List<TProduct> list= (List<TProduct>) resultBean.getDate();
        for(TProduct product:list){
            System.out.println(product.getName());
        }
    }

}
