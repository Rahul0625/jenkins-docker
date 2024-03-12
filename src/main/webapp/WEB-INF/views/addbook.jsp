<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Book</title>
</head>
<body>

    <h2>Add Book</h2>
    <form:form modelAttribute="item" action="addBook" method="post">
        <p> ${BookForm}</p>

        <!-- Name Input -->
        <label for="name">Name:</label>
        <form:input type="text" path="name" id="name" name="name" placeholder="Enter Name" />
        <br />
        <p>
            <form:errors path="name" />
        </p>

        <!-- Author Input -->
        <label for="author">Author Name:</label>
        <form:input type="text" path="author" id="author" name="author" placeholder="Enter Author Name" />
        <br />
        <p>
            <form:errors path="author" />
        </p>

        <!-- Price Input -->
        <label for="price">Price:</label>
        <form:input type="number" path="price" id="price" name="price" placeholder="Enter Price" />
        <br />
        <p>
            <form:errors path="price" />
        </p>

        <!-- Genre Input -->
        <label for="genre">Genre:</label>
        <form:input type="text" path="genre" id="genre" name="genre" placeholder="Enter Genre" />
        <br />
        <p>
            <form:errors path="genre" />
        </p>

        <!-- Author Mail Input -->
        <label for="authormail">Author Email:</label>
        <form:input type="text" path="authormail" id="authormail" name="authormail" placeholder="Enter Author Email" />
        <br />
        <p>
            <form:errors path="authormail" />
        </p>

        <!-- Published Year Input -->
        <label for="publishedyear">Published Year:</label>
        <form:input type="number" path="publishedyear" id="publishedyear" name="publishedyear" placeholder="Enter Published Year" />
        <br />
        <p>
            <form:errors path="publishedyear" />
        </p>

        <!-- Available Count Input -->
        <label for="availablecount">Available Count:</label>
        <form:input type="number" path="availablecount" id="availablecount" name="availablecount" placeholder="Enter Available Count" />
        <br />
        <p>
            <form:errors path="availablecount" />
        </p>

        <!-- Submit Button -->
        <input type="submit" value="AddBook">
    </form:form>

</body>
</html>
