var ComposerTributeApp = angular.module('ComposerTributeApp', ['infinite-scroll']);

ComposerTributeApp.controller('ComposerListController', function($scope, $http) {  
    $scope.sortByOptions = [
        {name: 'Surname', value: 'lastName'},
        {name: 'Genre', value: 'genre'},
        {name: 'Birthday', value: 'born'}
    ];
    
    $scope.filterByGenreDefault = {name: 'All', value: ' '};
    $scope.composers = [];
    $scope.page = 0;    
    $scope.busy = false;
    $scope.fullyLoaded = false;
    
//    $http.get('resources/genres').success(function(data) {
//        $scope.genres = data;
//    });
    
    $scope.nextPage = function() {
        if ($scope.busy || $scope.fullyLoaded) return;
        $scope.busy = true;
        $http.get('resources/composers/' + $scope.page).then(function(response) {
            var data = response.data;
            if (data && data.length > 0) {
                $scope.composers.push.apply($scope.composers, data);
                $scope.page++;
            } else {
                $scope.fullyLoaded = true;
            }
            $scope.busy = false;
        });         
    };
    // Load the first page
    $scope.nextPage();
});