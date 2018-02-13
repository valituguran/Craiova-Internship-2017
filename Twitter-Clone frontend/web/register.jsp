<%--
  Created by IntelliJ IDEA.
  User: lucian.Nicolescu
  Date: 2/6/2018
  Time: 11:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.7/angular.min.js">
    </script>
    <link rel="stylesheet"  type="text/css" href="./styles/register.css">
    <script src="./scripts/registercontroller.js"></script>
</head>
<body ng-app="twitterclone" ng-controller="registercontroller">

<div class="header">
    <h2>twitterClone</h2>

</div>
<div class="register">
<form>
    <p><label for="firstname">Frist Name:</label></p>
    <input type="text"  ng-model="firstname" name="firstname" id="firstname" class="txt" /><br>
    <p><label for="lastname">Last Name:</label></p>
    <input type="text"  ng-model="lastname"  name="lastname" id="lastname" class="txt" /><br>
    <p><label for="username">Username:</label></p>
    <input type="text"  ng-model="username"  name="username" id="username" class="txt" /><br>
    <p><label for="email">Email Address:</label></p>
    <input type="text"  ng-model="email"  name="email" id="email" class="txt" /><br>
    <p><label for="password">Password:</label></p>
    <input type="password"  ng-model="password"  name="password" id="password" class="txt" /><br>
    <p><label for="address">Address:</label></p>
    <input type="text"  ng-model="adress"  name="adress" id="address" class="txt" /><br>
    <input type="button"  ng-click="registerFunction(firstname, lastname, username, password, email, adress)"  name="btnSubmit" id="btnSubmit" value="Sign Up!" class="btn" />
</form>
</div>
</body>
</html>

