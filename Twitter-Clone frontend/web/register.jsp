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

<form>
    <fieldset>
        <legend>Registration Form</legend>
        <div>
            <label for="fullname">Frist Name:</label>
            <input type="text"  ng-model="firstname" name="firstname" id="fullname" class="txt" />
        </div>
        <div>
            <label for="fullname">Last Name:</label>
            <input type="text"  ng-model="lastname"  name="lastname" id="fullname" class="txt" />
        </div>
        <div>
            <label for="fullname">Username:</label>
            <input type="text"  ng-model="username"  name="username" id="fullname" class="txt" />
        </div>
        <div>
            <label for="email">Email Address:</label>
            <input type="text"  ng-model="email"  name="email" id="email" class="txt" />
        </div>
        <div>
            <label for="password1">Password:</label>
            <input type="password"  ng-model="password"  name="password" id="password1" class="txt" />
        </div>
        <div>
            <label for="password1">Adress:</label>
            <input type="text"  ng-model="adress"  name="adress" id="password1" class="txt" />
        </div>
    </fieldset>
    <div>
        <input type="button"  ng-click="registerFunction(firstname, lastname, username, password, email, adress)"  name="btnSubmit" id="btnSubmit" value="Sign Up!" class="btn" />
    </div>
</form>
</body>
</html>

