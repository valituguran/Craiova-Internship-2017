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
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
</head>
<body ng-app="twitterclone">
This is the example for Madalina Luca,who think that the frontend will not work!
<div ng-controller="example">
    <p>The ID is {{rezultat}}</p>
</div>
<script>
    angular.module('twitterclone', [])
        .controller('example', function($scope, $http) {
            $http({
                method: 'GET',
                url: 'http://localhost:8080/5a7823e99b18960f18f4d409',
                headers: {'Authorization': 'application/json'}
            });
            then(function(response) {
                $scope.rezultat = response.data;
            });
        });
</script>
</body>
</html>
