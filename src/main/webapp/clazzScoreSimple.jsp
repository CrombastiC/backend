<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--
THIS EXAMPLE WAS DOWNLOADED FROM https://echarts.apache.org/examples/zh/editor.html?c=bar-simple
-->
<!DOCTYPE html>
<html style="height: 100%">
<head>
    <meta charset="utf-8">
</head>
<body style="height: 100%; margin: 0">
<div id="main" style="height: 100%"></div>

<script src="js/jquery.js"></script>
<script src="js/echarts.min.js"></script>
<script src="./js/axios-0.18.0.js"></script>
<script>

    window.onload=()=>{
        //发送axios请求
        axios.get("http://localhost:8080/clazz?method=findClazzCount").then(function (res){
            console.log(res.data)
            let arr=res.data

            //定义两个数组一个存放名字，一个存放人数
            let arr1 = [];
            let arr2 = [];

            //遍历数组，把班级名称和人数分别存放到arr1 与arr2里
            for (let i = 0; i < arr.length; i++) {
                arr1[i] = arr[i].cname;
                arr2[i] =arr[i].count;

            }


            var chartDom = document.getElementById('main');
            var myChart = echarts.init(chartDom);
            var option;

            option = {
                xAxis: {
                    type: 'category',
                    data: arr1
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        data: arr2,
                        type: 'line'
                    }
                ]
            };

            option && myChart.setOption(option);




        })
    }

</script>
</body>
</html>
