var ComposerTributeApp = angular.module('ComposerTributeApp', []);

ComposerTributeApp.controller('ComposerListController', function($scope, $http) {  
    $scope.sortByOptions = [
        {name: 'Surname', value: 'lname'},
        {name: 'Genre', value: 'genre'},
        {name: 'Birthday', value: 'born'}
    ];
    
    $scope.filterByGenreDefault = {name: 'All', value: ' '};
    
    $http.get('resources/composers').success(function(data) {
        $scope.composers = data;
    });            
    $http.get('resources/genres').success(function(data) {
        $scope.genres = data;
    });            
});

ComposerTributeApp.filter('capitalize', function() {
    // Credit: http://codepen.io/WinterJoey/pen/sfFaK
    return function(input, all) {
      return (!!input) ? input.replace('_', ' ').replace(/([^\W_]+[^\s-]*) */g, 
        function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();}) : '';
    };
});

