<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kolya
  Date: 29.12.2021
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Оператор заказов</title>
</head>
<body>
<h2>Список заказов</h2>
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
    <th>Заказчик</th>
  </tr>
  <c:forEach items="${orders}" var="order">
    <tr>
      <td align="center"><a href="/customer/transportation_document/${order.id}">${order.id}</a></td>
      <td align="center">${order.departureDate}</td>
      <td align="center">${order.arrivalDate}</td>
      <td align="center">${order.cargo}</td>
      <td align="center">${order.cargoWeight}</td>
      <td align="center">${order.costDelivery}</td>
      <td align="center">${order.pointDeparture.pointLocation}</td>
      <td align="center">${order.pointArrival.pointLocation}</td>
      <td align="center">${order.customer.firstName.concat(" ").concat(order.customer.secondName)}</td>
      <td width="auto"><button type="button" onclick="location.href='/operator/${order.id}/payment_order'">Проверить оплату</button></td>
      <td width="auto"><button type="button" onclick="location.href='/operator/get_route_points/${order.documentNote.document.route.id}'">Посмотреть маршрут</button></td>
      <td width="auto"><button type="button" onclick="location.href='/operator/update_order/${order.id}'">Изменить</button></td>
      <td width="auto"><button type="button" onclick="location.href='/operator/delete_order/${order.id}'">Удалить</button></td>
    </tr>
  </c:forEach>
</table>
<hr/>
<button type="button" onclick="location.href='/operator/create_order'">Создать заказ</button>
<button type="button" onclick="location.href='/'">Назад</button>

</body>
</html>
