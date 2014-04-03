angular.module('SpiralApp', [
    'SpiralApp.services',
    'SpiralApp.controllers'
]);

angular.module('SpiralApp.services', [
])
.factory('SpiralFactory', function($http) {
    return {
        get: function(scope) {            
            return $http({
                method: 'GET',
                url: 'http://localhost:31415/spiral/' + scope.order.toLowerCase(),
                headers: {
                    'Accept': scope.contentType
                },
                params: {
                    end: scope.end,
                    direction: scope.direction,
                    rotation: scope.rotation
                }
            });
        }
    }
});

angular.module('SpiralApp.controllers', [
])
.controller('SpiralController', function($scope, $location, SpiralFactory) {
    $scope.end = 24;
    $scope.direction = 'RIGHT';
    $scope.rotation = 'CLOCKWISE';
    $scope.style = 'PLAIN';
    $scope.order = 'FORWARD';
    $scope.spiral = {};
    $scope.errorMsg = '';

    $scope.refreshSpiral = function() {
        $scope.contentType = $scope.style == 'PRETTY' ? 'application/json' : 'text/plain'
        
        SpiralFactory.get($scope)
            .success(function(data) {
                $scope.spiral = data;
                $scope.errorMsg = '';
            })
            .error(function(data, status) {
                $scope.spiral = '';
                $scope.errorMsg = 'ERROR\n\nThere was an error fetching the results from the web service.\nPlease make sure the web service is running.';
            });
    };
 
    $scope.refreshSpiral();
});
