<%--
  Created by IntelliJ IDEA.
  User: madalina.luca
  Date: 7/27/2017
  Time: 3:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../styles/layout.css" type="text/css">
    <title>Title</title>
    <script>

    </script>
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
            <form name="register_form" action="registerServlet" method="post">
                <h1>Register</h1>

                <fieldset>

                    <label for="username"> Name:</label>
                    <input type="text" id="username" name="username" required="required">



                    <label for="userpass"> Password:</label>
                    <input type="password" id="userpass" name="userpass" required="required">



                    <label for="email"> Email:</label>
                    <input type="text" id="email" name="email" required="required">

                    <label for="realname"> Realname:</label>
                    <input type="text" id="realname" name="realname" required="required">
                </fieldset>

                <button type="submit">Register</button>
            </form>
        </div><!-- container -->
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
