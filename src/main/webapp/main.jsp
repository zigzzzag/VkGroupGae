<%--
  Created by IntelliJ IDEA.
  User: sbt-nikiforov-mo
  Date: 30.06.16
  Time: 18:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
<h1><%--ГРУПС ФОР ДОДО--%></h1>

<INPUT type="button" value="Add Row" onclick="addRow('dataTable')"/>

<INPUT type="button" value="Delete Row" onclick="deleteRow('dataTable')"/>

<TABLE id="dataTable" width="350px" border="1">
    <TR>
        <TD><INPUT type="checkbox" name="chk"/></TD>
        <TD><INPUT type="text" name="txt"/></TD>
        <TD>
            <SELECT name="country">
                <OPTION value="in">India</OPTION>
                <OPTION value="de">Germany</OPTION>
                <OPTION value="fr">France</OPTION>
                <OPTION value="us">United States</OPTION>
                <OPTION value="ch">Switzerland</OPTION>
            </SELECT>
        </TD>
        <TD><INPUT type="button" value="Запустить"/></TD>
        <TD><INPUT type="button" value="Остановить"/></TD>
    </TR>
</TABLE>
</body>
</html>
