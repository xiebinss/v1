package com.qianfeg.v1cart.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qianfeng.common.pojo.ResultBean;
import com.qianfeng.v1.api.ICarService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
@RequestMapping("cart")
public class CarController {
    @Reference
    private ICarService carService;

    /*
    * 如果user_car为空  就创建一个uuid放入cookie作为购物车凭证，如果已经登陆会通过过滤器将userId作为凭证操作已登陆购物车
    * */
    @RequestMapping("add/{productId}/{count}")
    @ResponseBody
    public ResultBean add(@PathVariable("productId")Long productId,
                          @PathVariable("count")Integer count,
                          @CookieValue(name="user_cart", required = false)String uuid,
                          HttpServletResponse response, HttpServletRequest request){

        String userId= (String) request.getAttribute("userId");
        if(userId!=null){
            return carService.add(userId,productId,count);
        }
        if(uuid==null||"".equals(uuid)){
            //1.创建uuid
            uuid = UUID.randomUUID().toString();
            fiushCookie(uuid, response,30*60*60*24);

        }
        ResultBean resultBean= carService.add(uuid,productId,count);
         return resultBean;
    }
    @RequestMapping("query")
    @ResponseBody
    public ResultBean query(@CookieValue(name="user_cart", required = false)String uuid,HttpServletResponse response,HttpServletRequest request){
        String userId= (String) request.getAttribute("userId");
        if(userId!=null){
            return carService.query(userId);
        }
        if(uuid==null||"".equals(uuid)){
            return new ResultBean("400","购物车已经清空");
        }
        fiushCookie(uuid, response,30*60*60*24);
        return carService.query(uuid);
    }

    @RequestMapping("update/{productId}/{count}")
    @ResponseBody
    public ResultBean update(@PathVariable("productId")Long productId,
                          @PathVariable("count")Integer count,
                          @CookieValue(name="user_cart", required = false)String uuid,
                          HttpServletResponse response){
        if(uuid==null||"".equals(uuid)){
            return new ResultBean("400","购物车已经清空");
        }

        ResultBean resultBean= carService.update(uuid,productId,count);
        fiushCookie(uuid, response,30*60*60*24);
        return resultBean;
    }

    @RequestMapping("del/{productId}")
    public ResultBean del(@PathVariable("productId") Long productId,
                          @CookieValue(name = "user_cart",required = false) String uuid,
                          HttpServletResponse response){
        //1.判断是否为第一次操作购物车
        if(uuid == null || "".equals(uuid)){
            return new ResultBean("404","您的购物车已经被老公清空了！");
        }
        //更新cookie的有效期
        fiushCookie(uuid, response,30*60*60*24);
        //2.更新购物车
        return carService.del(uuid,productId);
    }


    @RequestMapping("merge")
    @ResponseBody
    public ResultBean merge(@CookieValue(name="user_cart", required = false)String uuid,
                            HttpServletResponse response, HttpServletRequest request){
        String userId= (String) request.getAttribute("userId");
        if(userId!=null){
            return carService.merge(uuid,userId);
        }
        return new ResultBean("404","无需合并");
    }
    private void fiushCookie(@CookieValue(name = "user_cart", required = false) String uuid, HttpServletResponse response,int expire) {
        Cookie cookie=new Cookie("user_cart",uuid);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(expire);
        cookie.setDomain("qf.com");
        response.addCookie(cookie);
    }
}
