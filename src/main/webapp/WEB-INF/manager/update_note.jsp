<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kolya
  Date: 28.12.2021
  Time: 23:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Обновление строчки документа</title>
</head>
<body>
<div>
    <h2>Изменение строки документа №${note.document.docNumber}</h2>
</div>
<form:form method="POST" action="/manager/transportation_document/update_note" modelAttribute="note">
    <table>
        <tr hidden>
            <th align="right"><label for="id">ID</label></th>
            <th align="left"><form:input path="id" readonly="true" id="id" cssStyle="background-color: #bbbaba"/></th>
        </tr>
        <tr>
            <th align="right"><label>Отметка о выгрузке заказа</label></th>
            <th align="left"><form:checkbox path="unloadingMark"/></th>
        </tr>
        <tr>
            <th align="right"><label>Груз</label></th>

            <th align="left">
                <form:input type="text" list="shipList" path="order.id" autocomplete="false"/>
                <datalist id="shipList">
                    <c:forEach items="${orderList}" var="order">
                        <option value="${order.id}">${order.cargo}</option>
                    </c:forEach>
                </datalist>
            </th>
        </tr>
        <tr hidden>
            <th align="right"><label>ID document</label></th>
            <th align="left"><form:input path="document.id" value="${note.document.id}" readonly="true" cssStyle="background-color: #bbbaba"/></th>
        </tr>
        <tr>
            <th></th>
            <th align="right">
                <button type="button" onclick="location.href='/manager/transportation_document/${note.document.id}'">Назад</button>
                <button type="submit">Добавить</button>
            </th>
        </tr>
    </table>
</form:form>
</body>
</html>
