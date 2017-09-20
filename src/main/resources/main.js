var myApp = angular.module('AmarApp',[]);

myApp.controller('GreetingController', ['$scope', function($scope) {
    $scope.greeting = 'Ashish!!';
}]);