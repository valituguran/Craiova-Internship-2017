<%--
  Created by IntelliJ IDEA.
  User: lucian.Nicolescu
  Date: 2/5/2018
  Time: 4:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Example</title>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js">
    </script>
    <script src="./scripts/examplecontroller.js"></script>
</head>
<body>
<div ng-app="twitterclone" ng-controller="example" >
    <p >The ID is: {{r.id}}</p><br>
    <p >The First Name is: {{r.firstname}} </p><br>
    <p >The Second Name is: {{r.lastname}} </p><br>
    <p >The Password is: {{r.password}} </p><br>
    <p >The Email is: {{r.email}} </p><br>
    <p >The Adress is: {{r.adress}} </p><br>
    <p >The Username is: {{r.username}} </p><br>
</div>
<script>

</script>
</body>
</html>
