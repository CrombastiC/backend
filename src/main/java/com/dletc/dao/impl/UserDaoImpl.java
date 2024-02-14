package com.dletc.dao.impl;

import com.dletc.dao.UserDao;
import com.dletc.domain.User;
import com.dletc.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public User getUserByUkeyAndPwd(String ukey, String pwd) {
        User user= null;
        try {
            //创建qr
            QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
            //定义sql
            String sql = "select * from user where ukey=? and pwd=?";
            //执行sql
            user = qr.query(sql, new BeanHandler<>(User.class), ukey, pwd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
