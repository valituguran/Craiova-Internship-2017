<%@ page import="com.ymens.servlet.AddBookServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../styles/layout_login.css" type="text/css">
    <title>Title</title>
</head>
<body>
<form action="addauthorServlet" method="post">
    <header>Add author details</header>

    <label for="name"> Name :</label>
    <input type="text" id="name" name="name" required="required">

    <label for="nationality"> Nationality :</label>
    <input type="text" id="nationality" name="nationality" required="required">

    <label for="description_author"> Description :</label>
    <input type="text" id="description_author" name="description_author" required="required">

    <label for="age"> Age:</label>
    <input type="text" id="age" name="age" required="required">

    <button type="submit">Add</button>
</form>
</body>
</html>