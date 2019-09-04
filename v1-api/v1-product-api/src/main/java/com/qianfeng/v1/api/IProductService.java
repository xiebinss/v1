package com.qianfeng.v1.api;

import com.github.pagehelper.PageInfo;
import com.qianfeng.common.base.IBaseService;
import com.qianfeng.v1.api.vo.ProductVo;
import com.qianfeng.v1.entity.TProduct;

import java.util.List;

public interface IProductService extends IBaseService<TProduct>{

    public List<TProduct> gettList();
    public PageInfo<TProduct> page(Integer pageIndex,Integer pageSize);

    Long add(ProductVo vo);

    ProductVo getUserById(Long id);

    int deleteById(Long id);

    Long batchDel(List<Long> ids);
}
