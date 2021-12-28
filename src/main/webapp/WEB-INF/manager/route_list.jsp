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
    <title>Список маршрутов</title>
</head>
<body>
<div class="top">
    <h2>Список маршрутов</h2>
</div>
<table>
    <tr>
        <th>#</th>
        <th>Требование к судну</th>
    </tr>
    <c:forEach items="${routeList}" var="route">
        <tr>
            <td align="center"><a href="/manager/sequence_route/${route.id}">${route.id}</a></td>
            <td align="center">${route.shipRequirement}</td>
        </tr>
    </c:forEach>
</table>
<hr/>
<button type="button" onclick="location.href='/manager/main_page'">Вернуться</button>
</body>
</html>
