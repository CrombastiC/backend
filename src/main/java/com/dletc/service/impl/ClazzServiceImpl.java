package com.dletc.service.impl;

import com.dletc.dao.ClazzDao;
import com.dletc.dao.impl.ClazzDaoImpl;
import com.dletc.domain.Clazz;
import com.dletc.domain.Count;
import com.dletc.domain.PageBean;
import com.dletc.service.ClazzService;

import java.util.List;

public class ClazzServiceImpl implements ClazzService {
    //需要连接dao层
    ClazzDao clazzDao = new ClazzDaoImpl();
    //增加班级信息的业务方法
    @Override
    public int insertClazz(Clazz clazz) {
        return clazzDao.addClazz(clazz);

    }

    @Override
    public PageBean<Clazz> getClazzByPage(int pageNum, int pageSize) {
        //调用分页查询的方法查询记录
        List<Clazz> list = clazzDao.getPageClazz(pageNum, pageSize);
        //调用查询总记录数的方法
        long totalSize = clazzDao.getCount();
        //计算总页数
        //把集合+记录数+当前页码+每条页数 封装到pageBean中
        PageBean<Clazz> pageBean = new PageBean<>(pageNum, pageSize, totalSize, list);

        return pageBean;
    }

    @Override
    public int removeClazzById(int cid) {

        return clazzDao.deleteClazzById(cid);
    }

    @Override
    public int modifyClazz(Clazz clazz) {
        return clazzDao.updateClazz(clazz);
    }

    @Override
    public List<Count> findClazzCount() {
        return clazzDao.getCountByClazz();
    }

    @Override
    public List<Clazz> findAll() {
        return clazzDao.getAll();
    }


}
