<%@ page import="io.buchin.models.pojo.User" %><%--
  Created by IntelliJ IDEA.
  User: yuri
  Date: 26.02.17
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/adminDashboard" method="post">
    <%
        HttpSession session = request.getSession();

        if ((boolean)session.getAttribute("notification")) { %>
    <tr>
        <td><input type="hidden" name="Notification" id="Notification" value="false"></td>
        <td><input type="submit" value="Disable notification of entering" formmethod="post"></td>
    </tr>
    <% } else { %>
    <tr>
        <td><input type="hidden" name="Notification" id="Notification" value="true"></td>
        <td><input type="submit" value="Enable notifications of entering" formmethod="post"></td>
    </tr>
    <% } %>
</form>
<a href="/usersList">Список пользователей</a><br>
<a href="/riddlesList">База загадок</a><br>
<a href="/logout">Logout</a>
</body>
</html>
