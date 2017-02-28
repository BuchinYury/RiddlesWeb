<%--
  Created by IntelliJ IDEA.
  User: yuri
  Date: 28.02.17
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/editRiddle" method="post">
    <table>

        <tr>
            <th>Field</th>
            <th>Value</th>
        </tr>

        <tr>
            <td><label for="idRiddle">Riddle id:</label></td>
            <td><input type="text" disabled name="idRiddle" id="idRiddle" value="${idRiddle}" placeholder="Riddle id">
            </td>
            <input type="hidden" name="idRiddle" id="idRiddle" value="${idRiddle}">
        </tr>

        <tr>
            <td><label for="name">Name:</label></td>
            <td><input type="text" disabled name="name" id="name" value="${name}" placeholder="Input"></td>
        </tr>

        <tr>
            <td><label for="text">Text:</label></td>
            <td><input type="text" disabled name="text" id="text" value="${text}" placeholder="Input"></td>
        </tr>

        <tr>
            <td><label for="answer">Answer:</label></td>
            <td><input type="text" disabled name="answer" id="answer" value="${answer}" placeholder="Input"></td>
        </tr>

        <tr>
            <td><label for="level">Level:</label></td>
            <td><input type="text" disabled name="level" id="level" value="${level}" placeholder="Input"></td>
        </tr>

        <tr>
            <td><label for="category">Category:</label></td>
            <td><input type="text" disabled name="category" id="category" value="${category}" placeholder="Input"></td>
        </tr>

        <tr>
            <td><label for="idUser">User id:</label></td>
            <td><input type="text" disabled name="idUser" id="idUser" value="${idUser}" placeholder="User id"></td>
        </tr>

        <tr></tr>

        <% if ((boolean) request.getAttribute("block")) { %>
        <tr>
            <td><input type="hidden" name="isBlock" id="isBlock" value="true"></td>
            <td><input type="submit" value="Un Block riddle" formmethod="post"></td>
        </tr>

        <% } else { %>
        <tr>
            <td><input type="hidden" name="isBlock" id="isBlock" value="false"></td>
            <td><input type="submit" value="Block riddle" formmethod="post"></td>
        </tr>
        <% } %>

    </table>
</form>
</body>
</html>