var app =  angular.module('myApp', []);
var userwhoislogin = null;
app.controller('logincontroller' ,function($scope,$http, $window) {
    $scope.username = null;
    $scope.password = null;

    $scope.login = function (username,password){
        $http({ method:'GET',
            url: 'http://localhost:8080/login',
            params: {
                username: username,
                password: password
            }
        }).then(function (response){
            if(response.data == ''){
                $scope.message = "Username or password are wrong!"
                window.alert($scope.message);
                console.log($scope.message);
            }
            else {
                console.log("succesfully login!");
                $scope.userwhoislogin = response.data;
                $window.location.assign('http://localhost:9999/menu.jsp');
            }
        });
    };

    $scope.followfunction = function () {
        console.log("am intrat in functia de follow");
        console.log($scope.userwhoislogin);
        $http({
            url: 'http://localhost:8080/follow/',
            method: "GET",
            params: {
                id_follow: '5a7823c39b18960f18f4d408',
                id_user: $scope.userwhoislogin
            }
        }).then(function (success) {
            console.log("operatiunea s-a finalizat cu succes!");
            $scope.userwhoislogin = success.data;
        }, function (error) {
            console.log("operatiunea s-a finalizat cu eroare!");
        });
    }
});
app.controller('logoutController', function($scope, $window){
    $scope.logout = function(){
        $scope.username = '';
        $scope.password = '';
        $window.location.assign('http://localhost:9999/index.jsp');
    }
})


app.controller('userInfo', function($scope, $http){
    $http({ method:'GET',
        url: "http://localhost:8080/users",
        headers: new Headers({
            'Content-Type': 'application/json'
        })
    }).then(function(response) {
        $scope.users = response.data;
        console.log(userwhoislogin);
    });
});