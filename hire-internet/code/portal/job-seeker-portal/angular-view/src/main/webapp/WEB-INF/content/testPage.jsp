<html ng-module="demoTestModule">
	<head>
		<title>AngularTest</title>	
		<%@ include file="../includes/_style.jsp" %>
		
		<%@ include file="../includes/_scripts.jsp" %>
	</head>
	<body ng-app="testApp">
		<div>hello world!</div>
		<div ng-app="testAppController">
			Name : <input type="text" ng-model="testData"/> {{testData}}
		</div>
	</body>
</html>
