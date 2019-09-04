package com.qianfeng.v1.mapper;

import com.qianfeng.common.base.IBaseDao;
import com.qianfeng.v1.entity.TProduct;

import java.util.List;

public interface TProductMapper extends IBaseDao<TProduct> {



    int delById(Long id);

    Long batchDel(List<Long> ids);
}