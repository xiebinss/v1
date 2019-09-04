package com.qianfeng.common.pojo;

import java.io.Serializable;

public class ResultBean<T> implements Serializable {
    private String statusCode;
    private T date;
    public ResultBean() {

    }
    public ResultBean(String statusCode, T date) {
        this.statusCode = statusCode;
        this.date = date;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }
}
