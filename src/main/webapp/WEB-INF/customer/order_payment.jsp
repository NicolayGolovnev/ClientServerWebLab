<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Pavel
  Date: 29.12.2021
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Оплаты заказа</title>
</head>
<body>
<div class="top">
    <h2>Список заказов</h2>
</div>
<table>
    <tr>
        <th>Сумма оплаты</th>
        <th>Дата оплаты</th>
    </tr>
    <c:forEach items="${payments}" var="payment">
        <tr>
            <td align="center">${payment.payment}</td>
            <td align="center">${payment.paymentDate}</td>
        </tr>
    </c:forEach>
</table>
<hr/>
<button type="button" onclick="location.href='/customer/personal_page/${customer_id}'">Вернуться</button>
</body>
</html>
