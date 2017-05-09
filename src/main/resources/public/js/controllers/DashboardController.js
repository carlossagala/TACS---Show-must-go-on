/**
 * Dashboard Controller
 */

mainApp.controller('DashboardController', ['$scope', '$http', '$timeout', '$location', 'utilityService', '$route', function($scope, $http, $timeout, $location, utilityService, $route) {

    // Set content type for requests
    $http.defaults.headers.post["Content-Type"] = "application/json";
    // Set api_key for logged user
    $http.defaults.headers.common["api_key"] = localStorage.getItem('token');

    var main = this;

    $scope.storagePath = 'https://image.tmdb.org/t/p/w500/';
    $scope.contentUrl = $route.current.$$route.contentUrl;
    $scope.movies = [];
    $scope.favmovies = [];
    $scope.favactors = [];
    $scope.users = [];
    $scope.loading = true;
    $scope.tab_content = false;
    $scope.search_result = false;

    main.init = function(resource) {
        main.getData(resource);
    }

    main.showContent = function() {
        $scope.loading = false;
    }

    main.getData = function(resource) {
        $scope.loading = true;
        switch(resource) {
            case 'recommended':
                main.getRecommendedMovies();
                break;
            case 'favmovies':
                main.getFavmovies();
                break;
            case 'favactors':
                main.getFavactors();
                break;
            case 'users':
                main.getUsers();
                break;
        }
    }

    main.getFavmovies = function() {

        // Get favmovies via ajax
        $http.get('/api/users/' + localStorage.getItem('user_id') +'/favmovies/').success(function(data) {
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
            $.each($scope.favactors, function(idx, el) {
                main.getActorDetails(el);
            })
            $scope.loading = false;
            $scope.tab_content = true;
        }).error(function (data, status) {
            console.log('Error getting favactors: ' + data);
            $scope.favactors = [];
        }).finally(function () {
            $scope.loading = false;
            $scope.tab_content = true;
            $scope.search_result = false;
        });
    }

    main.getActorDetails = function(actorId) {

        // Get favactors via ajax
        $http.get('/api/actor/' + actorId + '/').success(function(data) {
            var actor_details = data.data;
            angular.element(document).find(".details-wrapper-"+ actorId).html('<img src="'+ $scope.storagePath + actor_details.image[0].file_path + '" alt="" class="circle"><span>' + actor_details.name + '</span><a href="#/favactor/unmark/'+ actor_details.id +'" class="secondary-content"><i class="material-icons">grade</i></a>')
        }).error(function (data, status) {
            angular.element(document).find(".details-wrapper-"+ actorId).html("No se pudo encontrar informacion para este actor")
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
            if(status === 500)
                $location.path("/dashboard/recommended");
            if(data === "No se encuentra autenticado")
                $location.path("/logout");
        }).finally(function () {
            $scope.loading = false;
            $scope.tab_content = true;
            $scope.search_result = false;
        });
    }

    main.getUsers = function() {

        // Get users via ajax
        $http.get('/api/users/').success(function(data) {
            $scope.users = data.data;
            $scope.loading = false;
            $scope.tab_content = true;
        }).error(function (data, status) {
            console.log('Error getting users: ' + data);
            $scope.users = [];
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