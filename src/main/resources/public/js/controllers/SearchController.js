/**
 * Search Controller
 */

mainApp.controller('SearchController', ['$scope', '$http', 'utilityService', function($scope, $http, utilityService) {

    // Set content type for requests
    $http.defaults.headers.post["Content-Type"] = "application/json";
    // Set api_key for logged user
    $http.defaults.headers.common["api_key"] = localStorage.getItem('token');

    var search = this;

    search.controls = {
        type: 'full'
    }

    search.search = function() {
        var criteria = search.controls.type === 'movie'? 'movie' : search.controls.type === 'actor'? 'actor' : 'full';
        var term = search.term;
        var url = 'api/search/' + criteria + '/' + term + '/';

        // Search for resources
        $http.get(url).success(function(data) {
            if(data.total_results > 0) {
                $scope.$parent.$parent.$broadcast('search_result', data);
            }
            else {
                console.log('set message: no results found')
                utilityService.setMessage('No se encontraron resultados para la busqueda');
            }
        }).error(function (data, status) {

        });
    }

    search.showResults = function(results) {

    }

}]);