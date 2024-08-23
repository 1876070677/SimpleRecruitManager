<%@ page import="java.util.ArrayList" %>
<%@ page import="DTO.Recruit" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        th, td {
            border: 1px solid;
        }
    </style>
</head>
<body style="width: 50%">
<%
    String keyword = (String) request.getAttribute("keyword");
    String url = null;
    if (keyword != null)
        url = "/list?keyword=" + keyword + "&";
    else
        url = "/list?";
%>
<button type="button" onclick="location.href='/'">Home</button>
<button type="button" onclick="location.href='<%=url%>opt=company'">회사명 기준 정렬</button>
<button type="button" onclick="location.href='<%=url%>opt=money'">봉급 기준 정렬</button>
<button type="button" onclick="location.href='/list'">초기화</button>
<table style="border: 1px solid; border-collapse: collapse; width: 100%">
    <tr style="background-color: gray">
        <th style="width: 25%">회사</th>
        <th style="width: 20%">모집 직무</th>
        <th style="width: 15%">봉급</th>
        <th style="width: 10%">지역</th>
        <th style="width: 30%">전화번호</th>
    </tr>
    <%
        ArrayList<Recruit> list = (ArrayList<Recruit>) request.getAttribute("list");
        if (list != null) {
            for (Recruit r : list) {
    %>
    <tr>
        <th><%=r.getCompany()%></th>
        <th><%=r.getJob()%></th>
        <th><%=r.getMoney()%></th>
        <th><%=r.getRegion()%></th>
        <th><%=r.getPhone()%></th>
    </tr>
    <%
            }
        }
    %>
</table>

<form name="form" action="/list" method="get">
    검색: <input type="text" name="keyword" maxlength="24">
    <input type="submit" value="Search" />
</form>
</body>
</html>
