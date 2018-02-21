
var app =  angular.module('myApp', []);

app.controller('logincontroller',function($scope,$http,$window) {
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
                sessionStorage.setItem("userwhoislogin",JSON.stringify(response.data));
                $window.location.assign('http://localhost:9999/menu.jsp');
            }
        });
    };
});


app.controller('logoutController', function($scope, $window){
    $scope.logout = function(){
        $scope.username = '';
        $scope.password = '';
        $window.location.assign('http://localhost:9999/index.jsp');
    }
})


app.controller('userInfo',function($scope, $http){


    $http({ method:'GET',
        url: "http://localhost:8080/users",
        headers: new Headers({
            'Content-Type': 'application/json'
        })
    }).then(function(response) {
        $scope.users = response.data;
        $scope.userwhoislogin = JSON.parse(sessionStorage.getItem("userwhoislogin"));
        console.log("User-ul care este logat este:" +$scope.userwhoislogin.username);
    });

    $scope.userID = null;

    $scope.accountdetails = function (userID) {
        $http({
            method: 'GET',
            url: 'http://localhost:8080',
            params: {
                id: userID
            }
        }).then(function (response) {
            if (response.data == '') {
                console.log("aaaaa");
            }
            else {
                $scope.user = response.data;
                $window.location.assign('http://localhost:9999/userinfo.jsp');
            }
        });
    };




    $scope.followfunction = function (userID) {

        var userwhoislogin = JSON.parse(sessionStorage.getItem("userwhoislogin"));
        $http({
            url: 'http://localhost:8080/follow/',
            method: "GET",
            params: {
                id_follow: userID,
                id_user: userwhoislogin.id
            }
        }).then(function (response) {
            console.log("User-ul curent:" + userwhoislogin.username + "este conectat cu user-ul:" + response.data.username);
        });
    };


});
