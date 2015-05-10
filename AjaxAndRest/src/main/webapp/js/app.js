var ComposerTributeApp = angular.module('ComposerTributeApp', []);

ComposerTributeApp.controller('ComposerListController', function($scope, $http) {
    $http.get('resources/composers').success(function(data) {
        $scope.composers = data;
    }).error(function() {
        console.log('There was an error with the API call.')
    });
});

