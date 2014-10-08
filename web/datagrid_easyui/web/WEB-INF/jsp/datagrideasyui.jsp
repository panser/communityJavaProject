<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta charset="UTF-8">

    <link rel='stylesheet' type='text/css' href='<c:url value="/webjars/bootstrap/3.2.0/css/bootstrap.css"/>'>
    <link rel='stylesheet' type='text/css' href='<c:url value="/webjars/jquery-ui/1.10.4/themes/base/jquery.ui.all.css"/>'>
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/easyui/easyui.css"/>">

    <script src="<c:url value="/webjars/jquery/2.1.1/jquery.js"/>"></script>
    <script src="<c:url value="/webjars/jquery-ui/1.10.4/ui/jquery-ui.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/static/easyui/jquery.easyui.min.js"/>"></script>
</head>

<body>
<script type="text/javascript">
    $(function (){
        $("#search").click(function() {
            var filterValue = $('#filterValue').val();
            $('#dg').datagrid('load', {
                apn: filterValue
            });
            return false;
        });
        $("#dg").datagrid({
            onClickCell: function(index, field, value) {
                if (field === "edit") {
                    console.log("edit");
                    $.get("/jfrs_apn/networks/edit/" + index, function () {
//                        window.location = '/jfrs_apn/networks/edit/';
                    })
                } else if (field === "delete") {
                    console.log("delete");
                    var value = $("#dg").datagrid('getRows')[index].apn;
                    $.post("/jfrs_apn/networks/delete/", {rowApn: value}, loadTableData());
                } else {
                    console.log("other");
                }
            }
        })
    });

    function editAction(){
        return '<u>Изменить</u>';
    }
    function deleteAction(){
        return '<u>Удалить</u>';
    }
</script>



<table cellpadding="3" cellspacing="0" border="0">
    <tr>
        <td>
            User:
        </td>
        <td>
            <form:input id="filterValue" name="filterValue" path="user"/>
        </td>
        <td>
<%--
            <form:button id="search" name="search">
                Search
            </form:button>
--%>
        </td>
    </tr>
</table>

<%--
<form:button name="addNetwork">
    Add
</form:button>
--%>

<%--
<form:button name="editNetwork">
    Edit
</form:button>
--%>

<table id="dg" class="easyui-datagrid" style="width:auto"
       url="/datagrideasyui"
       toolbar="#toolbar" pagination="true"
       rownumbers="true" fitColumns="true" singleSelect="true">
    <thead>
    <tr>
        <th field="login" width="50">Login</th>
        <th field="email" width="50">Email</th>
        <th field="password" width="50">Password</th>
        <th data-options="field:'edit',formatter:editAction"></th>
        <th data-options="field:'delete',formatter:deleteAction"></th>
    </tr>
    </thead>
</table>

</body>
</html>