var testApp = angular.module("testFunctionApp", []);

testApp.controller("testFunctionAppController", ['$scope', function($scope){

$scope.testMe = function(){
	console.log(this == $scope);
};

}]);


