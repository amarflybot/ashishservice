/**
 * Created by amarendra on 20/09/17.
 */
var myApp = angular.module('AmarApp');

myApp.service('PersonService', ['$http', function($http) {

    this.getName = function(){
        return "Ashish!!";
    }

    this.getAllPersons = function () {
        return $http.get('/api/msg/')
            .then(function(data) {
                return data;
            });
    }

    this.savePerson = function (person) {
        return $http.post('/api/msg/',[person])
            .then(function(data) {
                return data;
            });
    }

    this.add  = function () {
        return $http.get('/api/msg/add')
            .then(function(data) {
                return data;
            });
    }

}]);
