package com.dletc.service;

import com.dletc.domain.Clazz;
import com.dletc.domain.Count;
import com.dletc.domain.PageBean;

import java.util.List;

//班级表的业务层
public interface ClazzService {
    //增加班级信息
    int insertClazz(Clazz clazz);
    //分页查询数据，将分页查询的数据与页码的信息组装到一起 ，发给controller层
    //参数1：当前页码
    //参数2：每页显示的条数
    //返回值：封装了分页查询的数据与页码的信息
    PageBean<Clazz> getClazzByPage(int pageNum, int pageSize);

    //根据id删除班级信息
    int removeClazzById(int cid);

    //根据id修改数据
    int modifyClazz(Clazz clazz);

    //查询班级人数
    List<Count> findClazzCount();

    //查询表中所有的数据
    List<Clazz> findAll();
}
