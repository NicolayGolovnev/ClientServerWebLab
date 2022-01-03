<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Pavel
  Date: 04.01.2022
  Time: 4:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактирование заказа №${order.id}</title>
</head>
<body>
<div>
    <h2>Изменение заказа №${order.id}</h2>
</div>
<form:form method="POST" action="/operator/update_order" modelAttribute="order">
    <table>
        <tr hidden>
            <th align="right"><label for="id">ID</label></th>
            <th align="left"><form:input path="id" readonly="true" id="id" cssStyle="background-color: #bbbaba"/></th>
        </tr>
        <tr>
            <th align="right"><label>Дата отправки</label></th>
            <th align="left"><form:input type="date" path="departureDate"/></th>
        </tr>
        <tr>
            <th align="right"><label>Дата доставки</label></th>
            <th align="left"><form:input type="date" path="arrivalDate"/></th>
        </tr>
        <tr>
            <th align="right"><label>Груз</label></th>
            <th align="left"><form:input type="text" placeHolder="Введите число" path="cargo"/></th>
        </tr>
        <tr>
            <th align="right"><label>Вес груза</label></th>
            <th align="left"><form:input type="text" placeHolder="Введите число" path="cargoWeight"/></th>
        </tr>
        <tr>
            <th align="right"><label>Стоимость доставки</label></th>
            <th align="left"><form:input type="text" placeHolder="Введите число" path="costDelivery"/></th>
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
            <th></th>
            <th align="right">
                <button type="button" onclick="location.href='/operator/main_page'">Назад</button>
                <button type="submit">Обновить</button>
            </th>
        </tr>
    </table>
</form:form>
</body>
</html>
