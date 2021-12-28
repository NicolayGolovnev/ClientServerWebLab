<%--
  Created by IntelliJ IDEA.
  User: kolya
  Date: 28.12.2021
  Time: 21:11
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создание документа</title>
</head>
<body>
<div>
    <h2>Добавление нового документа</h2>
</div>
<form:form method="POST" action="/manager/create_transportation_document" modelAttribute="document">
    <table>
        <tr hidden>
            <th align="right"><label for="id">ID</label></th>
            <th align="left"><form:input path="id" readonly="true" id="id" cssStyle="background-color: #bbbaba"/></th>
        </tr>
        <tr>
            <th align="right"><label>Номер документа</label></th>
            <th align="left"><form:input type="text" path="docNumber" maxlength="25"/></th>
        </tr>
        <tr>
            <th align="right"><label>Судно</label></th>

            <th align="left">
                <form:input type="text" list="shipList" path="ship.id" autocomplete="false"/>
                <datalist id="shipList">
                    <c:forEach items="${shipList}" var="ship">
                        <option value="${ship.id}"></option>
                    </c:forEach>
                </datalist>
            </th>
        </tr>
        <tr>
            <th align="right"><label>Маршрут</label></th>
            <th align="left">
                <form:input type="text" list="routeList" path="route.id" autocomplete="false"/>
                <datalist id="routeList">
                    <c:forEach items="${routeList}" var="route">
                        <option value="${route.id}"></option>
                    </c:forEach>
                </datalist>
            </th>
        </tr>
        <tr>
            <th></th>
            <th align="right">
                <button type="button" onclick="location.href='/manager/main_page'">Назад</button>
                <button type="submit">Добавить</button>
            </th>
        </tr>
    </table>
</form:form>
</body>
</html>
