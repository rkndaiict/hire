var module = angular.module("homeModule",[]);

module.controller("homeModuleController", ['$scope', function($scope){

	$scope.testData = 'Hello World!';
}]);