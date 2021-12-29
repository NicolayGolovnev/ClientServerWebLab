<%--
  Created by IntelliJ IDEA.
  User: kolya
  Date: 29.12.2021
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Состояние заказа</title>
</head>
<body>
<h2>
    Состояние заказа
</h2>
Заказ: ${order.documentNote.unloadingMark == true ? 'пришел' : 'в пути'}
<button type="button" onclick="location.href='/customer/personal_page/${customer.id}'">Назад</button>
</body>
</html>
