package com.qianfeng.v1cartservice.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.qianfeng.common.pojo.ResultBean;
import com.qianfeng.v1.api.ICarService;
import com.qianfeng.v1.pojo.CarItem;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.*;

@Service
public class CarServiceImpl implements ICarService {
    @Resource(name="redisTemplate4String")
    private RedisTemplate<String,Object> redisTemplate;
    @Override
    public ResultBean add(String key, Long productId, Integer count) {
        StringBuilder redisKey=new StringBuilder("user_car:").append(key);
        Map<Object, Object> car = redisTemplate.opsForHash().entries(redisKey.toString());
        if(car.get(productId)!=null){
            CarItem carItem= (CarItem) car.get(productId);
            carItem.setCount(carItem.getCount()+count);
            carItem.setUpdateTime(new Date());
            redisTemplate.opsForHash().putAll(redisKey.toString(),car);
            return new ResultBean("200","添加成功");
        }
        CarItem carItem=new CarItem(productId,count,new Date());
        car.put(productId,carItem);
        redisTemplate.opsForHash().putAll(redisKey.toString(),car);
        return new ResultBean("200","添加成功");
    }

    @Override
    public ResultBean del(String key, Long id) {
        StringBuilder redisKey=new StringBuilder("user_cart:").append(key);
        Long delete = redisTemplate.opsForHash().delete(redisKey.toString(),id);
        if(delete>0){
            return new ResultBean("200","删除成功");
        }
        return new ResultBean("400","删除失败");
    }

    @Override
    public ResultBean update(String key, long productId, Integer count) {
        StringBuilder redisKey=new StringBuilder("user_cart:").append(key);
        CarItem carItem= (CarItem) redisTemplate.opsForHash().get(redisKey.toString(),productId);
        if(carItem!=null){
            carItem.setCount(count);
            carItem.setUpdateTime(new Date());

            redisTemplate.opsForHash().put(redisKey.toString(),productId,carItem);
            return new ResultBean("200","跟新完成");
        }
        return new ResultBean("400","不存在");
    }

    @Override
    public ResultBean query(String key) {
        //1.获取购物车信息
        StringBuilder redisKey = new StringBuilder("user_cart:").append(key);
        Map<Object, Object> cart = redisTemplate.opsForHash().entries(redisKey.toString());
        if(cart.size() > 0){
            //做排序处理
            Collection<Object> values = cart.values();
            //设置比较规则
            TreeSet<CarItem> target = new TreeSet<>();
            //将购物车的信息逐个添加到新集合中，自动根据排序规则进行排序
            for (Object value : values) {
                target.add((CarItem) value);
            }
            return new ResultBean("200",target);
        }
        return new ResultBean("404","当前购物车为空");
    }
/*
* 购物车和并
* */
    @Override
    public ResultBean merge(String noLoginKey, String loginKey) {
        StringBuilder noLoginRedisKey=new StringBuilder("user_cart:").append(noLoginKey);
       //未登录的购物车
        Map<Object,Object> noLoginCart=redisTemplate.opsForHash().entries(noLoginRedisKey.toString());
        if (noLoginCart.size()==0){
            return new ResultBean("200","不存在未登录购物车，无需合并");
        }
        StringBuilder loginRedisKey=new StringBuilder("user_cart").append(noLoginKey);
        //已登陆的购物车
        Map<Object,Object> loginCart=redisTemplate.opsForHash().entries(loginRedisKey.toString());
        if(loginCart.size()==0){
            redisTemplate.opsForHash().putAll(loginRedisKey.toString(),noLoginCart);
            redisTemplate.delete(noLoginRedisKey.toString());
            return new ResultBean("200","不存在已登陆购物车，直接将未登录购物车合并");
        }
        //未登录的购物车商品集合
        Set<Map.Entry<Object, Object>> noLoginEntries = noLoginCart.entrySet();
        for (Map.Entry<Object,Object> noLoginEntry: noLoginEntries){
           if (redisTemplate.opsForHash().get(loginRedisKey.toString(),noLoginEntry.getKey())!=null){
              CarItem carItem= (CarItem) redisTemplate.opsForHash().get(loginRedisKey.toString(),noLoginEntry.getKey());
              CarItem noLoginCarItrm= (CarItem) noLoginEntry.getValue();
              carItem.setCount(carItem.getCount()+noLoginCarItrm.getCount());
              carItem.setUpdateTime(new Date());
              redisTemplate.opsForHash().put(loginRedisKey.toString(),noLoginEntry.getKey(),carItem);
            }else{
               redisTemplate.opsForHash().put(loginRedisKey.toString(),noLoginEntry.getKey(),noLoginEntry.getValue());
            }
        }
        redisTemplate.delete(noLoginRedisKey.toString());
        return new ResultBean("200","合并成功");
    }

}
