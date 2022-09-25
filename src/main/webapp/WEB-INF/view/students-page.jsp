<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
<body>
    <c:forEach var="student" items="${requestScope.students}">
        <ul>
            <li>
                id <c:out value="${student.id}"/>
            </li>
            <li>
                Имя <c:out value="${student.name}"/>
            </li>
            <li>
                Фамилия <c:out value="${student.surname}"/>
            </li>
            <a href="/simpleCRUD/delete?id=<c:out value='${student.id}'/>">Delete</a>
        </ul>
    </c:forEach>
    <h2>создание нового пользователя</h2>
    <form method="post" action="create">
        <label>
            <input type="number" name="id">
        </label>id: </br>
        <label>
            <input type="text" name="name">
        </label>Имя: </br>
        <label>
            <input type="text" name="surname">
        </label>Фамилия: </br>
        <input type="submit" value="Ok" name="Ok">
    </form>
</body>
</html>
