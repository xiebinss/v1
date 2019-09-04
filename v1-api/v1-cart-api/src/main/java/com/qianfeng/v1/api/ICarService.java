package com.qianfeng.v1.api;


import com.qianfeng.common.pojo.ResultBean;

/*
* 购物车操作
* */
public interface ICarService {
    public ResultBean add(String key, Long productId, Integer count);
    public ResultBean del(String key,Long id);
    public ResultBean update(String key,long productId,Integer count);
    public ResultBean query(String key);
    public ResultBean merge(String noLoginKey,String loginKey);
}
