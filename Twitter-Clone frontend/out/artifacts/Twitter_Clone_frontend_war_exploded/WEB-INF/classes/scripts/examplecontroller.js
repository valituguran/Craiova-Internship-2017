angular.module('twitterclone', [])
    .controller('example', function($scope, $http) {
        $http.get('http://localhost:8080/5a7823e99b18960f18f4d409').
        then(function(response) {
            $scope.rezultat = response;
        });
    });