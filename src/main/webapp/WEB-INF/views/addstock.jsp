<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Stock</title>
</head>
<body>

    <h2>Add Stock</h2>
    <form action="addStock" method="post">
        <p>${stockForm}</p>

        <!-- Book ID Input -->
        <label for="id">Book ID:</label>
        <input type="number" id="id" name="id" required>
        <br>

        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="i" required> <!-- Use 'i' as the name attribute -->
        <br>

        <!-- Submit Button -->
        <input type="submit" value="Add">
    </form>

</body>
</html>
