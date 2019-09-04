package com.qianfeng.v1.mapper;

import com.qianfeng.common.base.IBaseDao;
import com.qianfeng.v1.entity.TProductDesc;
import org.apache.ibatis.annotations.Param;

public interface TProductDescMapper extends IBaseDao<TProductDesc> {

    TProductDesc selextByProductId(@Param("productId") Long productId);
}