<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kolya
  Date: 29.12.2021
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Главный менеджер</title>
</head>
<body>
<h2>Список маршрутов</h2>
<table>
    <tr>
        <th>#</th>
        <th>Требование к маршруту</th>
    </tr>
    <c:forEach items="${routes}" var="route">
        <tr>
            <td align="center"><a href="/general_manager/route/${route.id}">${route.id}</a></td>
            <td align="center">${route.shipRequirement}</td>
            <td width="auto"><button type="button" onclick="location.href='/general_manager/update_route/${route.id}'">Изменить</button></td>
            <td width="auto"><button type="button" onclick="location.href='/general_manager/delete_route/${route.id}'">Удалить</button></td>
        </tr>
    </c:forEach>
</table>
<hr/>
<h2>Список пунктов</h2>
<table>
    <tr>
        <th>#</th>
        <th>Местоположение</th>
    </tr>
    <c:forEach items="${points}" var="point">
        <tr>
            <td align="center">${point.id}</td>
            <td align="center">${point.pointLocation}</td>
            <td width="auto"><button type="button" onclick="location.href='/general_manager/update_point/${point.id}'">Изменить</button></td>
            <td width="auto"><button type="button" onclick="location.href='/general_manager/delete_point/${point.id}'">Удалить</button></td>
        </tr>
    </c:forEach>
</table>
<hr/>
<button type="button" onclick="location.href='/general_manager/create_route'">Добавить маршрут</button>
<button type="button" onclick="location.href='/general_manager/create_point'">Добавить пункт</button>
<button type="button" onclick="location.href='/'">Вернуться</button>
</body>
</html>
