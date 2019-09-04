package com.qianfeng.v1userservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.qianfeng.api.IUserService;
import com.qianfeng.common.base.BaseServiceImpl;
import com.qianfeng.common.base.IBaseDao;
import com.qianfeng.common.pojo.ResultBean;
import com.qianfeng.v1.entity.TUser;
import com.qianfeng.v1.mapper.TUserMapper;
import com.qianfeng.v1userservice.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends BaseServiceImpl<TUser> implements IUserService {
    @Autowired
    private TUserMapper userMapper;

    @Resource(name = "redisTemplate4String")
    private RedisTemplate<String,Object> redisTemplate;
    @Override
    public IBaseDao<TUser> getBaseDao() {
        return userMapper;
    }

    @Override
    public ResultBean checkLogin(TUser user) {
        TUser currentUser=userMapper.selectUserByName(user.getUsername());
        //2.做密码的匹配判断
        if(user.getPassword().equals(currentUser.getPassword())){
            //登录验证成功！ redis---》JWT
            //1.生成令牌
            JwtUtil jwtUtils = new JwtUtil();
            jwtUtils.setTtl(30*60*1000);
            jwtUtils.setSecretKey("java1904");

            String jwtToken = jwtUtils.createJwtToken(currentUser.getId().toString(), currentUser.getUsername());
            //2.返回结果
            return new ResultBean("200",jwtToken);
        }
        //登录验证失败！
        return new ResultBean("404","账号或密码错误！");
    }
        /* if(currentUser.getPassword().equals(user.getPassword())){
            String uuid= UUID.randomUUID().toString();
            StringBuilder key=new StringBuilder("usertoken:").append(uuid);
            currentUser.setPassword("");
            redisTemplate.opsForValue().set(key.toString(),currentUser);
            redisTemplate.expire(key.toString(),30, TimeUnit.MINUTES);
            return new ResultBean("200",uuid);
        }*/



    /*
    * 令牌方式验证是否登陆
    * */
    @Override
    public ResultBean checkIsLogin(String jwtToken ) {
        JwtUtil jwtUtil =new JwtUtil();
        jwtUtil.setSecretKey("java1904");
        try {
            Claims claims = jwtUtil.parseJwtToken(jwtToken);
            String username=claims.getSubject();
            return new ResultBean("200",jwtToken);

        }catch (SignatureException e){
            return new ResultBean("400","当前用户未登陆");
        }



    }
    /*@Override
    public ResultBean checkIsLogin(String uuid) {
        StringBuilder key=new StringBuilder("usertoken:").append(uuid);
        TUser currentUser= (TUser) redisTemplate.opsForValue().get(key.toString());
        if(currentUser!=null){
            redisTemplate.expire(key.toString(),30, TimeUnit.MINUTES);
            return new ResultBean("200",uuid);
        }
        return new ResultBean("400","当前用户未登陆");
    }
*/
    @Override
    public ResultBean logout(String uuid) {
        StringBuilder key=new StringBuilder("usertoken:").append(uuid);
        redisTemplate.delete(key.toString());
        return new ResultBean("200","注销成功") ;
    }

    @Override
    public ResultBean parseTokengeId(String token) {
       JwtUtil jwtUtil=new JwtUtil();
       jwtUtil.setSecretKey("java1904");
       try {
           Claims claims=jwtUtil.parseJwtToken(token);
           String id=claims.getId();
           return new ResultBean("200",id);
       }catch(SignatureException e) {
           return new ResultBean("400", "解析错误");
       }


    }
}
