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
            <td><a href="/user/editRiddle/${riddle.idRiddle}">Редактировать</a></td>
                <%--<td><a href="/delete?id=${user.id}">Delete</a></td>--%>
        </tr>

    </c:forEach>
</table>

<h3>Добавить загадку</h3>

<%--<c:url var="addAction" value="/addRiddle"/>--%>

<form:form action="/user/addRiddle" commandName="riddle">
    <c:if test="${!empty mes}">
        ${mes}
    </c:if>
    <br>
    <table>
        <c:if test="${!empty riddle.name}">
            <tr>
                <td>
                    <form:label path="idRiddle">
                        <spring:message text="Riddle ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="idRiddle" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="idRiddle"/>
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="name">
                    <spring:message text="Title"/>
                </form:label>
            </td>
            <td>
                <form:input path="name"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="text">
                    <spring:message text="Text"/>
                </form:label>
            </td>
            <td>
                <form:input path="text"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="answer">
                    <spring:message text="Answer"/>
                </form:label>
            </td>
            <td>
                <form:input path="answer"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="level">
                    <spring:message text="Level"/>
                </form:label>
            </td>
            <td>
                <form:input path="level"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="category">
                    <spring:message text="Category"/>
                </form:label>
            </td>
            <td>
                <form:input path="category"/>
            </td>
        </tr>

        <c:if test="${!empty riddle.name}">
            <tr>
                <td>
                    <form:label path="idUser">
                        <spring:message text="User add ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="idUser" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="idUser"/>
                </td>
            </tr>
        </c:if>

        <tr>
            <td colspan="2">
                <c:if test="${!empty riddle.name}">
                    <input type="submit"
                           value="<spring:message text="Edit Riddle"/>"/>
                </c:if>
                <c:if test="${empty riddle.name}">
                    <input type="submit"
                           value="<spring:message text="Add Riddle"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>
