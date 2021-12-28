<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kolya
  Date: 28.12.2021
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="top">
    <h2>Список кораблей</h2>
</div>
<table>
    <tr>
        <th>#</th>
        <th>Грузоподъемность</th>
        <th>Проходимость</th>
        <th>Стоимость</th>
        <th>Состояние</th>
        <th>Парк</th>
    </tr>
    <c:forEach items="${shipList}" var="ship">
        <tr>
            <td align="center">${ship.id}</td>
            <td align="center">${ship.liftingCapacity}</td>
            <td align="center">${ship.passability}</td>
            <td align="center">${ship.price}</td>
            <td align="center">${ship.state}</td>
            <td align="center">${ship.park.name}</td>
        </tr>
    </c:forEach>
</table>
<hr/>
<button type="button" onclick="location.href='/manager/main_page'">Вернуться</button>
</body>
</html>
