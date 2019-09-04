package com.qianfeng.v1productservice;

import com.qianfeng.v1.api.IProductService;
import com.qianfeng.v1.api.IProductTypeService;
import com.qianfeng.v1.entity.TProduct;
import com.qianfeng.v1.entity.TProductType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class V1ProductServiceApplicationTests {
    @Autowired
    private IProductService productService;
    @Autowired
    private IProductTypeService productTypeService;

    @Test
    public void contextLoads() {
        TProduct product=productService.selectByPrimaryKey(7L);
        System.out.println(product.getPrice()+""+product.getName());
    }

    @Test
    public void testList(){
        List<TProductType> list = productTypeService.getList();
        for (TProductType productType : list) {
            System.out.println(productType.getId()+"->"+productType.getName());
        }
    }

    @Test
    public void productList(){
        List<TProduct> list = productService.getList();
        for (TProduct productType : list) {
            System.out.println(productType.getId()+"->"+productType.getName());
        }
    }

}
