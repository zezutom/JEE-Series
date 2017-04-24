var eShop = angular.module('eShop', []);

eShop.controller('CategoryController', function ($scope, $http) {

    $scope.categories = [];
    
    $scope.loadAll = function () {
        $http.get('resources/categories').then(function(response) {
            var data = response.data;
            if (data && data.length > 0) {
                $scope.categories.push.apply($scope.categories, data);
            }
        });
    };

    // Load all categories
    $scope.loadAll();
});