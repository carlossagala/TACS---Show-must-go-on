/**
 * Dashboard Controller
 */

mainApp.controller('DashboardController', ['$scope', '$http', '$timeout', '$location', 'utilityService', '$route', '$compile', '$routeParams', function($scope, $http, $timeout, $location, utilityService, $route, $compile, $routeParams) {

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

    $scope.resourceId = $routeParams.param;

    $scope.username = localStorage.getItem('username');
    $scope.is_admin = localStorage.getItem('profile') === "admin";

    main.init = function(resource) {
        // Preload favmovies for dropdown
        main.getFavmovies();
        // Get data from resource navigated
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
            case 'favmovie_details':
                main.getFavmovieDetails($scope.resourceId);
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

        $http.get('/api/users/' + localStorage.getItem('user_id') +'/favmovies/').success(function(data) {
            $scope.favmovies = data.data;
        }).error(function (data, status) {
            $scope.favmovies = [];
        }).finally(function () {
            if($route.current.$$route.resource==="favmovies")
                $scope.loading = false;
            $scope.tab_content = true;
            $scope.search_result = false;
        });
    }

    main.getFavactors = function(page) {

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
            $scope.favactors = [];
        }).finally(function () {
            $scope.loading = false;
            $scope.tab_content = true;
            $scope.search_result = false;
        });
    }

    main.getRankingFavactors = function() {

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

        $http.get('/api/actor/' + actorId + '/').success(function(data) {
            var actor_details = data.data;
            var $el = angular.element(document).find(".details-wrapper-"+ actorId).html('<img src="'+ $scope.storagePath + actor_details.image[0].file_path + '" alt="" class="circle"><span>' + actor_details.name + '</span><a tooltipped data-position="top" data-delay="150" data-tooltip="Desmarcar como favorito!" ng-click="dashboard.unmarkAsFavourite('+ actor_details.id +')" class="btn red secondary-content"><i class="material-icons">grade</i></a>')
            $compile($el)($scope);
        }).error(function (data, status) {
            angular.element(document).find(".details-wrapper-"+ actorId).html("No se pudo encontrar informacion para este actor")
        });
    }

    main.getRecommendedMovies = function() {

        $scope.loading = true;

        $http.get('/api/movies/recommended/').success(function(data) {
            if(data === "No se encuentra autenticado")
                $location.path("/logout");
            $scope.movies = data.data;
        }).error(function (data, status) {
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

        var $modal_header = angular.element(document).find("#user-details-header-wrapper");
        var $modal_body   = angular.element(document).find("#user-details-body-wrapper");
        var $modal_loader = angular.element(document).find("#user-details-loader");

        $http.get('/api/users/' + userId + '/').success(function(data) {
            var user = data.data;
            $modal_loader.hide();
            $modal_header.html('<h4>Detalles del usuario: ' + user.user_name + '</h4>');
            $modal_body.html('<br><p>Actores favoritos: '+ user.cant_fav_actors +'</p><p>Peliculas favoritas: '+ user.cant_fav_movies +'</p>')
        }).error(function (data, status) {
            console.log('Error getting users: ' + data);
        });
    }

    main.getFavmovieDetails = function(favmovieId) {

        $http.get('/api/favmovies/' + favmovieId + '/').success(function(data) {
            $scope.favmovie = data.data;
            $scope.favmovies_details = [];

            $.each($scope.favmovie.movies, function(idx, el) {
                main.getMovie(el.movie_id);
            })

            var $el = angular.element(document).find('#favmovie_details')
            $compile($el)($scope);

            $scope.loading = false;
        }).error(function (data, status) {
            console.log('Error getting favmovies: ' + data);
            $scope.favmovies_details = [];
        }).finally(function () {
            $scope.loading = false;
            $scope.tab_content = true;
            $scope.search_result = false;
        });
    }

    main.getMovie = function(movieId) {

        $scope.loading = true;

        $http.get('/api/movie/' + movieId + '/').success(function(data) {
            var movie = data.data;
            movie.contentType = 'movie';
            $scope.favmovies_details.push(movie);
            
        }).error(function (data, status) {
            console.log('Error getting users: ' + data);
        });
    }

    main.markAsFavouritePost = function(actorId) {

        var favactor = {
            "id": actorId
        }

        $http.post('/api/favactors/', favactor).success(function(data) {
            var user = data.data;
            Materialize.toast("El actor fue marcado como favorito!", 2000, "orange")

        }).error(function (data, status) {
            Materialize.toast("No se pudo marcar el actor fue marcado como favorito, intentelo nuevamente.", 2000, "red")
        });
    }

    main.unmarkAsFavouritePost = function(actorId) {

        $http.delete('/api/favactors/' + actorId + '/').success(function(data) {
            //TODO: modify backend showing properly message
            if(data==null) {
                Materialize.toast("El actor fue desmarcado como favorito!", 2000, "orange")
                main.getFavactors(1);
            }

        }).error(function (data, status) {
            Materialize.toast("No se pudo desmarcar el actor fue marcado como favorito, intentelo nuevamente.", 2000, "red")
        });
    }

    main.getDetailsUser = function(userId) {

        var $modal_header = angular.element(document).find("#user-details-header-wrapper");
        var $modal_body   = angular.element(document).find("#user-details-body-wrapper");
        var $modal_loader = angular.element(document).find("#user-details-loader");

        $modal_loader.show();
        $modal_body.html('');
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

    main.addMovieToFavmovie = function(movieId, favmovieId) {
        
        var movie = {
            "id": movieId
        }

        $http.post('/api/favmovies/' + favmovieId + '/movies/', movie).success(function(data) {
            var user = data.data;
            Materialize.toast("Se agrego la pelicula a la lista!", 2000, "orange")
        }).error(function (data, status) {
            Materialize.toast("No se pudo agregar la pelicula a la lista, intentelo nuevamente.", 2000, "red")
        });
    }

    main.removeFavmovie = function(favmovieId) {

        $http.delete('/api/favmovies/' + favmovieId + '/').success(function(data) {
            //TODO: modify backend showing properly message
            if(data.message==="ok") {
                Materialize.toast("La lista se elimino correctamente", 2000, "orange")
                main.getFavmovies();
            }

        }).error(function (data, status) {
            Materialize.toast("No se pudo desmarcar el actor fue marcado como favorito, intentelo nuevamente.", 2000, "red")
        });
    }

    main.addFavmovie = function() {

        var favmovie = {
            "name": main.favmovie_form.name
        }

        $http.post('/api/favmovies/', favmovie).success(function(data) {
            $scope.favmovies.push(data.data);
            main.favmovie_form.name = "";
            Materialize.toast("Se creo la lista!", 2000, "orange")
        }).error(function (data, status) {
            Materialize.toast("No se pudo crear la lista, intentelo nuevamente.", 2000, "red")
        });
    }

    main.removeMovieFromFavmovie = function(movieId) {
        $http.delete('/api/favmovies/' + $scope.resourceId + '/movies/' + movieId + '/').success(function(data) {
            //TODO: modify backend showing properly message
            if(data.data==="Se eliminó la pelicula de la lista") {
                Materialize.toast("La pelicula se quito de la lista!", 2000, "orange")
                $route.reload();
            }
        }).error(function (data, status) {
            Materialize.toast("No se pudo quitar la pelicula de la lista, intentelo nuevamente.", 2000, "red")
        });
    }

    main.hideSearchResults = function() {
        $scope.tab_content = true;
        $scope.search_result = false;
    }

    $scope.$on('searching', function(evt, data){
        $scope.loading = true;
        $scope.tab_content = false;
        $scope.search_result = false;
    });

    $scope.$on('search_result', function(evt, data){
        $scope.search_items = data;
        $scope.loading = false;
        $scope.tab_content = false;
        $scope.search_result = true;
    });

    main.init($route.current.$$route.resource);
}]);