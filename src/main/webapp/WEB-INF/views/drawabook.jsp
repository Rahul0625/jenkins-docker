<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pick a Book</title>

    <h2>Pick a Book</h2>
        <form action="drawABook" method="post">
        <p> ${drawForm} </p>
            <!-- Name Input -->
            <label for="id">Book ID:</label>
            <input type="number" id="id" name="id" required>
            <br>

            <!-- Submit Button -->
            <input type="submit" value="Get">
        </form>
</head>
<body>

</body>
</html>
