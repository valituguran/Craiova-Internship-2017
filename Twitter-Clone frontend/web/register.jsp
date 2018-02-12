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
    <script src="./scripts/registercontroller.js"></script>
</head>
<body>
<div ng-app="twitterclone" ng-controller="registercontroller">

    <div>
        First Name : <input ng-model="firstname" /><br/><br/>
        Last Name : <input ng-model="lastname" /><br/><br/>
        Username : <input ng-model="username" /><br/><br/>
        Password : <input type=password ng-model="password" /><br/><br/>
        Email : <input ng-model="email" /><br/><br/>
        Adress : <input ng-model="adress" /><br/><br/>
        <input type="button" value="Register" ng-click="registerFunction(firstname, lastname, username, password, email, adress)" /> <br/><br/>
    </div>

    <p>Output Message : {{mesaj}}</p>

</div>
</body>
</html>
