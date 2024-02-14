package com.dletc.service.impl;

import com.dletc.dao.UserDao;
import com.dletc.dao.impl.UserDaoImpl;
import com.dletc.domain.User;
import com.dletc.service.UserService;

public class UserServiceImpl implements UserService {
    //实现业务层与dao层的连接
    //声明dao层对象
     UserDao userDao = new UserDaoImpl();

    @Override
    public User login(String ukey, String pwd) {
        //没有什么业务，就是把用户名和密码传到dao层
        User user = userDao.getUserByUkeyAndPwd(ukey, pwd);
        return  user;
    }
}
