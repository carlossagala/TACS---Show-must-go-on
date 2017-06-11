/**
 * Dashboard Controller
 */

mainApp.controller('DashboardController', ['$scope', '$http', '$timeout', '$location', 'utilityService', '$route', '$compile', '$routeParams', function($scope, $http, $timeout, $location, utilityService, $route, $compile, $routeParams) {

    // Set content type for requests
    $http.defaults.headers.post["Content-Type"] = "application/json";
    // Set api_key for logged user
    $http.defaults.headers.common["api_key"] = localStorage.getItem('token');

    var main = this;

    main.currentFavmovie = null;
    main.editedFavmovie = {};
    main.editedFavmovie.title = 'Nueva lista';

    $scope.storagePath = 'https://image.tmdb.org/t/p/w500/';
    $scope.contentUrl = $route.current.$$route.contentUrl;
    $scope.movies = [];
    $scope.favmovies = [];
    $scope.favactors = [];
    $scope.pagination_result = [];
    $scope.ranking_favactors = [];
    $scope.users = [];
    $scope.users_favmovies = [];
    $scope.loading = true;
    $scope.tab_content = false;
    $scope.search_result = false;
    $scope.intersection = false;
    $scope.movie_item_details = [];
    $scope.actor_item_details = [];

    $scope.resourceId = $routeParams.param;

    $scope.username = localStorage.getItem('username');
    $scope.is_admin = localStorage.getItem('profile') === "admin";

    $scope.valid_intersection = false;
    $scope.intersectionable = {}
    main.intersection_arr = [undefined, undefined];

    main.init = function(resource) {
        // Preload favmovies for dropdown
        main.getFavmovies();
        // Get data from resource navigated
        main.getData(resource);
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
            case 'ranking_favmovie_actors':
                main.getRankingFavmovieActors($scope.resourceId);
                break;
            case 'intersection_favmovies':
                main.getUsersFavmovies();
                break;
        }
    }

    main.getFavmovies = function() {

        $http.get('/api/users/' + localStorage.getItem('user_id') +'/favmovies/').success(function(data) {
            $scope.favmovies = data.data;
            $.each($scope.favmovies, function(idx, el) {
                $scope.intersectionable[el.id] = false;
            })
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
            $scope.favactors = _.uniq(data.data);
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
            var $el = angular.element(document).find(".details-wrapper-"+ actorId).html('<img src="'+ $scope.storagePath + actor_details.image[0].file_path + '" alt="" class="circle"><span>' + actor_details.name + '</span><a tooltipped data-position="top" data-delay="150" data-tooltip="Desmarcar como favorito!" ng-click="dashboard.unmarkAsFavouriteAndReload('+ actor_details.id +')" class="btn red secondary-content"><i class="material-icons">grade</i></a>')
            $compile($el)($scope);
        }).error(function (data, status) {
            angular.element(document).find(".details-wrapper-"+ actorId).html("No se pudo encontrar informacion para este actor");
        });
    }

    main.getActorDetailsModal = function(actorId) {

        var $modal_loader = angular.element(document).find("#actor-details-loader");
        $modal_loader.show();

        $http.get('/api/actor/' + actorId + '/').success(function(data) {
            $modal_loader.hide();
            $scope.actor_item_details = data.data;
            $scope.actor_item_details.contentType = 'actor';

        }).error(function (data, status) {
            Materialize.toast("No se pudo encontrar informacion para este actor", 2000, "red");
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

     main.getUsersFavmovies = function() {

            main.intersection_arr = [undefined, undefined];

            $http.get('/api/users/').success(function(data) {
                $scope.users_favmovies = _.reduce(
                    data.data,
                    function(result, el) {
                        var _favmovie = _.map(el.fav_movies, function(element) {
                             return _.extend({}, element, {user_name: el.user_name});
                        })
                        result = _.union(result, _favmovie);
                        return result
                    },
                    []
                )
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

        $modal_loader.show();

        $http.get('/api/users/' + userId + '/').success(function(data) {
            var user = data.data;
            $modal_loader.hide();
            $modal_header.html(`<h4>Detalles del usuario: ${user.user_name}</h4>`);
            $modal_body.html(`
                <h5>Cantidad de actores favoritos: <span class="light-blue-text">${user.cant_fav_actors}</span></h5>
                <h5>Cantidad de listas de peliculas favoritas: <span class="light-blue-text">${user.cant_fav_movies}</span></h5>
                <h5>Ultimo acceso: <span class="light-blue-text">${ (!_.isUndefined(user.last_access)? user.last_access : "-") }</span></h5>`
            );
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
            movie.can_remove = true;
            $scope.favmovies_details.push(movie);
            
        }).error(function (data, status) {
            console.log('Error getting users: ' + data);
        });
    }

    main.getRankingFavmovieActors = function(favmovieId) {

        $scope.ranking_favmovie = {
            items: [],
            favmovie_name: ""
        }

        $http.get('/api/favmovies/' + favmovieId + '/').success(function(data) {
            $scope.ranking_favmovie.favmovie_name = data.data.name;

            $http.get('/api/favmovies/' + favmovieId + '/ranking/').success(function(data) {
                $scope.ranking_favmovie.items = data.data;
                $scope.loading = false;
            }).error(function (data, status) {
                $scope.ranking_favmovie.items = [];
                Materialize.toast("No se pudo listar los actores repetidos de esta lista, intentelo nuevamente.", 2000, "red");
            }).finally(function () {
                $scope.loading = false;
                $scope.tab_content = true;
                $scope.search_result = false;
            });
        });

    }

    main.markAsFavouriteService = function(actorId) {

        var favactor = {
            "id": actorId
        }

        $http.post('/api/favactors/', favactor).success(function(data) {
            var user = data.data;
            Materialize.toast("El actor fue marcado como favorito!", 2000, "orange");

        }).error(function (data, status) {
            Materialize.toast("No se pudo marcar el actor fue marcado como favorito, intentelo nuevamente.", 2000, "red");
        });
    }

    main.unmarkAsFavouriteService = function(actorId, callback) {

        $http.delete('/api/favactors/' + actorId + '/').success(function(data) {
            //TODO: modify backend showing properly message
            if(data) {
                Materialize.toast("El actor fue desmarcado como favorito!", 2000, "orange")
                callback();
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
        main.markAsFavouriteService(actorId);
    }

    main.unmarkAsFavouriteAndRefresh = function(actorId) {
        main.unmarkAsFavouriteService(actorId, function() { console.log('reload search?') });
    }

    main.unmarkAsFavouriteAndReload = function(actorId) {
        main.unmarkAsFavouriteService(actorId, function() { main.getFavactors(1); });
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
            Materialize.toast(data.message, 2000, "orange")
            main.getFavmovies();

        }).error(function (data, status) {
            Materialize.toast("No se pudo eliminar la lista de películas favoritas, intentelo nuevamente.", 2000, "red")
        });
    }

    main.addFavmovie = function() {

        var favmovie = {
            "name": main.editedFavmovie.name
        }

        $http.post('/api/favmovies/', favmovie).success(function(data) {
            $scope.favmovies.push(data.data);
            Materialize.toast("Se creo la lista!", 2000, "orange");
            main.resetFavmovieForm();
        }).error(function (data, status) {
            Materialize.toast("No se pudo crear la lista, intentelo nuevamente.", 2000, "red")
        });
    }

    main.updateFavmovieService = function() {

        var favmovie = {
            "new_title": main.editedFavmovie.name
        }

        $http.put('/api/favmovies/' + main.editedFavmovie.id + '/', favmovie).success(function(data) {
            Materialize.toast("Se actualizo la lista!", 2000, "orange");
            main.resetFavmovieForm();
        }).error(function (data, status) {
            Materialize.toast("No se pudo actualizar la lista, intentelo nuevamente.", 2000, "red")
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

    main.editFavmovie = function(favmovie){
        main.currentFavmovie = favmovie;
        main.editedFavmovie = angular.copy(main.currentFavmovie);
        main.editedFavmovie.title = 'Editar lista';
    }

    main.updateFavmovie = function() {
        main.currentFavmovie.name = main.editedFavmovie.name;
        main.updateFavmovieService();
    }

    main.resetFavmovieForm = function() {
        main.currentFavmovie = null;
        main.editedFavmovie = {};

        main.favmovieForm.$setPristine();
        main.favmovieForm.$setUntouched();

        main.editedFavmovie.title = 'Nueva lista';
    }

    main.updateFavmovieCancel = function() {
        main.resetFavmovieForm();
    }

    main.hideSearchResults = function() {
        $scope.tab_content = true;
        $scope.search_result = false;
    }
 
    main.isFavactor = function(actorId) {
        return $scope.favactors_ids.indexOf(actorId) > -1;
    }    

    $scope.$on('searching', function(evt, data){
        $scope.loading = true;
        $scope.tab_content = false;
        $scope.search_result = false;
    });

    $scope.$on('search_result', function(evt, data){
        $http.get('/api/favactors/?page=0').success(function(data_ids) {
            $scope.favactors_ids = _.uniq(data_ids.data);
        }).error(function (data_ids, status) {
            $scope.favactors_ids = [];
        }).finally(function () {
            $scope.loading = false;
            $scope.tab_content = false;
            $scope.search_result = true; 
            $scope.search_items = data;
        });
    });

    main.selectFavmovie = function(favmovieId) {

        var key = main.intersection_arr.shift();

        if(key) {
            $scope.intersectionable[key] = false;
        }

        if(main.intersection_arr.indexOf(favmovieId) > -1) {
            main.intersection_arr.push(undefined);
            $scope.intersectionable[favmovieId] = false;
        }
        else {
            main.intersection_arr.push(favmovieId);
            $scope.intersectionable[favmovieId] = true;
        }

        $scope.valid_intersection = (main.intersection_arr[0]!==undefined && main.intersection_arr[1]!==undefined);

    }

    main.intersectionFavmovies = function() {

        var values = main.intersection_arr;
        $http.get('/api/favmovies/' + values[0] + '/intersection/' + values[1] + '/').success(function(data) {
            $scope.favmovies_intersection = data.data;
            $scope.intersection = true;

            $.each($scope.favmovies_intersection, function(idx, el) {
                main.getMovieDetails(el.movie_id)
            })
        }).error(function (data, status) {
            Materialize.toast("No se pudo obtener la interseccion entre las listas, intentelo nuevamente.", 2000, "red")
            $scope.intersection = false;
        }).finally(function () {
            $scope.loading = false;
            $scope.tab_content = true;
            $scope.search_result = false;
        });
    }

    main.getMovieDetails = function(movieId) {

        $http.get('/api/movie/' + movieId + '/').success(function(data) {
            var movie_details = data.data;
            var $el = angular.element(document).find(".details-wrapper-movie-"+ movieId)
                .html('<span class="col m1"><i class="material-icons">theaters</i></span><span class="col m11"><p class="title-movie-wrapper">' + movie_details.title + '</p></span>')
            $compile($el)($scope);
        }).error(function (data, status) {
            angular.element(document).find(".details-wrapper-movie-"+ movieId).html("No se pudo encontrar informacion para esta pelicula")
        });
    }

    main.getMovieDetailsModal = function(movieId) {

        var $modal_loader = angular.element(document).find("#movie-details-loader");
        $modal_loader.show();

        $http.get('/api/movie/' + movieId + '/').success(function(data) {
            $modal_loader.hide();
            $scope.movie_item_details = data.data;
            $scope.movie_item_details.contentType = 'movie';
            $scope.can_remove = false;

        }).error(function (data, status) {
            Materialize.toast("No se pudo encontrar informacion para este actor", 2000, "red");
        });
    }

    main.hidefavmovieIntersection = function() {
        $scope.intersection = false;
    }

    main.init($route.current.$$route.resource);
}]);