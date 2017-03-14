<%--
  Created by IntelliJ IDEA.
  User: yuri
  Date: 24.02.17
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
${mes}<br>
${mesLogin}
<form action="/registration" method="post">
    <label for="login">Login:</label>
    <input type="text" name="login" id="login" value="" placeholder="Input"><br>

    <label for="password">Password:</label>
    <input type="password" name="password" id="password" value="" placeholder="Input"><br>

    <label for="userName">User Name:</label>
    <input type="text" name="userName" id="userName" value="" placeholder="Input"><br>

    <label for="email">Email:</label>
    <input type="text" name="email" id="email" value="" placeholder="Input"><br>

    <input type="submit" value="Submit" formmethod="post">
</form>
</body>
</html>
