<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Find Book</title>
</head>
<body>

    <h2>Find Book</h2>
    <form action="findBookToUpdate" method="post">
        <p>${findBookForm}</p>
        <!-- Book ID Input -->
        <label for="id">Book ID:</label>
        <input type="number" id="id" name="id" required>
        <br>

        <!-- Submit Button -->
        <input type="submit" value="Proceed to Update">
    </form>

</body>
</html>
