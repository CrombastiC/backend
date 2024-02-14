package com.dletc.dao;

import com.dletc.domain.Clazz;
import com.dletc.domain.Count;

import java.util.List;

public interface ClazzDao {

    //增加班级信息
    int addClazz(Clazz clazz);

    //分页查询数据
    //参数1：当前页码
    //参数2：每页显示的条数
    List<Clazz> getPageClazz(int pageNum, int pageSize);

    //查询总记录数
    long getCount();

    //根据id删除班级信息
    int deleteClazzById(int cid);

    //根据id修改数据
    int updateClazz(Clazz clazz);

    //查询班级人数
    List<Count> getCountByClazz();

    //查询表中所有的数据
    List<Clazz> getAll();
}
