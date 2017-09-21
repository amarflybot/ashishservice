/**
 * Created by amarendra on 20/09/17.
 */
var myApp = angular.module('AmarApp');

myApp.controller('PersonController', ['$scope', 'PersonService', function ($scope, PersonService) {
    $scope.greeting = PersonService.getName();

    $scope.persons = [];

    $scope.getAll = function () {
        PersonService.getAllPersons()
            .then(function (data) {
                $scope.persons = data.data;
                console.log("Got Persons " + JSON.stringify($scope.persons));
            });
    }

    $scope.savePerson = function (person) {
        PersonService.savePerson(person)
            .then(function (data) {
                console.log("Person saved: " + JSON.stringify(data));
                this.getAll();
            })
    }

    $scope.addPerson = function () {
        PersonService.add().then(function (data) {
            console.log(JSON.stringify(data));
            PersonService.getAllPersons()
                .then(function (data) {
                    $scope.persons = data.data;
                    console.log("Got Persons " + JSON.stringify($scope.persons));
                });
        });

    }

}]);
