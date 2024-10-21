<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://example.com/functions" prefix="t" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table>
    <tr>
        <th>id</th>
        <th>Время</th>
        <th>Описание</th>
        <th>Калории</th>
        <th></th>
    </tr>
<c:forEach var="meal" items="${meals}">
        <tr style="${meal.isExcess() ? 'background-color:red;' : 'background-color:green;'}">
            <td>${meal.getId()}</td>
            <td>${meal.getDateTime()}</td>
            <td>${meal.getDescription()}</td>
            <td>${meal.getCalories()}</td>
            <td>
                <a href="meals?action=delete&id=${meal.getId()}">удалить</a>
            </td>
        </tr>
</c:forEach>
</table>
<a href="meals?action=add">добавить</a>
</body>
</html>