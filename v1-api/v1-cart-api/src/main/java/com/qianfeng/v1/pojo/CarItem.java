package com.qianfeng.v1.pojo;

import java.io.Serializable;
import java.util.Date;

public class CarItem implements Serializable,Comparable<CarItem> {
    private Long productId;
    private Integer count;
    private Date updateTime;

    public CarItem(Long productId, Integer count, Date updateTime) {
        this.productId = productId;
        this.count = count;
        this.updateTime = updateTime;
    }

    public CarItem() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public int compareTo(CarItem o) {
            return (int)(o.getUpdateTime().getTime()-this.getUpdateTime().getTime());
    }
}
