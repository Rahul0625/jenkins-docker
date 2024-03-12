<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Items Page</title>
</head>
<body>

<form action="listBooks" method="post">

    <h3>Available Books Information</h3>

    <table border="1">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Author Name</th>
            <th>Price</th>
            <th>Genre</th>
            <th>Author Email</th>
            <th>Published Year</th>
            <th>Available Count</th>
        </tr>
        </thead>
        <tbody>
        <!-- Assuming you have a list of items in your request attribute named "items" -->
        <c:forEach var="item" items="${items}">
            <tr>
                <td>${item.id}</td>
                <td>${item.name}</td>
                <td>${item.author}</td>
                <td>${item.price}</td>
                <td>${item.genre}</td>
                <td>${item.authormail}</td>
                <td>${item.publishedyear}</td>
                <td>${item.availablecount}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</form>

</body>
</html>
