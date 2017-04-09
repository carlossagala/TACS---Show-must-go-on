/**
 * Main Controller
 */

mainApp.controller('MainController', ['$scope', '$http', function($scope, $http) {

    var main = this;

    $scope.users = [];
    $scope.movies = [];

    main.init = function() {
        main.getUsers();
        main.getMovies();
    }

    main.getUsers = function() {

        // TODO change to base_url . 'api/users/' when service is ready
        $http.get('users.json').success(function(data) {
            $scope.users = data;
        }).error(function (data, status) {
            console.log('Error ' + data);
        });

        return $scope.users;
    }

    main.getMovies = function() {

        // TODO change to base_url . 'api/movies/' when service is read
        $http.get('movies.json').success(function(data) {
            $scope.movies = data;
        }).error(function (data, status) {
            console.log('Error ' + data);
        });

        return $scope.movies;
    }

    main.init();
}]);