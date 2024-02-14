package com.dletc.dao;

import com.dletc.domain.User;

//操作用户表
public interface UserDao {
    //1.根据用户名和密码查询用户
    User getUserByUkeyAndPwd(String ukey, String pwd);
}
