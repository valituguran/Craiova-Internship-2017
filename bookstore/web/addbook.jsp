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
            <form name="addbook_form" action="addbookServlet" method="post">
                <h1>Add a book</h1>
                <fieldset>
                    <label for="name"> Name:</label>
                    <input type="text" id="name" name="name" required="required">

                    <label for="cnp">CNP Author:</label>
                    <input type="text" id="cnp" name="cnp" required="required">

                    <label for="isbn"> ISBN:</label>
                    <input type="text" id="isbn" name="isbn" required="required">

                    <label for="price"> Price:</label>
                    <input type="text" id="price" name="price" required="required">

                    <label for="description"> Description:</label>
                    <input type="text" id="description" name="description" required="required">

                    <p style="color:black;">Please specify a image:</p><br>
                    <input type="file" name="image" size="40" accept="image/*" style="color:black;">


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
