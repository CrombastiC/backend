<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="renderer" content="webkit">
    <title></title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>
    <script src="js/axios-0.18.0.js"></script>
    <script>
        window.onload = () => {
            //定义三个全局变量
            let pageNum = 1//设置起始页
            let pageCount = 0//设置总页数
            let totalSize = 0//设置总记录数
            //页面加载完成后，发送ajax请求，获取数据

            //创建分页查询的函数
            function getData(){
                axios.get("http://localhost:8080/clazz?method=pageClazz&pageNum=" + pageNum).then((res) => {
                    //测试
                    console.log(res.data)
                    pageNum = res.data.pageNum
                    pageCount = res.data.pageCount
                    totalSize = res.data.totalSize
                    let list = res.data.data
                    console.log(list)
                    //获取上一页与下一页的节点
                    let lastRow = document.querySelector("#lastRow")
                    //遍历数组
                    for (let i = 0; i < list.length; i++) {
                        let tr = document.createElement("tr")
                        //添加清除的类名
                        tr.className='del';
                        tr.innerHTML = `
                        <td>\${list[i].cid}</td>
                        <td>\${list[i].cname}</td>
                        <td>\${list[i].cteacher}</td>
                        <td>\${list[i].remark}</td>
                        <td>
                        <div class="button-group">
                        <a class="button border-main" cid="\${list[i].cid}" id="update_btn" href="updateeClazz.jsp?cid=\${list[i].cid}&cname=\${list[i].cname}&cteacher=\${list[i].cteacher}&remark=\${list[i].remark}">
                        <span class="icon-edit"></span> 修改
                        </a>
                         <a class="button border-red" cid="\${list[i].cid}" id="delete_btn"  href="javascript:void(0)" >
                        <span class="icon-trash-o"></span> 删除
                        </a>
                        </div>
                        </td>
                        `

                        //将tr节点添加到页面中
                        //把新节点插入到指定节点之前
                        lastRow.parentNode.insertBefore(tr, lastRow)
                    }

                    //1.2.3.4按钮的遍历
                    for (let i=0;i<pageCount;i++){
                        //创建a标签
                        let a= document.createElement("a")
                        //为a标签添加类名
                        a.className='pageA del'
                        //为a标签添加文本
                        a.innerText=i+1
                        //将a标签插入到页面中
                      let nextPage = document.querySelector("#nextPage")
                        nextPage.parentNode.insertBefore(a,nextPage)    //把新节点插入到指定节点之前

                    }

                    //删除指定cid的班级
                    let delete_arr = document.querySelectorAll("#delete_btn")
                    //遍历数组
                    for (let i = 0; i < delete_arr.length; i++) {
                        delete_arr[i].addEventListener('click', function() {
                            //获取当前点击的a标签的cid
                           let cid=this.getAttribute("cid");
                           //添加删除提示
                            let flag=confirm("您确定要删除吗？")

                            if(!flag){
                                return;//结束函数
                            }

                            //发送ajax请求
                            axios.get("http://localhost:8080/clazz?method=delClazz&cid=" + cid).then((res) => {
                                //测试
                                console.log(res.data)
                                if (res.data == 1) {
                                    alert("删除成功")
                                    //刷新页面
                                    pageNum=1;
                                    getData();
                                    window.location.reload()

                                } else {
                                    alert("删除失败");
                                }
                            })
                        })
                    }
                })
            }
            //调用查询方法
            getData()

            //下一页功能
            let nextPage = document.querySelector("#nextPage")
            nextPage.addEventListener('click',()=>{
                //判断是否是最后一页
                if(pageNum+1> pageCount){
                    alert("已经是最后一页了")
                    return//结束函数
                }else {
                    pageNum++
                    //清空表格
                    let del = document.querySelectorAll(".del")
                    for (let i = 0; i < del.length; i++) {
                        del[i].remove();//删除节点
                    }
                    //调用查询方法
                    getData()
                }
            })

            //上一页功能
            let prePage = document.querySelector("#prePage")
            prePage.addEventListener('click',()=>{
                //判断是否是第一页
                if(pageNum-1<1){
                    alert("已经是第一页了")
                    return//结束函数
                }else {
                    pageNum--
                    //清空表格
                    let del = document.querySelectorAll(".del")
                    for (let i = 0; i < del.length; i++) {
                        del[i].remove();//删除节点
                    }
                    //调用查询方法
                    getData()
                }
            })
            //实现点击页码跳转到指定页
            let pageList = document.querySelector(".pagelist")
            pageList.addEventListener('click',(e)=>{
                //获取当前页码
                pageNum=e.target.innerText
                //清空表格
                let del = document.querySelectorAll(".del")
                for (let i = 0; i < del.length; i++) {
                    del[i].remove();//删除节点
                }
                //调用查询方法
                getData()
            })


        }

    </script>
