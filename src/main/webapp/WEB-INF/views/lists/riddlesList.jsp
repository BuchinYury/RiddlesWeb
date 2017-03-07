<%--
  Created by IntelliJ IDEA.
  User: yuri
  Date: 27.02.17
  Time: 23:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/addRiddle">Добавить загадку</a>
<table border="1">
    <tr>
        <th>Name</th>
        <th>Category</th>
        <th>Level</th>
        <th>User Add ID</th>
        <th>Blocked</th>
        <th>Action</th>
    </tr>

    <c:forEach items="${riddlesList}" var="riddle">

        <tr>
            <td><c:out value="${riddle.name}"></c:out></td>
            <td><c:out value="${riddle.category}"></c:out></td>
            <td><c:out value="${riddle.level}"></c:out></td>
            <td><c:out value="${riddle.idUser}"></c:out></td>
            <td><c:out value="${riddle.block}"></c:out></td>
            <td><a href="/editRiddle?id=${riddle.idRiddle}">Просмотреть</a></td>
            <%--<td><a href="/delete?id=${user.id}">Delete</a></td>--%>
        </tr>

    </c:forEach>
</table>

</body>
</html>
