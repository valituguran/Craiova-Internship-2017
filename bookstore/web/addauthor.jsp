
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../styles/layout_login.css" type="text/css">
    <title>Title</title>
</head>
<body>
<form action="addauthorServlet" method="post">
    <header>Adauga autor</header>

    <label for="name"> Nume :</label>
    <input type="text" id="name" name="name" required="required">

    <label for="nationality"> Nationalitate :</label>
    <input type="text" id="nationality" name="nationality" required="required">

    <label for="description_author"> Descriere autor :</label>
    <input type="text" id="description_author" name="description_author" required="required">

    <label for="age"> Varsta:</label>
    <input type="text" id="age" name="age" required="required">

    <button type="submit">Add</button>
</form>
</body>
</html>