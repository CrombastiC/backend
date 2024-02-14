package com.dletc.controller;

import com.alibaba.fastjson.JSON;
import com.dletc.domain.Clazz;
import com.dletc.domain.PageBean;
import com.dletc.service.ClazzService;
import com.dletc.service.impl.ClazzServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

//继承BaseServlet父类
@WebServlet("/clazz")
public class ClazzServlet extends BaseServlet {
    //需要连接service层与dao层

    ClazzService clazzService = new ClazzServiceImpl();

    //定义了一个增加班级信息的方法，替代了AddServlet
    protected void addClazz(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决请求乱码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        //获取请求参数
        BufferedReader reader = req.getReader();
        String clazz_json = reader.readLine();
        System.out.println(clazz_json);
        //把json字符串转换成对象
        Clazz clazz = JSON.parseObject(clazz_json, Clazz.class);
        System.out.println(clazz);
        //把clazz对象发给service层
        int count = clazzService.insertClazz(clazz);
        System.out.println(count);
        //把结果发送到前端
        resp.getWriter().write(count == 1 ? "true" : "false");
    }

    //创建一个分页方法

    protected void pageClazz(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决请求乱码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        //获取请求参数
        String pageNum = req.getParameter("pageNum");
        //调用service层的方法
        PageBean<Clazz> pageBean = clazzService.getClazzByPage(Integer.parseInt(pageNum), 5);
        //把pageBean对象转换成json字符串
        String pageBean_json = new Gson().toJson(pageBean);
        //把json字符串发给前端
        resp.getWriter().write(pageBean_json);
    }

    //删除的方法
    protected void delClazz(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决请求乱码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        //获取请求参数
        String cid = req.getParameter("cid");
        //调用service层的方法
        int count = clazzService.removeClazzById(Integer.parseInt(cid));
        //把结果发送到前端
        resp.getWriter().write(count == 1 ? "1" : "0");
    }

    //修改的方法
    protected void updateClazz(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决请求乱码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        //获取请求参数
        BufferedReader reader = req.getReader();
        String clazz_json = reader.readLine();
        //把json字符串转换成对象
        Clazz clazz = JSON.parseObject(clazz_json, Clazz.class);
        //调用service层的方法
        int count = clazzService.modifyClazz(clazz);
        //把结果发送到前端
        resp.getWriter().write(count == 1 ? "1" : "0");
    }

    //查询班级人数的方法
    protected void findClazzCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决请求乱码写在了base里
        //调用service层的方法
        String count_json = new Gson().toJson(clazzService.findClazzCount());
        //把结果发送到前端
        resp.getWriter().write(count_json);
    }

    //查询所有班级信息的方法
    protected void findAllClazz(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决请求乱码写在了base里
        //调用service层的方法
        String clazz_json = new Gson().toJson(clazzService.findAll());
        //把结果发送到前端
        resp.getWriter().write(clazz_json);
    }
}
