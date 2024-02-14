package com.dletc.dao.impl;
import com.dletc.dao.ClazzDao;
import com.dletc.domain.Clazz;
import com.dletc.domain.Count;
import com.dletc.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.xml.ws.handler.Handler;
import java.sql.SQLException;
import java.util.List;

public class ClazzDaoImpl implements ClazzDao {
    @Override
    public int addClazz(Clazz clazz) {
        int count = 0;
        try{
            //创建qr
            QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
            //定义sql
            String sql = "insert into clazz values(null,?,?,?)";
            //执行sql
            count = qr.update(sql,clazz.getCname(),clazz.getCteacher(),clazz.getRemark());
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
    //分页查询数据
    @Override
    public List<Clazz> getPageClazz(int pageNum, int pageSize) {
        List<Clazz> list = null;
        try {
            //创建qr
            QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
            //定义sql limit 0,5
            String sql = "select * from clazz limit ?,?";
            //执行sql
            list = qr.query(sql, new BeanListHandler<Clazz>(Clazz.class), (pageNum - 1) * pageSize, pageSize);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;

    }
    //获取总记录数
    @Override
    public long getCount() {
        long count= 0;
        try {
            QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
            count = (long) qr.query("select count(*) from clazz", new ScalarHandler<>());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    @Override
    public int deleteClazzById(int cid) {
        int id = 0;
        try {
            QueryRunner qr=new QueryRunner(DBUtils.getDataSource());
            id = qr.update("delete from clazz where cid=?", cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return id;
    }

    @Override
    public int updateClazz(Clazz clazz) {
        int count = 0;
        try {
            QueryRunner qr=new QueryRunner(DBUtils.getDataSource());
            String sql="update clazz set cname=?,cteacher=?,remark=? where cid=?";
            count = qr.update(sql, clazz.getCname(), clazz.getCteacher(), clazz.getRemark(), clazz.getCid());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    @Override
    public List<Count> getCountByClazz() {

        List<Count> list = null;
        try {
            QueryRunner qr=new QueryRunner(DBUtils.getDataSource());
            String sql="select cname,count(*) count from clazz,student where clazz.cid=student.cid group by cname";

            list = qr.query(sql, new BeanListHandler<>(Count.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;

    }

    @Override
    public List<Clazz> getAll() {
        List<Clazz> list = null;
        try {
            QueryRunner qr=new QueryRunner(DBUtils.getDataSource());
            list = qr.query("select * from clazz", new BeanListHandler<>(Clazz.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
