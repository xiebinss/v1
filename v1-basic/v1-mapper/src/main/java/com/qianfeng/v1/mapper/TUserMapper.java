package com.qianfeng.v1.mapper;

import com.qianfeng.common.base.IBaseDao;
import com.qianfeng.v1.entity.TUser;
import org.apache.ibatis.annotations.Param;

public interface TUserMapper extends IBaseDao<TUser> {

    TUser selectUserByName(@Param("username") String username);
}