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
        var criteria = search.controls.type;
        var term = search.term;
        var url = 'api/search/' + criteria + '/' + term + '/';

        $scope.$parent.$parent.$broadcast('searching', []);

        // Search for resources
        $http.get(url).success(function(data) {
            if(data.total_results > 0) {
                $scope.$parent.$parent.$broadcast('search_result', data);
            }
            else {
                $scope.$parent.$parent.$broadcast('search_result', []);
                utilityService.setMessage('No se encontraron resultados para la busqueda');
            }
        }).error(function (data, status) {

        });
    }

}]);