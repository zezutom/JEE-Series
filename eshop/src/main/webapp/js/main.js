var eShop = angular.module('eShop', ['ngRoute']);

// Configure routing
eShop.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'pages/home.html',
            controller: 'categoryController'
        })
        .when('/category', {
            templateUrl: 'pages/category.html',
            controller: 'categoryController'
        });
});

eShop.controller('categoryController', function ($scope, $http, $location) {

    $scope.location = $location;
    $scope.$watch('location.search()', function() {
        $scope.selectedId = +(($location.search()).id);
    }, true);

    $scope.categories = [];

    $scope.loadAll = function () {
        $http.get('resources/categories').then(function (response) {
            var data = response.data;
            if (data && data.length > 0) {
                $scope.categories.push.apply($scope.categories, data);
            }
        });
    };

    // Load all categories
    $scope.loadAll();
});