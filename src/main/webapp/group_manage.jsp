<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <meta charset="utf-8">
    <title>ГРУПС</title>
    <SCRIPT language="javascript">
        function addRow(tableID) {
            var table = document.getElementById(tableID);
            var rowCount = table.rows.length;
            var row = table.insertRow(rowCount);
            var colCount = table.rows[0].cells.length;

            for (var i = 0; i < colCount; i++) {
                var newcell = row.insertCell(i);

                newcell.innerHTML = table.rows[0].cells[i].innerHTML;
                //alert(newcell.childNodes);
                switch (newcell.childNodes[0].type) {
                    case "text":
                        newcell.childNodes[0].value = "";
                        break;
                    case "checkbox":
                        newcell.childNodes[0].checked = false;
                        break;
                    case "select-one":
                        newcell.childNodes[0].selectedIndex = 0;
                        break;
                }
            }
        }

        function deleteRow(tableID) {
            try {
                var table = document.getElementById(tableID);
                var rowCount = table.rows.length;

                for (var i = 0; i < rowCount; i++) {
                    var row = table.rows[i];
                    var chkbox = row.cells[0].childNodes[0];
                    if (null != chkbox && true == chkbox.checked) {
                        if (rowCount <= 1) {
                            alert("Cannot delete all the rows.");
                            break;
                        }
                        table.deleteRow(i);
                        rowCount--;
                        i--;
                    }
                }
            } catch (e) {
                alert(e);
            }
        }
    </SCRIPT>
</head>
<body>
<h1>ГРУПС ФОР ДОДО</h1>
<p>Сервис запускает сканирование составов указанных групп раз в 10 минут</p>

<INPUT type="button" value="Добавить группу" onclick="addRow('dataTable')"/>
<INPUT type="button" value="Удалить" onclick="deleteRow('dataTable')"/>

<form action="/groupMembers" method="get">
    <TABLE id="dataTable" width="350px" border="1">
        <TR>
            <TD><INPUT type="checkbox" name="chk"/></TD>
            <TD><INPUT type="text" name="groupId"/></TD>
            <TD><INPUT type="submit" value="Добавить в список"/></TD>
        </TR>
    </TABLE>
</form>

<h1>"vkGroups" ${vkGroups}</h1>

<c:forEach items="${vkGroups}" var="item">
    ${item}<br>
</c:forEach>

</body>
</html>
