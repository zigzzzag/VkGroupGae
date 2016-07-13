<%@ page import="java.util.Set" %>
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
    <% String dateStr = (String) request.getAttribute("date");%>
    Дата: <INPUT type="date" name="date" value='<%=dateStr%>'/>
    Группа: <select name="groupId">
            <%
                Set<String> groupIds = (Set<String>) request.getAttribute("groupIds");
                for (String groupId : groupIds) {
            %>
                <option><%=groupId%></option>
            <%
                }
            %>
            </select>
    <INPUT type="submit" name="getReport" value="GO"/>
</form>

<H3>Добавились :)</H3>
<%
    Set<Integer> addedIds = (Set<Integer>) request.getAttribute("addedIds");
    if (addedIds != null && !addedIds.isEmpty()) {
        for (Integer addedId : addedIds) {
%>
<a href="https://new.vk.com/id<%=addedId%>"><%=addedId%>
</a>
<br>
<%
        }
    } else {
%>
<br>Никто не пришел, не пошуметь блеять :(<br>
<%
    }
%>

<br><br><br>
<H3>Ушли :(</H3>
<%
    Set<Integer> deletedIds = (Set<Integer>) request.getAttribute("deletedIds");
    if (deletedIds != null && !deletedIds.isEmpty()) {
        for (Integer deletedId : deletedIds) {
%>
<a href="https://new.vk.com/id<%=deletedId%>"><%=deletedId%>
</a>
<br>
<%
        }
    } else {
%>
<br>Никто не ушел, бро :)<br>
<%
    }
%>

</body>
</html>
