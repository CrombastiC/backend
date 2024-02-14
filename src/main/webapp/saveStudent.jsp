<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>添加学生</title>
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>
    <script src="js/axios-0.18.0.js"></script>
    <script>
        window.onload=function (){
            axios.get("http://localhost:8080/clazz?method=findAllClazz").then(function (res){
                console.log(res.data)
                let list=res.data

                let select=document.querySelector("select")

                for (let i=0;i<list.length;i++){
                    let option=document.createElement("option")
                    option.innerText=list[i].cname
                    option.value=list[i].cid
                    select.appendChild(option)
                }
            })

            //获取全部表单数据
            let btn=document.querySelector("#btn")
            btn.addEventListener('click',function (){
                let data=$('form').serialize()
                //使用axios发送ajax请求
                axios.post("http://localhost:8080/student?method=addStudent",data).then(function (res){
                    console.log(res.data)
                    if (res.data==true){
                        alert("添加成功")

                    }else {
                        alert("添加失败")
                    }
                })
            })
        }
    </script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head"><strong><span class="icon-pencil-square-o"></span>添加学生</strong></div>
    <div class="body-content">
        <form method="post" class="form-x" action="javascript:return false">
            <div class="form-group">
                <div class="label">
                    <label>姓名：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="sname" value=""  style="width:30%"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>性别：</label>
                </div>
                <div class="field" style="padding-top:8px;">
                    男:<input type="radio"  name="sex" value="" />
                    女: <input type="radio"  name="sex" value="" />
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>爱好：</label>
                </div>
                <div class="field" style="padding-top:8px;">
                    抽烟 <input   type="checkbox" value="hobbies"/>
                    喝酒 <input   type="checkbox" value="hobbies"/>
                    烫头 <input   type="checkbox" value="hobbies"/>
                </div>
            </div>


            <div class="form-group">
                <div class="label">
                    <label>出生年月：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="birthdy" value=""  style="width:30%"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>手机号：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="phone" value="" style="width:30%"/>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>所在班级：</label>
                </div>
                <div class="field">

                    <select class="input" style="width:30%">
                        <option>--请选择--</option>
<%--                        <option>java117班</option>--%>
<%--                        <option>java118班</option>--%>
<%--                        <option>java119班</option>--%>
                    </select>

                </div>
            </div>


            <div class="form-group">
                <div class="label">
                    <label>描述：</label>
                </div>
                <div class="field">
                    <textarea type="text" class="input" name="s_desc" style="height:80px;"></textarea>
                </div>
            </div>


            <div class="form-group">
                <div class="label">
                    <label></label>
                </div>
                <div class="field">
                    <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body></html>