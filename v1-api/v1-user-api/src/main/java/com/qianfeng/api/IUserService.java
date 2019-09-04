package com.qianfeng.api;

import com.qianfeng.common.base.IBaseService;
import com.qianfeng.common.pojo.ResultBean;
import com.qianfeng.v1.entity.TUser;

public interface IUserService extends IBaseService<TUser> {

    public ResultBean checkLogin(TUser user);

    public ResultBean checkIsLogin(String uuid);

    ResultBean logout(String uuid);

    public ResultBean parseTokengeId(String token);
}
