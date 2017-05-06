/**
 * Dashboard Controller
 */

mainApp.controller('DashboardController', ['$scope', '$http', '$timeout', '$location', 'utilityService', '$route', function($scope, $http, $timeout, $location, utilityService, $route) {

    // Set content type for request
    $http.defaults.headers.post["Content-Type"] = "application/json";
    // Set api_key for logged user
    $http.defaults.headers.common["api_key"] = localStorage.getItem('token');

    var main = this;

    $scope.storagePath = 'https://image.tmdb.org/t/p/w500/';
    $scope.contentUrl = $route.current.$$route.contentUrl;
    $scope.movies = [];
    $scope.favmovies = [];
    $scope.favactors = [];
    $scope.tab_content = false;
    $scope.search_result = false;

    main.init = function(resource) {
        $scope.loading = true;
        main.getData(resource);
    }

    main.showContent = function() {
        $scope.loading = false;
    }

    main.getData = function(resource) {
        if(resource==="recommended")
            main.getRecommendedMovies()
        else if(resource==="favmovies")
            main.getFavmovies()
        else
            main.getFavactors()
    }

    main.getFavmovies = function() {

        // Get favmovies via ajax
        $http.get('/api/favmovies/').success(function(data) {
            $scope.favmovies = data.data;
        }).error(function (data, status) {
            console.log('Error getting favmovies: ' + data);
            $scope.favmovies = [];
        }).finally(function () {
            $scope.loading = false;
            $scope.tab_content = true;
            $scope.search_result = false;
        });
    }

    main.getFavactors = function() {

        // Get favactors via ajax
        $http.get('/api/favactors/').success(function(data) {
            $scope.favactors = data.data;
        }).error(function (data, status) {
            console.log('Error getting favactors: ' + data);
            $scope.favactors = [];
        }).finally(function () {
            $scope.loading = false;
            $scope.tab_content = true;
            $scope.search_result = false;
        });
    }

    main.getRecommendedMovies = function() {

        // Get recommended movies via ajax
        $http.get('/api/movies/recommended/').success(function(data) {
            $scope.movies = data.data;
            main.allContentLoaded = true;
        }).error(function (data, status) {
            console.log('Error getting recommended movies: ' + data);
            $scope.movies = [];

            if(data === "No se encuentra autenticado")
                $location.path("/logout");
        }).finally(function () {
            $scope.loading = false;
            $scope.tab_content = true;
            $scope.search_result = false;
        });
    }

    $scope.$on('search_result', function(evt, data){

        $scope.search_items = data;

        $scope.loading = false;
        $scope.tab_content = false;
        $scope.search_result = true;
    });

    main.init($route.current.$$route.resource);
}]);