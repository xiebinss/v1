package com.qianfeng.rabbitmqspringboot.entity;

import java.io.Serializable;

public class ProductDTO implements Serializable {
    private int id;
    private String name;

    public ProductDTO() {
    }

    public ProductDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
