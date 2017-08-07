<%@ page import="com.ymens.servlet.AddBookServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../styles/layout.css" type="text/css">
    <title>Title</title>
</head>
<body>
<div class="wrapper row1">
    <header id="header" class="clear">
        <div id="hgroup">
            <h1><a href="#">BOOKSTORE</a></h1>
        </div>
    </header>
</div>
<div class="wrapper row2">
    <div id="container" class="clear">
        <div class="container">
            <form name="addbook_form" action="addauthorServlet" method="post">
                <h1>Add author details</h1>
                <fieldset>
                    <label for="name"> Name :</label>
                    <input type="text" id="name" name="name" required="required">

                    <label for="nationality"> Nationality :</label>
                    <input type="text" id="nationality" name="nationality" required="required">

                    <label for="description_author"> Description :</label>
                    <input type="text" id="description_author" name="description_author" required="required">

                    <label for="age"> Age:</label>
                    <input type="text" id="age" name="age" required="required">

                </fieldset>
                <button type="submit">Add</button>
            </form>
        </div>
    </div>
</div>

<div class="wrapper row3">
    <footer id="footer" class="clear">
        <p class="fl_left">Copyright &copy; - All Rights Reserved - <a href="www.ymens.com">Ymens Homepage</a></p>
        <p class="fl_right"> Bookstore</p>
    </footer>
</div>
</body>
</html>