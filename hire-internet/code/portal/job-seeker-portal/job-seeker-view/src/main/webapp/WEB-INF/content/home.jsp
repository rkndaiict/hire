<!DOCTYPE html>
<html>
	<head>
		<title>AngularTest</title>	
		<%@ include file="../includes/_style.jsp" %>
		
		<%@ include file="../includes/_scripts.jsp" %>
	</head>
	<body ng-app="homeModule">
		<div ng-controller="homeModuleController">
			<div>hello world!</div>
			<div>
				Name : <input type="text" ng-model="testData"/> {{testData}}
			</div>
		</div>
	</body>
</html>