<%--
  Created by IntelliJ IDEA.
  User: kolya
  Date: 28.12.2021
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавления корабля в парк</title>
</head>
<body>
<div>
    <h2>Добавление нового корабля в парк</h2>
</div>
<form:form method="POST" action="/director/create_ship" modelAttribute="shipForm">
    <table>
        <tr hidden>
            <th align="right"><label for="id">ID</label></th>
            <th align="left"><form:input path="id" readonly="true" id="id" cssStyle="background-color: #bbbaba"/></th>
        </tr>
        <tr>
            <th align="right"><label for="liftingCapacity">Грузоподъемность</label></th>
            <th align="left"><form:input type="text" id="liftingCapacity" path="liftingCapacity" placeHolder="Введите число" maxlength="25"/></th>
        </tr>
        <tr>
            <th align="right"><label for="passability">Проходимость</label></th>
            <th align="left"><form:input type="text" id="passability" path="passability" maxlength="25"/></th>
        </tr>
        <tr>
            <th align="right"><label for="price">Стоимость</label></th>
            <th align="left"><form:input type="text" id="price" path="price" placeHolder="Введите число" maxlength="6"/></th>
        </tr>
        <tr>
            <th align="right"><label for="state">Состояние</label></th>
            <th align="left"><form:input type="text" id="state" path="state" maxlength="20"/></th>
        </tr>
        <tr>
            <th></th>
            <th align="right">
                <button type="button" onclick="location.href='/director/main_page'">Назад</button>
                <button type="submit">Добавить</button>
            </th>
        </tr>
    </table>
</form:form>
</body>
</html>
