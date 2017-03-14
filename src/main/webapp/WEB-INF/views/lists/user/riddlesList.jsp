<%--
  Created by IntelliJ IDEA.
  User: yuri
  Date: 27.02.17
  Time: 23:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%--<a href="/addRiddle">Добавить загадку</a>--%>

<h3>Список загадок</h3>
<table border="1">
    <tr>
        <th>Name</th>
        <th>Category</th>
        <th>Level</th>
        <th>Action</th>
    </tr>

    <c:forEach items="${riddlesList}" var="riddle">

        <tr>
            <td><c:out value="${riddle.name}"></c:out></td>
            <td><c:out value="${riddle.category}"></c:out></td>
            <td><c:out value="${riddle.level}"></c:out></td>
            <td>
                <a href="/user/solveRiddle/${riddle.idRiddle}">Решить загадку</a>
                <a href="/discusRiddle/${riddle.idRiddle}">Комментарии</a>
            </td>
        </tr>

    </c:forEach>
</table>
</body>
</html>
