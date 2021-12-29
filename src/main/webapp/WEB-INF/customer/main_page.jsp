<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Pavel
  Date: 29.12.2021
  Time: 13:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Рабочее место заказчика</title>
</head>
<body>
<c:forEach items="${customers}" var="customer">
    <button type="button" onclick="location.href='/customer/personal_page/${customer.id}'">
        ${customer.firstName.concat(" ").concat(customer.secondName)}
    </button>
</c:forEach>
</body>
<hr/>
<button type="button" onclick="location.href='/customer/create'">Зарегистрироваться</button>
<button type="button" onclick="location.href='/'">Вернуться</button>
</html>
