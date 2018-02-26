var app = angular.module('twitterclone',[]);
app.controller('registercontroller' ,function($scope,$http) {
    $scope.firstname = null;
    $scope.lastname = null;
    $scope.username = null;
    $scope.password = null;
    $scope.email = null;
    $scope.adress = null;
    $scope.mesaj = null;
    $scope.image = null;
    $scope.registerFunction = function (firstname,lastname,username,password,email,adress, image){
        var obj = {
            firstname : firstname,
            lastname : lastname,
            username : username,
            password : password,
            email : email,
            adress :adress,
            image: image
        };
        console.log("image: "+image);
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
(function () {
    'use strict';
    angular.module('ng-file-model', [])
        .directive("ngFileModel", [function () {
            return {
                scope: {
                    ngFileModel: "="
                },
                link: function (scope, element) {
                    element.bind("change", function (changeEvent) {
                        var reader = new FileReader();
                        reader.onload = function (loadEvent) {
                            scope.$apply(function () {
                                scope.ngFileModel = {
                                    lastModified: changeEvent.target.files[0].lastModified,
                                    lastModifiedDate: changeEvent.target.files[0].lastModifiedDate,
                                    name: changeEvent.target.files[0].name,
                                    size: changeEvent.target.files[0].size,
                                    type: changeEvent.target.files[0].type,
                                    data: loadEvent.target.result
                                };
                                console.log($scope.ngFileModel);
                            });
                        };
                        reader.readAsDataURL(changeEvent.target.files[0]);
                    });
                }
            }
        }]);
})();
angular.module("twitterclone", ["ng-file-model"])
    .controller("fileCtrl",["$scope", function($scope){
        $scope.submittedFile = {};
        $scope.obj = {};
        $scope.submit = function(obj){
            console.log(JSON.stringify(obj.testFile))
        }
    }]);