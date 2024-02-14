package com.dletc.controller;

import com.dletc.domain.Student;
import com.dletc.service.StudentService;
import com.dletc.service.impl.StudentServiceImpl;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/student")
public class StudentServlet extends BaseServlet {
    protected void addStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        //姓名
        String sname = req.getParameter("sname");
        //性别
        String sex = req.getParameter("sex");

        //多选
        String[] hobbies = req.getParameterValues("hobby");

        //将爱好的数组转换成字符串
        String hobby = String.join(",", hobbies);
        System.out.println(hobby);

        Date birthdate = null;
        //获取出生年月
        try {
            String date = req.getParameter("birthdate");
            //把日期转换为date格式
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            birthdate=sdf.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        //获取手机号
        String phone = req.getParameter("phone");
        //获取cid
        String cid=req.getParameter("cid");

        //获取备注
        String remark = req.getParameter("remark");
        //封装为学生对象，发送给service
         Student stu=   new Student(null,sname,sex,hobby,birthdate,phone,remark,Integer.parseInt(cid),null);

        //9 调用StudentService层,的insertStudent(Student stu)方法
        StudentService studentService = new StudentServiceImpl();
        studentService.insertStudent(stu);

        //10 返回结果到前端
        resp.getWriter().write("true");

    }




}