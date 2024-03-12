<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Book</title>
</head>
<body>

<%
    // Retrieve the existing book data from the request attributes
    com.example.model.Item existingBook = (com.example.model.Item) request.getAttribute("existingBook");
%>

<h2>Update Book</h2>
<form action="updateBook" method="post">
 <p> ${updateForm}</p>

    <!-- Book ID (hidden) -->
    <input type="hidden" name="id" value="<%= request.getParameter("id") %>">

    <!-- Display existing book data -->
    <% if (existingBook != null) { %>
        <p>Current Book Details:</p>
        <ul>
            <li>Book Name: <%= existingBook.getName() %></li>
            <li>Author Name: <%= existingBook.getAuthor() %></li>
            <li>Price: <%= existingBook.getPrice() %></li>
            <li>Genre: <%= existingBook.getGenre() %></li>
            <li>Author Email: <%= existingBook.getAuthormail() %></li>
            <li>Published Year: <%= existingBook.getPublishedyear() %></li>
            <li>Available Count: <%= existingBook.getAvailablecount() %></li>
        </ul>
    <% } %>

    <!-- Update Name Input -->
    <label for="name">Update Book Name:</label>
    <input type="text" id="name" name="name" value="<%= (existingBook != null) ? existingBook.getName() : "" %>" required>
    <br>

    <!-- Update Author Input -->
    <label for="author">Update Author Name:</label>
    <input type="text" id="author" name="author" value="<%= (existingBook != null) ? existingBook.getAuthor() : "" %>" required>
    <br>

    <!-- Update Price Input -->
    <label for="price">Update Price:</label>
    <input type="number" id="price" name="price" value="<%= (existingBook != null) ? existingBook.getPrice() : "" %>" required>
    <br>

    <!-- Update Genre Input -->
    <label for="genre">Update Genre:</label>
    <input type="text" id="genre" name="genre" value="<%= (existingBook != null) ? existingBook.getGenre() : "" %>" required>
    <br>

    <!-- Update Author Mail Input -->
    <label for="authormail">Update Author Email:</label>
    <input type="email" id="authormail" name="authormail" value="<%= (existingBook != null) ? existingBook.getAuthormail() : "" %>" required>
    <br>

    <!-- Update Published Year Input -->
    <label for="publishedyear">Update Published Year:</label>
    <input type="number" id="publishedyear" name="publishedyear" value="<%= (existingBook != null) ? existingBook.getPublishedyear() : "" %>" required>
    <br>

    <!-- Update Available Count Input -->
    <label for="availablecount">Update Available Count:</label>
    <input type="number" id="availablecount" name="availablecount" value="<%= (existingBook != null) ? existingBook.getAvailablecount() : "" %>" required>
    <br>

    <!-- Submit Button -->
    <input type="submit" value="Update Book">
</form>

</body>
</html>
