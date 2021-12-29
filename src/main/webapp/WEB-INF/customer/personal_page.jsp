<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Pavel
  Date: 29.12.2021
  Time: 13:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${customer.firstName.concat(" ").concat(customer.secondName)}</title>
</head>
<body>
<div class="top">
    <h2>Список заказов</h2>
</div>
<table>
    <tr>
        <th>#</th>
        <th>Дата отправки</th>
        <th>Дата доставки</th>
        <th>Груз</th>
        <th>Вес груза</th>
        <th>Стоимость доставки</th>
        <th>Пункт отправки</th>
        <th>Пункт доставки</th>
    </tr>
    <c:forEach items="${orders}" var="order">
        <tr>
            <td align="center"><a href="/customer/transportation_document/${order.id}">${order.id}</a></td>
            <td align="center">${order.departureDate}</td>
            <td align="center">${order.arrivalDate}</td>
            <td align="center">${order.cargo}</td>
            <td align="center">${order.cargoWeight}</td>
            <td align="center">${order.costDelivery}</td>
            <td align="center">${order.pointDeparture.location}</td>
            <td align="center">${order.pointArrival.location}</td>
            <td><button type="button" onclick="location.href='/customer/${order.id}/state_order'">Состояние заказа</button></td>
            <td><button type="button" onclick="location.href='/customer/${order.id}/pay_page'">Оплатить заказ</button></td>
        </tr>
    </c:forEach>
</table>
<hr/>

<button type="button" onclick="location.href='/customer/main_page'">Вернуться</button>
</body>
</html>
