package com.dletc.controller;

import com.alibaba.fastjson.JSON;
import com.dletc.domain.User;
import com.dletc.service.UserService;
import com.dletc.service.impl.UserServiceImpl;

import java.io.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//创建一个接收登录页面的servlet
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //接收地址栏中的数据
        String code = request.getParameter("code");
        //接收json字符串中的数据
        BufferedReader reader = request.getReader();
        String user_json = reader.readLine();
        User user = JSON.parseObject(user_json, User.class);
        //测试是否获取到
        System.out.println("code = " + code);
        System.out.println("user_json = " + user_json);
        //获取服务中session中的验证码
        HttpSession session = request.getSession();
        String randStr = (String) session.getAttribute("randStr");
        //校验验证码
        if (code != null && code.equals(randStr)) {
            //调用service的登录方法，传递用户名和密码
            UserService userService = new UserServiceImpl();
            User u=userService.login(user.getUkey(),user.getPwd());
            //判断是否登录成功
            if (u != null) {
                //登录成功
                //将用户信息存储到session中
                session.setAttribute("user", u);
                //返回的是一个自定义的状态码
                //1001:登录成功
                //1002：用户名 或密码错误
                //1003：验证码错误
                response.getWriter().write("1001");
            } else {
                //用户名或密码错误
                //返回的是一个自定义的状态码
                //1001:登录成功
                //1002：用户名 或密码错误
                //1003：验证码错误
                response.getWriter().write("1002");
            }
        } else {
            //验证码错误
            //返回的是一个自定义的状态码
            //1001:登录成功
            //1002：用户名 或密码错误
            //1003：验证码错误
            response.getWriter().write("1003");
        }
    }
}