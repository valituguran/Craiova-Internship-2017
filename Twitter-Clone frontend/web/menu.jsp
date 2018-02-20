<%--
  Created by IntelliJ IDEA.
  User: madalina.luca
  Date: 2/14/2018
  Time: 10:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Twitter Clone</title>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.7/angular.min.js">
    </script>
    <script src="./scripts/login.js"></script>
    <script src="./scripts/registercontroller.js"></script>
    <link rel="stylesheet" type="text/css" href="./styles/index.css">
</head>
<body ng-app="myApp">

<div class="header">
    <h2>twitterClone</h2>
</div>

<div class="topnav">
    <div ng-app="myApp" ng-controller="logoutController">
        <div style="float:right">
            <p ng-controller="userInfo">{{userwhoislogin.username}}<input type="button" value="Log Out" ng-click="logout()"/> </p>
        </div>
    </div>
</div>

<div class="row">
    <div class="leftcolumn">
        <div class="card">
            <h2>TITLE HEADING</h2>
            <h5>Title description, Dec 7, 2017</h5>
            <div class="fakeimg" style="height:200px;">Image</div>
            <p>Some text..</p>
            <p>Sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco.</p>
        </div>
        <div class="card">
            <h2>TITLE HEADING</h2>
            <h5>Title description, Sep 2, 2017</h5>
            <div class="fakeimg" style="height:200px;">Image</div>
            <p>Some text..</p>
            <p>Sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco.</p>
        </div>
    </div>
    <div class="rightcolumn">
        <div class="card">
            <h3>News on Twitter </h3>
            <div ng-controller="userInfo">
                <ul>
                    <li ng-repeat="user in users | limitTo:5 | orderBy:'time':true">
                        <a href="#">{{user.firstname}}</a><br>
                        <img src="data:image/png;base64,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" >
                        <br><button> Connect</button>
                    </li>
                </ul>
            </div>
        </div>
        <div class="card2">
            <div class="card">
                <h3>Popular Post</h3>
                <div class="fakeimg"><p>Image</p></div>
                <div class="fakeimg"><p>Image</p></div>
                <div class="fakeimg"><p>Image</p></div>
            </div>
            <div class="card">
                <h3>Follow Me</h3>
                <p>Some text..</p>
            </div>
        </div>
    </div>
</div>

<div class="footer">
    <h2>Footer</h2>
</div>

</body>
</html>
