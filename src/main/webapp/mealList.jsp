<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal List</title>

    <style>
        a, tr.notExceed, tr.exceed{
            color:white;
        }
        tr.notExceed{
            background-color: darkgreen;
        }
        tr.exceed{
            background-color: darkred;
        }
        table{
            border:1px solid black;
            width: 50%
        }
        tr{
            text-align:right;
        }
        th{
            text-align: center;
        }
    </style>
</head>
<body>
    <c:set var="mealId" value="${0}"/>
    <h2>Meal List</h2>
    <table>
        <tr>
            <th>id</th>
            <th>date-time</th>
            <th>description</th>
            <th>calories</th>
            <th>actions</th>
        </tr>
    <c:forEach var="meal" items="${meals}">
        <tr class="${meal.isExceed() ? "exceed" : "notExceed"}" fhbfre="${meal.getDateTime()}">
            <td align="left">#${mealId+1}</td>
            <td>
                <fmt:parseDate value="${ meal.getDateTime() }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                <fmt:formatDate pattern="dd.MM.yyyy" value="${ parsedDateTime }" />
            </td>
            <%--<td>${localDateTimeFormat.parse()}</td>--%>
            <td>${meal.getDescription()}</td>
            <td>${meal.getCalories()}</td>
            <td>
                <a href="/topjava/meal/delete?mealId=${mealId}">Удалить</a>
                <br/>
                <a href="/topjava/meal/edit?mealId=${mealId}">Редактировать</a>
            </td>
        </tr>
        <c:set var="mealId" value="${mealId+1}"/>
    </c:forEach>
    </table>
</body>
</html>