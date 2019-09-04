package com.qianfeng.v1productservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.qianfeng.common.base.BaseServiceImpl;
import com.qianfeng.common.base.IBaseDao;
import com.qianfeng.v1.api.IProductTypeService;
import com.qianfeng.v1.entity.TProductType;
import com.qianfeng.v1.mapper.TProductTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@Service
public class ProductTypeService extends BaseServiceImpl<TProductType> implements IProductTypeService {

   @Autowired
    private TProductTypeMapper productTypeMapper;
   @Autowired
   private RedisTemplate redisTemplate;

    @Override
    public IBaseDao<TProductType> getBaseDao() {
        return productTypeMapper;
    }

    @Override
    public List<TProductType> getList() {
        String keywords="productType:list";
        List<TProductType> cacheList= (List<TProductType>) redisTemplate.opsForValue().get(keywords);
        if(cacheList!=null){
                return cacheList;
        }
            List<TProductType> list= super.getList();
        if(list!=null){
            System.out.println("从数据库获取数据。。");
            redisTemplate.opsForValue().set(keywords,list);

        }
        return list;

    }
}