</head>
<body>
<form method="post" action="" id="listform">
    <div class="panel admin-panel">
        <div class="panel-head"><strong class="icon-reorder"> 班级列表</strong> <a href=""
                                                                                   style="float:right; display:none;">添加字段</a>
        </div>
        <table class="table table-hover text-center">
            <tr id="firstRow">
                <th width="100" style="text-align:left; padding-left:20px;">ID</th>
                <th width="10%">班级名称</th>
                <th>班主任</th>
                <th>备注</th>
            </tr>

            <tr id="lastRow">
                <td colspan="8">
                    <div class="pagelist">
                        <a href="javascript:;" id="prePage">上一页</a>
                        <a href="javascript:;" id="nextPage">下一页</a>
                    </div>
                </td>
            </tr>
        </table>
    </div>
</form>
<script type="text/javascript">

    //搜索
    function changesearch() {

    }

    //单个删除
    function del(id, mid, iscid) {
        if (confirm("您确定要删除吗?")) {

        }
    }

    //全选
    $("#checkall").click(function () {
        $("input[name='id[]']").each(function () {
            if (this.checked) {
                this.checked = false;
            } else {
                this.checked = true;
            }
        });
    })

    //批量删除
    function DelSelect() {
        var Checkbox = false;
        $("input[name='id[]']").each(function () {
            if (this.checked == true) {
                Checkbox = true;
            }
        });
        if (Checkbox) {
            var t = confirm("您确认要删除选中的内容吗？");
            if (t == false) return false;
            $("#listform").submit();
        } else {
            alert("请选择您要删除的内容!");
            return false;
        }
    }

    //批量排序
    function sorts() {
        var Checkbox = false;
        $("input[name='id[]']").each(function () {
            if (this.checked == true) {
                Checkbox = true;
            }
        });
        if (Checkbox) {

            $("#listform").submit();
        } else {
            alert("请选择要操作的内容!");
            return false;
        }
    }


    //批量首页显示
    function changeishome(o) {
        var Checkbox = false;
        $("input[name='id[]']").each(function () {
            if (this.checked == true) {
                Checkbox = true;
            }
        });
        if (Checkbox) {

            $("#listform").submit();
        } else {
            alert("请选择要操作的内容!");

            return false;
        }
    }

    //批量推荐
    function changeisvouch(o) {
        var Checkbox = false;
        $("input[name='id[]']").each(function () {
            if (this.checked == true) {
                Checkbox = true;
            }
        });
        if (Checkbox) {


            $("#listform").submit();
        } else {
            alert("请选择要操作的内容!");

            return false;
        }
    }

    //批量置顶
    function changeistop(o) {
        var Checkbox = false;
        $("input[name='id[]']").each(function () {
            if (this.checked == true) {
                Checkbox = true;
            }
        });
        if (Checkbox) {

            $("#listform").submit();
        } else {
            alert("请选择要操作的内容!");

            return false;
        }
    }


    //批量移动
    function changecate(o) {
        var Checkbox = false;
        $("input[name='id[]']").each(function () {
            if (this.checked == true) {
                Checkbox = true;
            }
        });
        if (Checkbox) {

            $("#listform").submit();
        } else {
            alert("请选择要操作的内容!");

            return false;
        }
    }

    //批量复制
    function changecopy(o) {
        var Checkbox = false;
        $("input[name='id[]']").each(function () {
            if (this.checked == true) {
                Checkbox = true;
            }
        });
        if (Checkbox) {
            var i = 0;
            $("input[name='id[]']").each(function () {
                if (this.checked == true) {
                    i++;
                }
            });
            if (i > 1) {
                alert("只能选择一条信息!");
                $(o).find("option:first").prop("selected", "selected");
            } else {

                $("#listform").submit();
            }
        } else {
            alert("请选择要复制的内容!");
            $(o).find("option:first").prop("selected", "selected");
            return false;
        }
    }

</script>
</body>
</html>