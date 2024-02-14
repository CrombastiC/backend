<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="renderer" content="webkit">
    <title>登录</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>
    <%--    导入axios--%>
    <script src="./js/axios-0.18.0.js"></script>
    <script>
        //页面加载事件
        window.onload = () => {


            //获取元素
            let btn = document.querySelector("#btn");
            let ukey = document.querySelector("#ukey");
            let pwd = document.querySelector("#pwd");
            let code = document.querySelector("#code");
            //绑定点击事件
            btn.addEventListener('click', (e) => {
                // e.preventDefault();
                //封装对象
                let user = {
                    ukey: ukey.value,
                    pwd: pwd.value,

                }
                console.log(user)
                //发送请求
                //post的方法负责发送请求和传输数据 then负责接收响应
                //res.data 代表服务器返回的数据
                axios.post("http://localhost:8080/login?code=" + code.value, user).then((res) => {
                    //依据状态码返回不同的信息
                    if (res.data == "1003") {
                        alert("验证码错误")
                    } else if (res.data == "1002") {
                        alert("账号或密码错误")

                    } else {
                        //跳转页面
                        window.location.href = "index.jsp";
                    }
                })
            })
        }

    </script>
</head>
<body>
<div class="bg"></div>
<div class="container">
    <div class="line bouncein">
        <div class="xs6 xm4 xs3-move xm4-move">
            <div style="height:150px;"></div>
            <div class="media media-y margin-big-bottom">
            </div>
            <form action="javascript:return false" method="post">
                <div class="panel loginbox">
                    <div class="text-center margin-big padding-big-top"><h1>后台管理中心</h1></div>
                    <div class="panel-body" style="padding:30px; padding-bottom:10px; padding-top:10px;">
                        <div class="form-group">
                            <div class="field field-icon-right">
                                <input type="text" class="input input-big" name="ukey" id="ukey" placeholder="登录账号"
                                       data-validate="required:请填写账号"/>
                                <span class="icon icon-user margin-small"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="field field-icon-right">
                                <input type="password" class="input input-big" name="pwd" id="pwd"
                                       placeholder="登录密码" data-validate="required:请填写密码"/>
                                <span class="icon icon-key margin-small"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="field">
                                <input type="text" class="input input-big" id="code" name="code"
                                       placeholder="填写右侧的验证码" data-validate="required:请填写右侧的验证码"/>
                                <img src="RandomServlet" alt="" width="100" height="32" class="passcode"
                                     style="height:43px;cursor:pointer;" onclick="this.src=this.src+'?'">

                            </div>
                        </div>
                    </div>
                    <div style="padding:30px;">
                        <input type="submit" id="btn" class="button button-block bg-main text-big input-big"
                               value="登录">
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
