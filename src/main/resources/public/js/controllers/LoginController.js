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
                login.loggedIn = true;

                login.invalidInput = false;
                login.serverError = false;

                localStorage['token']   = data.token;
                localStorage['user_id'] = data.user_id;
                localStorage['profile'] = data.nivel;
                localStorage['username'] = login.user.username;

                data.nivel==="admin"
                    ? $location.path("/dashboard/users")
                    : $location.path("/dashboard/recommended");
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
    
    login.register = function() {
    	
    	var user_credentials = {
            "user_name": login.user.username,
            "password": login.user.password
        }
    	
    	$http.post('api/user/', user_credentials).success(function(data) {
            login.loggedIn = true;
            login.successfullRegister = true;
            login.unsuccessfullRegister = false;
            login.invalidInput = false;
            login.serverError = false;
            //automatic login
            setTimeout(login.auth, 2000);
        }).error(function (data, status) {
            login.invalidInput = false;
        	login.successfullRegister = false;
        	login.unsuccessfullRegister = true;
            login.loggedIn = false;
        });
    	
    }

}]);