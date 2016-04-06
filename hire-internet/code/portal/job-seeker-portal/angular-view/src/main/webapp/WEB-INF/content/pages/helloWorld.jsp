<%@ include file="/WEB-INF/includes/_tagLibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="decorator" content="angular_custom_layout">
</head>

<body>

    <div id="wrapper">
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <h1 class="page-header topic-header">
                        Hello World!
                    </h1>                                           
                    <div>
                        <div class="row">
                            <div class="col-lg-12">
                                <strong>Simple </strong> Example 1
                            </div>
                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="angular-js-code">
                                    <div align="middle"><span>JS Code</span></div>
                                    <br>
                                    <xmp>
                                        No Code Required
                                    </xmp>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="angular-js-jsp">
                                    <div align="middle"><span>JSP Code</span></div>
                                    <br/>
                                    <xmp>               
<html>
    <head>
        <title>AngularTest</title>  
    </head>
    <div>hello world!</div>
</html>
                                    </xmp>
                                </div>
                            </div>
                        </div>
                        <br><br>
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="angular-js-image">
                                    <div align="middle"><span>Sample Output</span></div>
                                    <br>
                                    <img src="images/helloWorldExample_1.jpg">
                                </div>
                            </div>
                        </div>
                    </div>
                    <br>
                    <!-- Example 1 Ends here-->
                    --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
                    <br><br><br><br>
                    <div>
                        <div class="row">
                            <div class="col-lg-12">
                                <strong>Simple </strong> Example 2
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="angular-js-code">
                                    <div align="middle"><span>JS Code</span></div>
                                    <br>
                                    <xmp>
var testApp = angular.module("testApp", []);
testApp.controller("testAppController", ['$scope', function($scope){
    $scope.testData = "Hellow World!";
}]);                                    </xmp>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="angular-js-jsp">
                                    <div align="middle"><span>JSP Code</span></div>
                                    <br>
                                    <xmp>
<html ng-app="testApp">
<head>    
<%@ include file="../../includes/_scripts_sample.jsp" %>
</head>
<body ng-controller="testAppController">
    <div id="wrapper">
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <span>
                        {{testData}}
                    </span>                                           
                </div> 
            </div>
        </div>
    </div>
</body>
</html>
                                    </xmp>
                                </div>
                            </div>
                        </div>
                        <br><br>
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="angular-js-image">
                                    <div align="middle"><span>Sample Output</span></div>
                                    <br>
                                    <img src="images/helloWorldExample_2.jpg">
                                </div>
                            </div>
                        </div>
                    </div>
                    <br>
                    <!-- Example 2 Ends here-->
                    --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            </div> 
            </div>
        </div>
    </div>
</body>

</html>
