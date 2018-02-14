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
            $window.location.assign('http://localhost:9999/menu.jsp');
        },function (error){

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
app.controller('list', function($scope, $http){
    // Here the array would be your response.text:
    $scope.names = ['John', 'Jessie', 'Johanna', 'Joy', 'Mary', 'Peter', 'Sebastian', 'Erika', 'Patrick', 'Samantha'];
    $http({ method:'GET',
        url: "http://localhost:8080/users",
        headers: new Headers({
            'Content-Type': 'application/json'
        })
    }).then(function(response) {
        console.log("a intrat in then");
        $scope.users = response.data;
    });
});