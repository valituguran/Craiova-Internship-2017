<%--
  Created by IntelliJ IDEA.
  User: lucian.Nicolescu
  Date: 8/23/2017
  Time: 1:56 PM
  To change this template use File | Settings | File Templates.
--%>
<!---------------------------------------------------------->
<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8">
    <head>
        <link rel="stylesheet" type="text/css" href="../StyleSheet/loginStyle.css">
    </head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="../StyleSheet/loginStyle.css">
</head>
<body>
<body class="align">
<div class="grid">
    <form action="loginServlet" method="get" class="form login">
        <header class="login__header">
            <h3 class="login__title">Login</h3>
        </header>
        <div class="login__body">
            <div class="form__field">
                <input name="name" type="username" placeholder="Username" required>
            </div>
            <div class="form__field">
                <input name="password" type="password" placeholder="Password" required>
            </div>
        </div>
        <footer class="login__footer">
            <input type="submit" value="Login">
        </footer>
    </form>
</div>
</body>
</body>
</html>
