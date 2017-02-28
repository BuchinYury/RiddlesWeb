<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page session="false" %>

<html>
<head>
    <title>Users List</title>
</head>
<body>
<h1>List</h1>
<%--<a href="/lections">Лекции</a><br>--%>
<%--<a href="/addstudent">Добавить студента</a>--%>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Login</th>
        <th>User Name</th>
        <%--<th>Action</th>--%>
    </tr>

    <c:forEach items="${usersList}" var="user">

        <tr>
            <td><c:out value="${user.idUser}"></c:out></td>
            <td><c:out value="${user.login}"></c:out></td>
            <td><c:out value="${user.userName}"></c:out></td>
                <%--<td><a href="/edit?id=${user.id}">Edit</a></td>--%>
                <%--<td><a href="/delete?id=${user.id}">Delete</a></td>--%>
        </tr>

    </c:forEach>
</table>
</body>
</html>
