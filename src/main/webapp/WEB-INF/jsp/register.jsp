<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String err = (String) request.getAttribute("err");
    if (err != null) {
%>
    <div style="color: darkred;"><%=err%></div><br>
<%
    }
%>
    <form name="form" action="/register" method="post">
        회사명: <input type="text" name="company" maxlength="24"><br>
        모집 직무: <input type="text" name="job" maxlength="24"><br>
        봉급: <input type="number" name="money" maxlength="9"><br>
        지역: <input type="text" name="region" maxlength="10"><br>
        회사 전화번호: <input type="number" name="phone" maxlength="11"><br>
        <input type="submit" value="제출"><br>
    </form>
</body>
</html>
