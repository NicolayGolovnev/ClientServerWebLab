<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: kolya
  Date: 29.12.2021
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создание пункта</title>
</head>
<body>
<h2>Добавление пункта</h2>
<form:form method="POST" action="/general_manager/create_point" modelAttribute="pointForm">
    <table>
    <tr hidden>
    <th align="right"><label for="id">ID</label></th>
    <th align="left"><form:input path="id" readonly="true" id="id" cssStyle="background-color: #bbbaba"/></th>
    </tr>
    <tr>
    <th align="right"><label>Местоположение</label></th>
    <th align="left"><form:input type="text" path="location"/></th>
    </tr>

    <tr>
    <th align="right">
    <button type="button" onclick="location.href='/general_manager/main_page'">Назад</button>
    <button type="submit">Добавить</button>
    </th>
    </tr>
    </table>
</form:form>
</body>
</html>
