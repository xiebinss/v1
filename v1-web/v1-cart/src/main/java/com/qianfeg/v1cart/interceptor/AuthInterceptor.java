package com.qianfeg.v1cart.interceptor;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qianfeng.api.IUserService;
import com.qianfeng.common.pojo.ResultBean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Reference
    private IUserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie cookies[]=request.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies) {
                if("user_token".equals(cookie.getName())){
                    String jwtToken = cookie.getValue();
                    ResultBean resultBean = userService.parseTokengeId(jwtToken);
                    if("200".equals(resultBean.getStatusCode())){
                        String userId = (String) resultBean.getDate();
                        request.setAttribute("userId",userId);
                        return true;
                    }
                }
            }
        }
        return true;
    }
}
