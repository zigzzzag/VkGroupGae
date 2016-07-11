<%--
  Created by IntelliJ IDEA.
  User: Zigzag
  Date: 09.07.2016
  Time: 1:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1><a href="/">ГРУПС ФОР ДОДО</a></h1>

<form action="/groupReport" method="get">
    Дата: <INPUT type="date" name="date"/>
    Группа: <select name="groupId"><option>1</option><option>2</option> </select>
    <INPUT type="submit" name="getReport" value="GO"/>
</form>

</body>
</html>
