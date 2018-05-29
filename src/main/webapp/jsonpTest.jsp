<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/10
  Time: 9:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>Title</title>

    <script src="<%=path%>/js/jquery/jquery-1.11.3.min.js"></script>
    <script>
        function testJsonp(){
            $.ajax({
                url:"http://localhost:8080/jsonp/testJsonp.do",
                type:"get",
                dataType:"json",
                success:function(data){
                    console.log(data);
                }
            });
        }

    </script>
</head>
<body>

    <input type="button"  value="点击" onclick="testJsonp()"/>
</body>
</html>
