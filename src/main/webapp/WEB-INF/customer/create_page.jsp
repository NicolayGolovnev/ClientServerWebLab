<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: kolya
  Date: 29.12.2021
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
<div>
    <h2>Регистрация заказчика</h2>
</div>
<form:form method="POST" action="/customer/add_new" modelAttribute="customerForm">
    <table>
        <tr hidden>
            <th align="right"><label for="id">ID</label></th>
            <th align="left"><form:input path="id" readonly="true" id="id" cssStyle="background-color: #bbbaba"/></th>
        </tr>
        <tr>
            <th align="right"><label for="liftingCapacity">Фамилия</label></th>
            <th align="left"><form:input type="text" id="liftingCapacity" path="firstName" maxlength="25"/></th>
        </tr>
        <tr>
            <th align="right"><label for="passability">Имя</label></th>
            <th align="left"><form:input type="text" id="passability" path="secondName" maxlength="25"/></th>
        </tr>
        <tr>
            <th align="right"><label for="price">Отчество</label></th>
            <th align="left"><form:input type="text" id="price" path="fatherName" maxlength="6"/></th>
        </tr>
        <tr>
            <th align="right"><label for="state">Паспорт</label></th>
            <th align="left"><form:input type="text" id="state" path="passport" maxlength="20"/></th>
        </tr>
        <tr>
            <th></th>
            <th align="right">
                <button type="button" onclick="location.href='/customer/main_page'">Назад</button>
                <button type="submit">Добавить</button>
            </th>
        </tr>
    </table>
</form:form>
</body>
</html>
