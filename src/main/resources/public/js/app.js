'use strict';

// Main dependencies
var mainApp = angular.module('mainApp',['ngRoute','ui.materialize']);

// Config Routes
mainApp.config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider) {

    $routeProvider
        .when("/", {
            templateUrl: "views/common/login.html",
            controller: "LoginController as login"
        })
        .when("/dashboard/u", {
            templateUrl: "views/user/dashboard.html"
        })
        .when("/dashboard/a", {
            templateUrl: "views/admin/dashboard.html",
            controller: "DashboardController as dashboard"
        })
        .when("/logout", {
            templateUrl: "views/common/login.html",
            controller: "LoginController as login"
        })
    ;
}]);

// Services
mainApp.factory('utilityService', function() {
    return {
        data: null,
        getData: function() {
            return this.data;
        },
        setData: function(d) {
            this.data = d;
        },
        message: '',
        getMessage: function() {
            return this.message;
        },
        setMessage: function(msg) {
            this.message = msg;
        }
    };
})