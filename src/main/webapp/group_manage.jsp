<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <meta charset="utf-8">
    <title>ГРУПС</title>
</head>
<body>
<h1><a href="/">ГРУПС ФОР ДОДО</a></h1>
<p>Сервис запускает сканирование составов указанных групп раз в 10 минут</p>


<form action="/unloadGroupData" method="get">
    Group Id: <INPUT type="text" name="groupId"/>
    <INPUT type="submit" name="addGroup" value="Получить всех кентов"/>
</form>

<br><br>

<form action="/groupMembers" method="get">
    Group Id: <INPUT type="text" name="groupId"/>
    <INPUT type="submit" name="addGroup" value="Добавить в список"/>
    <INPUT type="submit" name="deleteGroup" value="Удалить группу"/>
</form>


Список групп(GroupId), по которым идет ежедневная проверка на пришедших/вышедших:<br>
<c:forEach items="${vkGroups}" var="item">
    ${item}<br>
</c:forEach>

</body>
</html>
