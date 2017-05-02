/**
 * Login Controller
 */

mainApp.controller('LoginController', ['$scope', '$http', '$timeout', '$location', function($scope, $http, $timeout, $location) {

    $http.defaults.headers.post["Content-Type"] = "application/json";

    var login = this;
    var loggedIn = false;

    login.user = {};
    login.invalidInput = false;

    login.auth = function() {

        var user_credentials = {
            "user_name": login.user.username,
            "password": login.user.password
        }

        // Login an user
        $http.post('api/user/login/', user_credentials).success(function(data) {
            if(data.token) {
                login.api_key = data.token;
                login.loggedIn = true;

                login.invalidInput = false;
                login.serverError = false;

                // Set api_key for logged user
                $http.defaults.headers.common["api_key"] = login.api_key;

                //TODO: redirect to dashboard according to profile
                $location.path("/dashboard/a");
                //$location.path("/dashboard/u");
            }
            else {
                login.api_key = null;
                login.loggedIn = false;
                login.invalidInput = true;
            }
        }).error(function (data, status) {
            if(data === null) {
                login.invalidInput = true;
            }
            else {
                login.serverError = true;
            }
            login.loggedIn = false;
        });
    }

}]);