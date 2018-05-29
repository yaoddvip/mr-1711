<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/7
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>导入</title>

    <script src="<%=path%>/js/jquery/jquery-1.11.3.min.js"></script>

    <script>
        //在表单提交之前，判断文件是否为excel文件，
        // 当使用文件上传参数的时候，直接限定文件上传的类型
        function test(){
            var fileName = $("#fileId").val();
            //indexOf(str,starIndex)方法,返回指定字符串第一次出现的位置.
            // 参数str表示目标字符,startIndex代表开始查找的下标,如果没有目标字符,则返回-1
            alert(fileName);
            if(fileName.indexOf(".xlsx")<0||fileName.indexOf(".xls")<0){//不是excel文件
                alert("必须传入excel文件");
                return false;
            }else{
                return true;
            }
        }
    </script>
</head>
<body>
    <form method="post" onsubmit="return test()"
          action="<%=path%>/poi/importPoi.do" enctype="multipart/form-data">
        <input id="fileId"  type="file" name="xxx"/>
        <input type="submit"  value="上传"/>
    </form>
</body>
</html>
