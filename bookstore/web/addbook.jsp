<%@ page import="com.ymens.servlet.AddBookServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../styles/layout_login.css" type="text/css">
    <title>Title</title>
</head>
<body>
<form action="addbookServlet" method="post" enctype="multipart/form-data">
    <header>Add a book</header>
    <label for="name"> Name:</label>
    <input type="text" id="name" name="name" required="required" />

    <label for="cnp">CNP Author:</label>
    <input type="text" id="cnp" name="cnp" required="required" />

        <label for="isbn"> ISBN:</label>
        <input type="text" id="isbn" name="isbn" required="required" />

        <label for="price"> Price:</label>
        <input type="text" id="price" name="price" required="required" />

        <label for="description"> Description:</label>
        <input type="text" id="description" name="description" required="required" />

        <p style="color:black;">Please specify a image:</p><br>
    <input type="file" name="image" id="file" size="40" style="color:black;" required="required" />
    <button type="submit" >Add</button>
</form>
<script>



</script>
</body>
</html>
