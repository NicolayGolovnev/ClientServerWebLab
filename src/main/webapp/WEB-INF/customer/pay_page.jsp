<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: kolya
  Date: 29.12.2021
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Оплата заказа</title>
</head>
<body>
<h2>Оплатить заказ ${order.cargo}</h2>
<form:form method="POST" action="/customer/pay_page" modelAttribute="paymentForm">
    <table>
        <tr hidden>
            <th align="right"><label for="id">ID</label></th>
            <th align="left"><form:input path="id" readonly="true" id="id" cssStyle="background-color: #bbbaba"/></th>
        </tr>
        <tr>
            <th align="right"><label for="liftingCapacity">Сумму платежа</label></th>
            <th align="left"><form:input type="text" id="liftingCapacity" path="payment" placeHolder="Введите число" maxlength="25"/></th>
        </tr>
        <tr>
            <th align="right"><label for="passability">Дата платежа</label></th>
            <th align="left"><form:input type="date" id="passability" path="paymentDate"/></th>
        </tr>
        <tr hidden>
            <th align="right"><label for="price">id</label></th>
            <th align="left"><form:input type="text" id="price" path="order.id" value="${order.id}" maxlength="6"/></th>
        </tr>
        <tr>
            <th></th>
            <th align="right">
                <button type="button" onclick="location.href='/customer/personal_page/${customer.id}'">Назад</button>
                <button type="submit">Оплатить</button>
            </th>
        </tr>
    </table>
</form:form>
</body>
</html>
