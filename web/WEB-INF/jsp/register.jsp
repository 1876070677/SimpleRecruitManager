<%--
  Created by IntelliJ IDEA.
  User: JIWON
  Date: 2024-08-23
  Time: 오전 12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form name="form" action="/register" method="post">
        회사명: <input type="text" name="company"><br>
        모집 직무: <input type="text" name="job"><br>
        봉급: <input type="text" name="money"><br>
        지역: <input type="text" name="region"><br>
        회사 전화번호: <input type="text" name="phone"><br>
        <input type="submit" value="제출"><br>
    </form>
</body>
</html>
