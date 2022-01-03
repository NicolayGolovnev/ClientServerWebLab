<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kolya
  Date: 29.12.2021
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создание заказа</title>
</head>
<body>
<h2>Добавление нового заказа</h2>
<form:form method="POST" action="/operator/create_order" modelAttribute="orderForm">
    <table>
        <tr hidden>
            <th align="right"><label for="id">ID</label></th>
            <th align="left"><form:input path="id" readonly="true" id="id" cssStyle="background-color: #bbbaba"/></th>
        </tr>
        <tr>
            <th align="right"><label for="liftingCapacity">Время отправки</label></th>
            <th align="left"><form:input type="date" id="liftingCapacity" path="departureDate" maxlength="25"/></th>
        </tr>
        <tr>
            <th align="right"><label for="passability">Время прибытия</label></th>
            <th align="left"><form:input type="date" id="passability" path="arrivalDate" maxlength="25"/></th>
        </tr>
        <tr>
            <th align="right"><label for="price">Груз</label></th>
            <th align="left"><form:input type="text" id="price" path="cargo" maxlength="40"/></th>
        </tr>
        <tr>
            <th align="right"><label for="state">Вес груза</label></th>
            <th align="left"><form:input type="text" id="state" path="cargoWeight" placeHolder="Введите число" maxlength="20"/></th>
        </tr>
        <tr>
            <th align="right"><label for="state">Стоимость заказа</label></th>
            <th align="left"><form:input type="text" id="state" path="costDelivery" placeHolder="Введите число" maxlength="20"/></th>
        </tr>
        <tr>
            <th align="right"><label>Заказчик</label></th>

            <th align="left">
                <form:input type="text" list="customers" path="customer.id" autocomplete="false"/>
                <datalist id="customers">
                    <c:forEach items="${customers}" var="customer">
                        <option value="${customer.id}">${customer.firstName.concat(" ").concat(customer.secondName)}</option>

                    </c:forEach>
                </datalist>
            </th>
        </tr>
        <tr>
            <th align="right"><label>Пункт отправки</label></th>

            <th align="left">
                <form:input type="text" list="points" path="pointDeparture.id" autocomplete="false"/>
                <datalist id="points">
                    <c:forEach items="${points}" var="point">
                        <option value="${point.id}">${point.pointLocation.concat("")}</option>
                    </c:forEach>
                </datalist>
            </th>
        </tr>
        <tr>
            <th align="right"><label>Пункт доставки</label></th>

            <th align="left">
                <form:input type="text" list="points" path="pointArrival.id" autocomplete="false"/>
                <datalist id="points">
                    <c:forEach items="${points}" var="point">
                        <option value="${point.id}">${point.pointLocation}</option>
                    </c:forEach>
                </datalist>
            </th>
        </tr>
        <tr>
            <th></th>
            <th align="right">
                <button type="button" onclick="location.href='/operator/main_page'">Назад</button>
                <button type="submit">Добавить</button>
            </th>
        </tr>
    </table>
</form:form>
</body>
</html>
