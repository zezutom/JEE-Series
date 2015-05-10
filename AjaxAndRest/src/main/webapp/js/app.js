var ComposerTributeApp = angular.module('ComposerTributeApp', []);

ComposerTributeApp.controller('ComposerListController', function($scope, $http) {
    $http.get('resources/composers').success(function(data) {
        $scope.composers = data;
    }).error(function() {
        console.log('There was an error with the API call.')
    });
});

ComposerTributeApp.filter('capitalize', function() {
    // Credit: http://codepen.io/WinterJoey/pen/sfFaK
    return function(input, all) {
      return (!!input) ? input.replace(/([^\W_]+[^\s-]*) */g, 
        function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();}) : '';
    };
});

