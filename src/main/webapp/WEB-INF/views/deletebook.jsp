<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Book</title>

    <h2>Delete Book</h2>
        <form action="deleteBook" method="post">
        <p>${deleteForm}</p>

            <!-- Name Input -->
            <label for="id">Book ID:</label>
            <input type="number" id="id" name="id" required>
            <br>

            <!-- Submit Button -->
            <input type="submit" value="Delete Book">
        </form>
</head>
<body>

</body>
</html>
