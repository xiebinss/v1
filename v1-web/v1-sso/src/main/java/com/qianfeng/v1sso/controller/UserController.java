package com.qianfeng.v1sso.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qianfeng.api.IUserService;
import com.qianfeng.common.pojo.ResultBean;
import com.qianfeng.v1.entity.TUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("user")
public class UserController {
    @Reference
    private IUserService userService;

    @RequestMapping("showLogin")
    public String showLogin(){
        return "login";
    }

    @RequestMapping("chenckLogin")
    @ResponseBody
    public ResultBean<String> checkLogin(String username, String password, HttpServletResponse response){
        TUser user=new TUser();
        user.setUsername(username);
        user.setPassword(password);
        ResultBean<String> resultBean = userService.checkLogin(user);
        if ("200".equals(resultBean.getStatusCode())){
            String key="user_token";
            Cookie cookie=new Cookie(key,resultBean.getDate());
            cookie.setPath("/");
            cookie.setHttpOnly(true);
           cookie.setDomain("qf.com");
            response.addCookie(cookie);
        }
        return resultBean;
    }
    @RequestMapping("checkIsLogin")
    @ResponseBody
    @CrossOrigin(origins = "*",allowCredentials = "true")
    public ResultBean checkIsLogin(@CookieValue(name="user_token",required = false)String uuid){
      if(uuid!=null){
          ResultBean resultBean=userService.checkIsLogin(uuid);
          if("200".equals(resultBean.getStatusCode())){
                return resultBean;
          }
      }
        return new ResultBean("400","未登陆");
    }

    @RequestMapping("logout")
    @ResponseBody
    public ResultBean logout(@CookieValue(name="user_token")String uuid,HttpServletResponse response){
        ResultBean resultBean = userService.logout(uuid);
        Cookie cookie=new Cookie("user_token",uuid);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        cookie.setDomain("qf.com");
        response.addCookie(cookie);
        return  resultBean;
    }

}
