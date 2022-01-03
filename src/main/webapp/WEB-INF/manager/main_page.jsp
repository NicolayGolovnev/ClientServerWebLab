<%--
  Created by IntelliJ IDEA.
  User: kolya
  Date: 28.12.2021
  Time: 20:58
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Рабочее место Менеджера</title>
</head>
<body>
<div class="top">
    <h2>Список документов</h2>
</div>
<table>
    <tr>
        <th>#</th>
        <th>Номер документа</th>
        <th>Номер судна</th>
        <th>Номер маршрута</th>
    </tr>
    <c:forEach items="${documentList}" var="ship">
        <tr>
            <td align="center">${ship.id}</td>
            <td align="center"><a href="/manager/transportation_document/${ship.id}">${ship.docNumber}</a></td>
            <td align="center">${ship.ship.id}</td>
            <td align="center">${ship.route.id}</td>
            <td width="auto"><button type="button" onclick="location.href='/manager/update_transportation_document/${ship.id}'">Изменить</button></td>
            <td width="auto"><button type="button" onclick="location.href='/manager/delete_transportation_document/${ship.id}'">Удалить</button></td>
        </tr>
    </c:forEach>
</table>
<hr/>
<button type="button" onclick="location.href='/manager/ship_list'">Список судов</button>
<button type="button" onclick="location.href='/manager/route_list'">Список маршрутов</button>
<button type="button" onclick="location.href='/manager/create_transportation_document'">Создать новый документ</button>
<button type="button" onclick="location.href='/'">Вернуться</button>
</body>
</html>

