<%--
  Created by IntelliJ IDEA.
  User: yuri
  Date: 12.03.17
  Time: 0:32
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

<h3>Ответить на загадку</h3>

<c:if test="${!empty mesAnswer}">
    ${mesAnswer}
</c:if>

<form action="/user/solveRiddle/${riddle.idRiddle}" method="post">
    <label for="answer">Ваш ответ:</label>
    <input type="text" name="answer" id="answer" value="" placeholder="Input"><br>

    <input type="submit" value="Ответить">
</form>

<h3>Информация о загадке</h3>
<table>
    <tr>
        <td>Name</td>
        <td><c:out value="${riddle.name}"/></td>
    </tr>
    <tr>
        <td>Text</td>
        <td><c:out value="${riddle.text}"></c:out></td>
    </tr>
    <tr>
        <td>Category</td>
        <td><c:out value="${riddle.category}"></c:out></td>
    </tr>
</table>

</body>
</html>
