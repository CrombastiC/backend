package com.dletc.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtils {

    //创建数据库连接池对象，并赋值为空
    private  static DruidDataSource dataSource = null;

    //第一步：创建数据库连接池
    static {

        try {
            //1 加载数据库连接池配置文件信息
            //1.1 创建一个Properties类型的集合，用户存储driver url username password 四个参数信息
            Properties pro = new Properties();

            //1.2 读取配置文件里的四个参数的信息，存储到pro集合中
            pro.load(DBUtils.class.getClassLoader().getResourceAsStream("database.properties"));

            //1.2 创建数据库连接池,告诉数据库连接池连接哪一个数据库，返回一个数据库连接池对象
            //向下转型为子类dataSource类型
             dataSource = (DruidDataSource)DruidDataSourceFactory.createDataSource(pro);

        } catch (Exception e) {

            System.out.println("数据库连接失败");

        }
    }

    //第二步：获取数据库连接对象
    public  static Connection getConnection(){

        Connection conn = null;

        try {
            conn = dataSource.getConnection();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return  conn;//没有成功返回null
    }

    //第三步：获取数据库连接池对象的方法
    public  static  DruidDataSource getDataSource(){
        return  dataSource;
    }



    //第四步：释放资源
    public static void closeAll(ResultSet rs, Statement stat, Connection conn) {
        try {
            if(rs!=null) {
                rs.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            if(stat!=null) {
                stat.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            if(conn!=null) {
                conn.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
