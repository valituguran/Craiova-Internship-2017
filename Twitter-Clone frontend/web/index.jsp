<%--
  Created by IntelliJ IDEA.
  User: lucian.Nicolescu
  Date: 2/5/2018
  Time: 4:00 PM
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
<body>

<div class="header">
  <h2>twitterClone</h2>

</div>
<div class="topnav">
  <div ng-app="myApp" ng-controller="logincontroller">
    <div style="float:right">
      <p> Username : <input ng-model="username" /></p>
      <p>Password : <input type=password ng-model="password" /></p>
      <p><input type="button" value="Login" ng-click="login(username, password)" /> </p>
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
      <div ng-app="twitterclone" ng-controller="registercontroller">
        <div class="register">
          <h2> Creeaza un cont nou</h2>
          <input ng-model="firstname" placeholder="Nume"/>
          <input ng-model="lastname" placeholder="Prenumele"/>
          <input ng-model="username" placeholder="UserName"/>
          <input type=password ng-model="password" placeholder="Parola"/>
          <input ng-model="email" placeholder="Email sau numar de telefon"/>
          <input ng-model="adress" placeholder="Adresa"/>
          <input type="button" value="Register" ng-click="registerFunction(firstname, lastname, username, password, email, adress, followers, following)" />
        </div>
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
