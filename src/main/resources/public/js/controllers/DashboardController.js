/**
 * Dashboard Controller
 */

mainApp.controller('DashboardController', ['$scope', '$http', '$timeout', '$location', 'utilityService', '$route', '$compile', function($scope, $http, $timeout, $location, utilityService, $route, $compile) {

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
    $scope.pagination_result = [];
    $scope.ranking_favactors = [];
    $scope.users = [];
    $scope.loading = true;
    $scope.tab_content = false;
    $scope.search_result = false;

    $scope.username = localStorage.getItem('username');
    $scope.is_admin = localStorage.getItem('profile') === "admin";

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
                main.getFavactors(1);
                break;
            case 'users':
                main.getUsers();
                break;
            case 'ranking_favactors':
                main.getRankingFavactors();
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

    main.getFavactors = function(page) {

        console.log("main.getFavactors",page)

        // Get favactors via ajax
        $http.get('/api/favactors/?page=' + page).success(function(data) {
            $scope.favactors = data.data;
            $scope.pagination_result = {
                page: data.page,
                page_size: 5,
                total_results: data.total_results
            };

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

    main.getRankingFavactors = function() {

            // Get favactors via ajax
            $http.get('/api/users/ranking/actors/').success(function(data) {
                $scope.ranking_favactors = data.data;
                $scope.loading = false;
                $scope.tab_content = true;
            }).error(function (data, status) {
                console.log('Error getting ranking favactors: ' + data);
                $scope.ranking_favactors = [];
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
            var $el = angular.element(document).find(".details-wrapper-"+ actorId).html('<img src="'+ $scope.storagePath + actor_details.image[0].file_path + '" alt="" class="circle"><span>' + actor_details.name + '</span><a tooltipped data-position="top" data-delay="150" data-tooltip="Desmarcar como favorito!" ng-click="dashboard.unmarkAsFavourite('+ actor_details.id +')" class="btn red secondary-content"><i class="material-icons">grade</i></a>')
            $compile($el)($scope);
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

    main.getUser = function(userId) {

        // Get users via ajax
        $http.get('/api/users/' + userId + '/').success(function(data) {
            var user = data.data;
            angular.element(document).find("#user-details-loader").hide();
            angular.element(document).find("#user-details-header-wrapper")
                .html('<h4>Detalles del usuario: ' + user.user_name + '</h4>');
            angular.element(document).find("#user-details-body-wrapper")
                .html('<br><p>Actores favoritos: '+ user.cant_fav_actors +'</p><p>Peliculas favoritas: '+ user.cant_fav_movies +'</p>')
        }).error(function (data, status) {
            console.log('Error getting users: ' + data);

        });
    }

    main.getFavmovieDetails = function(favmovie) {

        angular.element(document).find("#user-details-loader").show();
        angular.element(document).find("#user-details-body-wrapper").html('');

        angular.element(document).find("#user-details-header-wrapper")
            .html('<h4>Detalles de la lista: ' + favmovie.name + '</h4>');
        $.each(favmovie.movies, function(idx, el) {
            main.getMovie(el.movie_id);
        })
    }

    main.getMovie = function(movieId) {

        // Get users via ajax
        $http.get('/api/movie/' + movieId + '/').success(function(data) {
            var movie = data.data;
            angular.element(document).find("#user-details-loader").hide();
            angular.element(document).find("#user-details-body-wrapper")
                .append('<br><div class="row"><h5>Pelicula: '+ movie.title +'</h5><p>Plot: '+ movie.overview +'</p></div>')
        }).error(function (data, status) {
            console.log('Error getting users: ' + data);

        });
    }

    main.markAsFavouritePost = function(actorId) {

        var favactor = {
            "id": actorId
        }

        // Get users via ajax
        $http.post('/api/favactors/', favactor).success(function(data) {
            var user = data.data;
            Materialize.toast("El actor fue marcado como favorito!", 1000)

        }).error(function (data, status) {
            console.log('Error trying to mark as fauvorite: ' + data);
            Materialize.toast("No se pudo marcar el actor fue marcado como favorito, intentelo nuevamente.", 1000)
        });
    }

    main.unmarkAsFavouritePost = function(actorId) {

        // Get users via ajax
        $http.delete('/api/favactors/' + actorId + '/').success(function(data) {
            //TODO: modify backend showing properly message
            if(data==null) {
                Materialize.toast("El actor fue desmarcado como favorito!", 1000)
                main.getFavactors(1);
            }

        }).error(function (data, status) {
            console.log('Error trying to mark as fauvorite: ' + data);
            Materialize.toast("No se pudo desmarcar el actor fue marcado como favorito, intentelo nuevamente.", 1000)
        });
    }

    main.getDetailsUser = function(userId) {
        angular.element(document).find("#user-details-loader").show();
        angular.element(document).find("#user-details-body-wrapper").html('');
        main.getUser(userId);
    }

    main.markAsFavourite = function(actorId) {
        main.markAsFavouritePost(actorId);
    }

    main.unmarkAsFavourite = function(actorId) {
        main.unmarkAsFavouritePost(actorId);
    }

    main.paginateFavactors = function(page) {
        console.log("goto page:", page)
        main.getFavactors(page);
    }

    $scope.$on('search_result', function(evt, data){

        $scope.search_items = data;

        $scope.loading = false;
        $scope.tab_content = false;
        $scope.search_result = true;
    });

    main.init($route.current.$$route.resource);
}]);