<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: kolya
  Date: 29.12.2021
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Изменение пункта</title>
</head>
<body>
<h2>Редактирование пункта</h2>
<form:form method="POST" action="/general_manager/update_point" modelAttribute="point">
    <table>
        <tr hidden>
            <th align="right"><label for="id">ID</label></th>
            <th align="left"><form:input path="id" readonly="true" id="id" cssStyle="background-color: #bbbaba"/></th>
        </tr>
        <tr>
            <th align="right"><label>Требование к судну</label></th>
            <th align="left"><form:input type="text" path="pointLocation"/></th>
        </tr>

        <tr>
            <th></th>
            <th align="right">
                <button type="button" onclick="location.href='/general_manager/main_page'">Назад</button>
                <button type="submit">Изменить</button>
            </th>
        </tr>
    </table>
</form:form>
</body>
</html>
