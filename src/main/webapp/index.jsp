<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
  <meta name="renderer" content="webkit">
  <title>学生后台管理中心</title>
  <link rel="stylesheet" href="css/pintuer.css">
  <link rel="stylesheet" href="css/admin.css">
  <script src="js/jquery.js"></script>
</head>
<body style="background-color:#f2f9fd;">
<div class="header bg-main">
  <div class="logo margin-big-left fadein-top">
    <h1 style="float:left"><img src="images/y.jpg" class="radius-circle rotate-hover" height="50" alt="" />学生后台管理中心</h1>
  </div>
</div>
<div class="leftnav">
  <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
  <h2><span class="icon-user"></span>班级管理</h2>
  <ul style="display:block">
    <li><a href="saveClazz.jsp" target="right"><span class="icon-caret-right"></span>添加班级</a></li>
    <li><a href="listClazz.jsp" target="right"><span class="icon-caret-right"></span>展示班级</a></li>
    <li><a href="clazzScoreSimple.jsp" target="right"><span class="icon-caret-right"></span>各班人数</a></li>
  </ul>
  <h2><span class="icon-user"></span>学生管理</h2>
  <ul style="display:block">
    <li><a href="saveStudent.jsp" target="right"><span class="icon-caret-right"></span>添加学生</a></li>
    <li><a href="listStudent.jsp" target="right"><span class="icon-caret-right"></span>展示学生</a></li>
  </ul>



</div>
<script type="text/javascript">
  $(function(){
    $(".leftnav h2").click(function(){
      $(this).next().slideToggle(200);
      $(this).toggleClass("on");
    })
    $(".leftnav ul li a").click(function(){
      $("#a_leader_txt").text($(this).text());
      $(".leftnav ul li a").removeClass("on");
      $(this).addClass("on");
    })
  });
</script>
<ul class="bread">
  <li><a href="{:U('Index/info')}" target="right" class="icon-home"> 首页</a></li>
  <li><a href="##" id="a_leader_txt">网站信息</a></li>
  <li><b>当前语言：</b><span style="color:red;">中文</php></span>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;切换语言：<a href="##">中文</a> &nbsp;&nbsp;<a href="##">英文</a> </li>
</ul>
<div class="admin">
  <iframe scrolling="auto" rameborder="0" src="saveClazz.jsp" name="right" width="100%" height="100%"></iframe>
</div>
<div style="text-align:center;">
</div>
</body>
</html>
