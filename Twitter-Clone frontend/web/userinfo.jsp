<%--
  Created by IntelliJ IDEA.
  User: madalina.luca
  Date: 2/14/2018
  Time: 2:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.7/angular.min.js">
    </script>
    <script src="./scripts/login.js"></script>
    <link rel="stylesheet" type="text/css" href="./styles/index.css">
</head>
<body ng-app="myApp" ng-controller="userInfo">
<div data-ng-init="init()">
<p>
    {{userDetails.id }}
</p>
<p>
    {{userDetails.firstname }}
</p>
<p>
    {{userDetails.lastname }}
</p>
<p>
    {{userDetails.username }}
</p>
<p>
    {{userDetails.address }}
</p>
<p>
    {{userDetails.email }}
</p>
</div>
</body>
</html>
