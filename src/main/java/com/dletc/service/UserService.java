package com.dletc.service;

import com.dletc.domain.User;

public interface UserService {
    //1.登录方法
        User login(String ukey, String pwd);
}
