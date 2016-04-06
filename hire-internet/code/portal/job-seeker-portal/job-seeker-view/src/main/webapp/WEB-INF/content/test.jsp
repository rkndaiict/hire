<html ng-module="demoTestModule">
	<head>
		<title>AngularTest</title>	
		<%@ include file="../includes/_style.jsp" %>
		
		<%@ include file="../includes/_scripts.jsp" %>
	</head>
	<body>
		<div>hello world!</div>
		<div ng-app="">
			Name : <input type="text" ng-model="testData"/> {{testData}}
		</div>
	</body>
</html>

</script>





<%-- <html ng-module="demoTestModule">
	<head>
		<title>AngularTest</title>	
		<%@ include file="../includes/_style.jsp" %>
		<%@ include file="../includes/_scripts.jsp" %>
	</head>
	<body>
		<div>hello world!</div>
		<form name="myForm" ng-controller="ExampleController">
			<input type="text" ng-model="testData"/> {{testData}}
		</form>
	</body>
</html>

<script>
angular.module('demoTestModule',[]).controller('ExampleController',['$scope', function($scope){
	$scope.testData = 'rahul';
}]);
 --%>
</script>