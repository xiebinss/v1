package com.qianfeng.common.pojo;

import java.io.Serializable;

public class WangEditor implements Serializable {
    private String errno;
    private String[] data;

    public WangEditor() {

    }
    public WangEditor(String errno, String[] data) {
        this.errno = errno;
        this.data = data;
    }

    public String getErrno() {
        return errno;
    }

    public void setErrno(String errno) {
        this.errno = errno;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }
}
