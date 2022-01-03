<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kolya
  Date: 29.12.2021
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактирование последовательности</title>
</head>
<body>
<h2>Изменение порядка маршрута</h2>
<form:form method="POST" action="/general_manager/route/update_sequence" modelAttribute="sequence">
    <table>
        <tr hidden>
            <th align="right"><label for="id">ID</label></th>
            <th align="left"><form:input path="id" readonly="true" id="id" cssStyle="background-color: #bbbaba"/></th>
        </tr>
        <tr>
            <th align="right"><label>Порядковый номер</label></th>
            <th align="left"><form:input type="text" placeHolder="Введите число" path="orderNumber"/></th>
        </tr>
        <tr>
            <th align="right"><label>Время прибытия</label></th>
            <th align="left"><form:input type="date" path="arrivalDate"/></th>
        </tr>
        <tr>
            <th align="right"><label>Время отправки</label></th>
            <th align="left"><form:input type="date" path="dispatchDate"/></th>
        </tr>
        <tr>
            <th align="right"><label>Пункт</label></th>

            <th align="left">
                <form:input type="text" list="points" path="point.id" autocomplete="false"/>
                <datalist id="points">
                    <c:forEach items="${points}" var="point">
                        <option value="${point.id}">${point.pointLocation}</option>
                    </c:forEach>
                </datalist>
            </th>
        </tr>
        <tr>
            <th align="right"><label>Маршрут</label></th>

            <th align="left">
                <form:input type="text" list="routes" path="route.id" autocomplete="false"/>
                <datalist id="routes">
                    <c:forEach items="${routes}" var="route">
                        <option value="${route.id}"></option>
                    </c:forEach>
                </datalist>
            </th>
        </tr>
        <tr>
            <th align="right">
                <button type="button" onclick="location.href='/general_manager/route/${sequenceId}'">Назад</button>
                <button type="submit">Изменить</button>
            </th>
        </tr>
    </table>
</form:form>
</body>
</html>
