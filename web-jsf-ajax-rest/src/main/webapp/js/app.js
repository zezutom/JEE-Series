var ComposerTributeApp = angular.module('ComposerTributeApp', ['infinite-scroll']);

ComposerTributeApp.controller('ComposerListController', function ($scope, $http) {
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
    $scope.searchText;
    
    var getUrl = function() {
      var baseUrl = 'resources/composers'; 
      return (!$scope.searchText || $scope.searchText.length === 0) ? baseUrl + '/' + $scope.page : baseUrl + '/' + $scope.searchText + '/' + $scope.page; 
    };
    
    $scope.nextPage = function () {
        if ($scope.busy || $scope.fullyLoaded)
            return;
        $scope.busy = true;
        $http.get(getUrl()).then(function (response) {
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

    $scope.applyFilter = function() {
        $scope.page = 0;
        $scope.composers = [];
        $scope.nextPage();
    };
    
    // Load the first page
    $scope.nextPage();
});