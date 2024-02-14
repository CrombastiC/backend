<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="renderer" content="webkit">
    <title>添加班级</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>
    <script src="js/axios-0.18.0.js"></script>
    <script>

        //页面加载事件
        window.onload = () => {
            //获取元素
            let cname = document.querySelector("#cname");
            let cteacher = document.querySelector("#cteacher");
            let remark = document.querySelector("#remark");
            let btn = document.querySelector("#btn");

            //绑定单击事件
            btn.addEventListener('click', () => {
                //获取元素的值
                let clazz = {
                    cname: cname.value,
                    cteacher: cteacher.value,
                    remark: remark.value
                }

                //判断是否为空
                if (clazz.cname == "" || clazz.cteacher == "") {
                    alert("班级名称或班主任不能为空");
                    return;
                }
                //发送ajax请求
                axios.post("http://localhost:8080/clazz?method=addClazz", clazz).then((res) => {
                    console.log(res.data);
                    //判断是否添加成功
                    alert(res.data == 1 ? "添加成功" : "添加失败");
                }).catch((err) => {

                    alert("添加失败");
                })

            })

        }

    </script>

</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head"><strong><span class="icon-pencil-square-o"></span>添加班级</strong></div>
    <div class="body-content">
        <form method="post" class="form-x" action="javascript:return false">
            <div class="form-group">
                <div class="label">
                    <label>班级名称：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="cname" id="cname" value="" style="width:30%"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>班主任：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="cteacher" id="cteacher" value="" style="width:30%"/>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>备注：</label>
                </div>
                <div class="field">
                    <textarea type="text" class="input" name="remark" id="remark" style="height:80px;"></textarea>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label></label>
                </div>
                <div class="field">
                    <button class="button bg-main icon-check-square-o" id="btn" type="submit"> 提交</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>