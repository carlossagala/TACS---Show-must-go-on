/**
 * Dashboard Controller
 */

mainApp.controller('DashboardController', ['$scope', '$http', '$timeout', 'utilityService', function($scope, $http, $timeout, utilityService) {

    $http.defaults.headers.post["Content-Type"] = "application/json";

    var main = this;
    var storagePath = 'https://image.tmdb.org/t/p/w500/';

    $scope.movies = [];
    $scope.favmovies = [];
    $scope.favactors = [];

    main.init = function() {
        //Get data
        main.getRecommendedMovies();
        main.getFavmovies();
        main.getFavactors();

        $timeout(main.showContent, 2000);
    }

    main.showContent = function() {
        main.loading = false;
        main.preloaded = true;
    }

    main.getFavmovies = function() {

        // Get favmovies via ajax
        $http.get('/api/favmovies/').success(function(data) {
            $scope.favmovies = data.data;
        }).error(function (data, status) {
            console.log('Error getting favmovies: ' + data);
        });

        return $scope.favmovies;
    }

    main.getFavactors = function() {

        // Get favactors via ajax
        $http.get('/api/favactors/').success(function(data) {
            $scope.favactors = data.data;
        }).error(function (data, status) {
            console.log('Error getting favactors: ' + data);
        });

        return $scope.favactors;
    }

    main.getRecommendedMovies = function() {

        // Get recommended movies via ajax
        $http.get('/api/movies/recommended/').success(function(data) {
            $scope.movies = data.data;
            main.allContentLoaded = true;
        }).error(function (data, status) {
            console.log('Error getting recommended movies: ' + data);
        });

        return $scope.movies;
    }

    $scope.$on('search_result', function(evt, data){

        $scope.search_items = data;

        main.loading = false;
        main.preloaded = false;
        main.search_result = true;
    });

    //main.init();
}]);