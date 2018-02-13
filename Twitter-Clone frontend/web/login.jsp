<%--
  Created by IntelliJ IDEA.
  User: madalina.luca
  Date: 2/5/2018
  Time: 4:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.7/angular.min.js"></script>
    <script src="./scripts/login.js"></script>
    <link rel="stylesheet"  type="text/css" href="./styles/login.css">
</head>
<body ng-app="myApp" ng-controller="logincontroller" onkeydown = "if (event.keyCode == 13) document.getElementById('button-login').click()">

<div class="header">
    <h2>twitterClone</h2>
</div>

<form>
<div class="login">
    <input type="text" placeholder="username" ng-model="username" id="username">
    <input type="password" ng-model="password" placeholder="password" id="password">
    <a href="#" class="forgot">forgot password?</a>
    <input type="button" ng-click="login(username, password)"  name="btnSubmit" id="btnSubmit" value="Sign In">
    <p>or</p>
    <a href="http://localhost:9999/register.jsp" class="signUp">Sign Up</a>
</div>
</form>
<div class="shadow"></div>
</body>
</html>


