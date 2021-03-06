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
        .when("/register", {
            templateUrl: "views/common/register.html",
            controller: "LoginController as login"
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
        .when("/dashboard/favmovies/:param", {
            templateUrl: "views/user/dashboard.html",
            contentUrl: "views/user/favmovie_details.html",
            controller: "DashboardController as dashboard",
            resource: "favmovie_details"
        })
        .when("/dashboard/favactors", {
            templateUrl: "views/user/dashboard.html",
            contentUrl: "views/user/favactors.html",
            controller: "DashboardController as dashboard",
            resource: "favactors"
        })
        .when("/dashboard/users", {
            templateUrl: "views/user/dashboard.html",
            contentUrl: "views/admin/users.html",
            controller: "DashboardController as dashboard",
            resource: "users"
        })
        .when("/dashboard/ranking-favactors", {
            templateUrl: "views/user/dashboard.html",
            contentUrl: "views/admin/favactors.html",
            controller: "DashboardController as dashboard",
            resource: "ranking_favactors"
        })
        .when("/dashboard/favmovies/:param/ranking", {
            templateUrl: "views/user/dashboard.html",
            contentUrl: "views/user/favmovie_ranking.html",
            controller: "DashboardController as dashboard",
            resource: "ranking_favmovie_actors"
        })
        .when("/dashboard/intersection-favmovies", {
            templateUrl: "views/user/dashboard.html",
            contentUrl: "views/admin/intersection_favmovies.html",
            controller: "DashboardController as dashboard",
            resource: "intersection_favmovies"
        })
        .when("/dashboard/search/:param", {
            templateUrl: "views/user/dashboard.html",
            contentUrl: "views/user/recommended.html",
            controller: "DashboardController as dashboard",
            resource: "recommended"
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

// Directives
mainApp.directive('contentItem', function($compile) {

    return {
        template: '<ng-include src="getTemplateUrl()"/>',
        scope: {
          content: '=',
          'remove': '&onRemove'
        },
        restrict: 'E',
        controller: function($scope) {
            //function used on the ng-include to resolve the template
            $scope.getTemplateUrl = function() {
                //content-type param handling
                if ($scope.content.contentType === "movie") {
                    return "views/common/movie.tpl.html";
                }
                if ($scope.content.contentType === "actor") {
                    return "views/common/actor.tpl.html";
                }
            }
        }
    };
});

mainApp.directive('ngEnter', function() {
    return function(scope, element, attrs) {
        element.bind("keydown keypress", function(event) {
            if(event.which === 13) {
                scope.$apply(function(){
                    scope.$eval(attrs.ngEnter, {'event': event});
                });

                event.preventDefault();
            }
        });
    };
});

// Filters
mainApp.filter('unique', function() {
    return function (arr, field) {
        return _.uniq(arr, function(a) { return a[field]; });
    };
});

mainApp.run(['$route', '$rootScope', '$location', function ($route, $rootScope, $location) {
    var original = $location.path;
    $location.path = function (path, reload) {
        if (reload === false) {
            var lastRoute = $route.current;
            var un = $rootScope.$on('$locationChangeSuccess', function () {
                $route.current = lastRoute;
                un();
            });
        }
        return original.apply($location, [path]);
    };
}])