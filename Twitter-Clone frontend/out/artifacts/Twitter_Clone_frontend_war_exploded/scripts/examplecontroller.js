var app =  angular.module('twitterclone', []);
app.controller('example', function($scope, $http) {
    console.log("a intat in get");
    $http.get('http://localhost:8080/5a7823e99b18960f18f4d409',{headers:{'Content-type':'application/json'}}).
    then(function(response) {
        console.log("a intrat in then");
        $scope.r = response.data;
        $scope.exemplu = "exemplu";
        console.log("a intat in get");
        console.log($scope.rezultat + "dfgdsds");
        console.log(response.data + "fgdssdf");
    });
});