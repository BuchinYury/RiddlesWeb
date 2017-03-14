<%--
  Created by IntelliJ IDEA.
  User: yuri
  Date: 23.02.17
  Time: 23:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>

<html>
<head>
    <title>Login</title>
</head>
<body>
${mes}
<div>
    <form action="/login" method="post">
        <label for="login">Login:</label>
        <input type="text" name="login" id="login" value="" placeholder="Input"><br>

        <label for="password">Password:</label>
        <input type="password" name="password" id="password" value="" placeholder="Input">

        <input type="submit" value="Submit">
    </form>
</div>
<a href="/registration">Регистрация</a>
</body>
</html>
