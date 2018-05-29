<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/7
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>poi</title>
</head>
<body>
    <h1>poi</h1>
    <a href="<%=path%>/poi/exportPoi.do">导出</a>
    <br>

    <c:forEach items="${list}" var="user">
        ${user}
        <br>
    </c:forEach>


</body>
</html>
