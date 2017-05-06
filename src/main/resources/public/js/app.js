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
        .when("/dashboard/a", {
            templateUrl: "views/user/dashboard.html",
            contentUrl: "views/user/list.html",
            controller: "DashboardController as dashboard"
        })
        .when("/dashboard/recommended", {
            templateUrl: "views/user/dashboard.html",
            contentUrl: "views/user/recommended.html",
            controller: "DashboardController as dashboard",
            resource: "recommended"
        })
        .when("/dashboard/favmovies", {
            templateUrl: "views/user/dashboard.html",
            contentUrl: "views/user/favmovies.html",
            controller: "DashboardController as dashboard",
            resource: "favmovies"
        })
        .when("/dashboard/favactors", {
            templateUrl: "views/user/dashboard.html",
            contentUrl: "views/user/favactors.html",
            controller: "DashboardController as dashboard",
            resource: "favactors"
        })
        .when("/dashboard/users", {
            templateUrl: "views/admin/users.html",
            contentUrl: "views/admin/users.html",
            controller: "DashboardController as dashboard"
        })
        .when("/dashboard/a", {
            templateUrl: "views/admin/dashboard.html",
            contentUrl: "views/user/list.html",
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