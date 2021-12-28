<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kolya
  Date: 28.12.2021
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Порядок следования</title>
</head>
<body>
<div class="top">
    <h2>Порядок следования</h2>
</div>
<table>
    <tr>
        <th>Порядковый номер</th>
        <th>Время прибытия</th>
        <th>Время отправки</th>
        <th>Пункт</th>
    </tr>
    <c:forEach items="${sequenceList}" var="sequence">
        <tr>
            <td align="center">${sequence.orderNumber}</td>
            <td align="center">${sequence.arrivalDate}</td>
            <td align="center">${sequence.dispatchDate}</td>
            <td align="center">${sequence.point.location}</td>
        </tr>
    </c:forEach>
</table>
<hr/>
<button type="button" onclick="location.href='/manager/route_list'">Вернуться</button>

</body>
</html>
