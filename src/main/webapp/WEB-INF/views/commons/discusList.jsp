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

<h3>Комментарии к загадке ${RiddleName}</h3>

<form action="/discusRiddle/${RiddleID}" method="post">
    <label for="discus">Введите комментарий:</label>
    <input type="text" name="discus" id="discus" value="" placeholder="Input"><br>

    <input type="submit" value="Отправить">
</form>

<table border="1">
    <tr>
        <th>Adds User</th>
        <th>Text Discus</th>
    </tr>

    <c:forEach items="${DiscusList}" var="discus">
        <tr>
            <td><c:out value="${discus.userName}"></c:out></td>
            <td><c:out value="${discus.text}"></c:out></td>
        </tr>

    </c:forEach>
</table>

</body>
</html>
