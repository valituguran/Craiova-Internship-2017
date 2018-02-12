var app = angular.module('twitterclone',[]);
app.controller('registercontroller' ,function($scope,$http) {
    $scope.firstname = null;
    $scope.lastname = null;
    $scope.username = null;
    $scope.password = null;
    $scope.email = null;
    $scope.adress = null;
    $scope.mesaj = null;
    $scope.registerFunction = function (firstname,lastname,username,password,email,adress){
        var obj = {
            firstname : firstname,
            lastname : lastname,
            username : username,
            password : password,
            email : email,
            adress :adress
        };
        console.log("a intrat1");
        console.log(JSON.stringify(obj));
        $http({ method:'POST',
                url:'http://localhost:8080/register',
                data:JSON.stringify(obj),
                headers:{'Content-type':'application/json'}})
            .then(function (response){
                console.log("a intrat");
                $scope.mesaj = JSON.parse(response);
        }).catch(function (error){
            $scope.error = error;
            console.log(error);
        });
    };
});

