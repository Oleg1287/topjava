<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal List</title>

    <style>
        table, td{
            border:1px solid black;
        }
    </style>
</head>
<body>
    <h2>Meal Edit</h2>

    <form action="/topjava/meal/edit" method="post">
    <input type="hidden" name="mealId" value="${mealId}"/>
    <table>
        <tr>
            <td>date-time</td>
            <td><input type="text" name="dateTime" disabled="disabled" value="${meal.getDateTime()}"/></td>
        </tr>
        <tr>
            <td>descrition</td>
            <td><input type="text" name="description" value="${meal.getDescription()}"/></td>
        </tr>
        <tr>
            <td>calories</td>
            <td><input type="text" name="calories" value="${meal.getCalories()}"/></td>
        </tr>
        <tr>
            <td align="right" colspan="2"><button type="submit">Сохранить</button></td>
        </tr>
    </table>
    </form>
</body>
</html>