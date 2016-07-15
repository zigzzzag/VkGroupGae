<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>ГРУПС</title>
</head>
<body>
<h1><a href="/">ГРУПС ФОР ДОДО</a></h1>

<form action="/refreshData">
    <button type="submit">Обновить состояние</button>
    Состояние актуально
    на: <%=request.getAttribute("actualDate") == null ? "Вообще не актуально" : request.getAttribute("actualDate")%>
</form>

<a href="/groupMembers">Управление группами</a>
<br>
<a href="/groupReport">Отчет по группам</a>

</body>
</html>
