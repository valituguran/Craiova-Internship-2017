var app =  angular.module('myApp', []);
app.controller('logincontroller' ,function($scope,$http, $window) {

    $scope.username = null;
    $scope.password = null;
    $scope.login = function (username,password){
        var obj = {
            username : username,
            password : password
        };
        console.log("a intrat1");
        console.log(JSON.stringify(obj));
        var string = "http://localhost:8080/login?username="+ $scope.username + "&password="+ $scope.password;
        console.log(string);

        $http({ method:'GET',
            url: string,
            headers: new Headers({
                'Content-Type': 'application/json'
            }),
            body: JSON.stringify(obj)
        }).then(function (success){
            $window.location.assign('http://localhost:9999/index.jsp');
        },function (error){

        });

    };
});

