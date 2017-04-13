var ComposerTributeApp = angular.module('ComposerTributeApp', ['infinite-scroll']);

ComposerTributeApp.controller('ComposerListController', function ($scope, $http) {

    $scope.composers = [];
    $scope.page = 0;
    $scope.busy = false;
    $scope.fullyLoaded = false;

    $scope.sortOptions = [
        {name: 'Name A-Z',  expr: 'lastName:asc'},
        {name: 'Name Z-A',  expr: 'lastName:desc'},
        {name: 'Genre A-Z', expr: 'genre:asc,lastName:asc'},        
        {name: 'Genre Z-A', expr: 'genre:desc,lastName:asc'}        
    ];
    $scope.selectedSort = $scope.sortOptions[0];
    
    $scope.selectSort = function() {
        $scope.composers = [];
        $scope.page = 0;
        $scope.busy = false;
        $scope.fullyLoaded = false;
        $scope.nextPage();
    };
    
    $scope.nextPage = function () {
        if ($scope.busy || $scope.fullyLoaded)
            return;
        $scope.busy = true;
        $http.get('resources/composers/list', {
            params: {
                page: $scope.page,
                sortBy: $scope.selectedSort.expr
            }
        }).then(function (response) {
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

    $scope.filterComposers = function (val) {
        var normalize = function (str) {
            return (str === undefined) ? "" : str.toLowerCase().trim();
        };
        var filter = function (composer) {
            return normalize(composer.firstName + composer.lastName).indexOf(normalize(val)) >= 0;
        };
        var composerExists = $scope.composers.filter(filter).length > 0;
        if (val === undefined || composerExists || $scope.busy || $scope.fullyLoaded) {
            return filter;
        } else {
            $scope.busy = true;
            return $http.get('resources/composers/filter', {
                params: {
                    query: val
                }
            }).then(function (response) {
                $scope.busy = false;
                var data = response.data;
                if (data && data.length > 0) {
                    $scope.composers.push.apply($scope.composers, data);                
                } else {
                    $scope.fullyLoaded = true;
                }
                return response.data.map(function (item) {
                    return item;
                });                
            });
        }
    };

    // Load the first page
    $scope.nextPage();
});