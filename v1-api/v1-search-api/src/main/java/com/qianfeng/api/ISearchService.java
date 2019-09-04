package com.qianfeng.api;


import com.qianfeng.common.pojo.ResultBean;

public interface ISearchService {
    /*
    * 同步数据的接口
    *初始化调用一次
    */

    public ResultBean sysalldata();
    public ResultBean queryByKeyWords(String keywords);
    public ResultBean updateById(Long id);

}
