<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kolya
  Date: 29.12.2021
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Маршрут</title>
</head>
<body>
<h2>Порядок следования</h2>
<table>
    <tr>
        <th>Порядковый номер</th>
        <th>Время прибытия</th>
        <th>Время отправки</th>
        <th>Пункт</th>
        <th>Маршрут</th>
    </tr>
    <c:forEach items="${sequences}" var="sequence">
        <tr>
            <td align="center">${sequence.orderNumber}</td>
            <td align="center">${sequence.arrivalDate}</td>
            <td align="center">${sequence.dispatchDate}</td>
            <td align="center">${sequence.point.location}</td>
            <td align="center">${sequence.route.id}</td>
            <td width="auto"><button type="button" onclick="location.href='/general_manager/route/update_sequence/${sequence.id}'">Изменить</button></td>
            <td width="auto"><button type="button" onclick="location.href='/general_manager/route/delete_sequence/${sequence.id}'">Удалить</button></td>
        </tr>
    </c:forEach>
</table>
<button type="button" onclick="location.href='/general_manager/route/${routeId}/create_sequence'">Добавить новый порядок в маршрут</button>
<button type="button" onclick="location.href='/general_manager/main_page'">Вернуться</button>
</body>
</html>
