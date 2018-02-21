
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
        sessionStorage.clear();
        $window.location.assign('http://localhost:9999/index.jsp');
    }
})


app.controller('userInfo',['$scope', '$http', '$window', function($scope, $http, $window){

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



    $scope.accountdetails = function(id) {
        var string ='http://localhost:8080/' + id;
        console.log(string);
        $http({
            method: 'GET',
            url: string,
            headers: new Headers({
                'Content-Type': 'application/json'
            })
        }).then(function (response) {
            if (response.data == '') {
                window.alert("A aparut o problema! Reincarcati pagina! ");
            }
            else {
                userDetails = response.data;
                sessionStorage.setItem("accountDetails",JSON.stringify(response.data));
                $scope.userDetails = response.data;
                console.log(sessionStorage.getItem("accountDetails"));
                $window.location.assign('http://localhost:9999/userinfo.jsp');
            }
        });
    };

    var init = function () {
        var userDetails = JSON.parse(sessionStorage.getItem("accountDetails"));
        $scope.userDetails = userDetails;
    }
    init();




    $scope.followfunction = function (followID) {

        console.log("fdsddsf" + followID);
        var userwhoislogin = JSON.parse(sessionStorage.getItem("userwhoislogin"));
        $http({
            url: 'http://localhost:8080/follow/',
            method: "GET",
            params: {
                id_follow: followID,
                id_user: userwhoislogin.id
            }
        }).then(function (response) {
            console.log(response.status);
            if(response.status  == 200){
                console.log("User-ul curent:" + userwhoislogin.username + "este conectat cu user-ul:" + response.data.username);
                window.alert("You are now connected with:" + followID);
            }
            else{
                window.alert("Error!");
            }
        });
    };
}]);
