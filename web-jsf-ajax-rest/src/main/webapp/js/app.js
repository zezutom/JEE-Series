var ComposerTributeApp = angular.module('ComposerTributeApp', ['infinite-scroll', 'ui.bootstrap']);

ComposerTributeApp.controller('ComposerListController', function ($scope, $http) {
    $scope.sortByOptions = [
        {name: 'Surname', value: 'lastName'},
        {name: 'Genre', value: 'genre'},
        {name: 'Birthday', value: 'born'}
    ];

    var init = function () {
        $scope.composers = [];
        $scope.selectedComposer = undefined;
        $scope.page = 0;
        $scope.busy = false;
        $scope.fullyLoaded = false;
    };


    $scope.nextPage = function () {
        if ($scope.busy || $scope.fullyLoaded)
            return;
        $scope.busy = true;
        $http.get('resources/composers/list', {
            params: {
                page: $scope.page
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
        var filtered = $scope.composers.filter(function(composer) {
            return composer.lastName.toLowerCase().indexOf(val.trim().toLowerCase()) >= 0;
        });
        if (filtered.length > 0) return filtered;
        
        $scope.busy = true;
        return $http.get('resources/composers/filter', {
            params: {
                query: val
            }
        }).then(function (response) {
            return response.data.map(function (item) {
                return item;
            });
            $scope.busy = false;
        });
    };

    $scope.onSelect = function ($item, $model, $label) {
//        if ($scope.busy) return;

        $scope.busy = true;
        $scope.composers = [];
        $http.get('resources/composers/' + $item.id).then(function (response) {
            var data = response.data;
            if (data && data.length > 0) {
                $scope.composers.push.apply($scope.composers, data);
            }
            $scope.busy = false;
        });
    };
    
    $scope.clearFilter = function() {
        init();
        $scope.nextPage();
    };
    
    // Initialise the model
    init();

    // Load the first page
    $scope.nextPage();
});