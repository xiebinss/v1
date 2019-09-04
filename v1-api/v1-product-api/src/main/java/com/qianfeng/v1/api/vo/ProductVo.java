package com.qianfeng.v1.api.vo;

import com.qianfeng.v1.entity.TProduct;

import java.io.Serializable;

public class ProductVo implements Serializable {
    private TProduct product;
    private String productDesc;

    public ProductVo() {

    }

    public ProductVo(TProduct product, String productDesc) {
        this.product = product;
        this.productDesc = productDesc;
    }

    public TProduct getProduct() {
        return product;
    }

    public void setProduct(TProduct product) {
        this.product = product;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }
}


