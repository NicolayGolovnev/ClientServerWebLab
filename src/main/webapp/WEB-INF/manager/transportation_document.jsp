<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kolya
  Date: 28.12.2021
  Time: 22:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Строки документа</title>
</head>
<body>
<div class="top">
    <h2>Строки документа #${documentNum}</h2>
</div>
<table>
    <tr>
        <th>#</th>
        <th>Отметка о выгрузке заказа</th>
        <th>Груз заказа</th>
    </tr>
    <c:forEach items="${documentNotes}" var="note">
        <tr>
            <td align="center">${note.id}</td>
            <td align="center">${note.unloadingMark}</td>
            <td align="center">${note.order.cargo}</td>
            <td width="auto"><button type="button" onclick="location.href='/manager/transportation_document/update_note/${note.id}'">Изменить</button></td>
            <td width="auto"><button type="button" onclick="location.href='/manager/transportation_document/delete_note/${note.id}'">Удалить</button></td>
        </tr>
    </c:forEach>
</table>
<hr/>
<button type="button" onclick="location.href='/manager/transportation_document/${documentId}/create_note'">Создать новую строчку в документе</button>
<button type="button" onclick="location.href='/manager/main_page'">Вернуться</button>
</body>
</html>
