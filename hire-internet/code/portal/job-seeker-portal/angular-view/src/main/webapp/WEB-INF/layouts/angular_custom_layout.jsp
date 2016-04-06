<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
<%@ include file="/WEB-INF/includes/_tagLibs.jsp" %>
<!doctype html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="author" content="">

		<title>Alternative ways of doing things in Angular</title>

		<%@ include file="../includes/_style.jsp" %>
		
		<%@ include file="../includes/_scripts.jsp" %>
	
	</head>
	<body >
		<div>
	        <nav class="nav navbar-inverse navbar-fixed-top" role="navigation">
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                	<%@ include file="../includes/_leftNavigation.jsp"%>
                </ul>
            </div>
        </nav>
		<article>
	        <decorator:body />
        </article>		
    </div>
	</body>
</html>