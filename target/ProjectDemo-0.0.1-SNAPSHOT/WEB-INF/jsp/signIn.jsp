<!---
FileName      :signIn.jsp
Functionality : It is used for sign In purpose
--->


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
<% response.setHeader("Pragma","No-cache");     
  response.setHeader("Cache-Control","no-cache");     
  response.setDateHeader("Expires",   0);%> 
  
  
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Pragma" content="no-cache"> 
<meta http-equiv="Cache-Control"      content="no-cache"> 
<meta http-equiv="Expires" content="Sat, 01 Dec 2012 00:00:00 GMT">

<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.2/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.16.0/jquery.validate.min.js"></script>
</head>

<body>

<%@ include file="/WEB-INF/jsp/header.jsp" %>
	<div class="container-fluid">

		<div class="row">
		
		<div class="row">
		<c:choose>
		<c:when test="${showMessage}">
		<div class="alert alert-warning">
		<div class="text-center">
		${signInStatus}
		</div>
		</div>
		</c:when>
		</c:choose>
		</div>
			<div class="col-md-3" id="form-border" style="margin: auto; left: 0; right: 0; position: absolute">
				<h2>SignIn</h2>

			 <form:form id="form_signin" commandName="loginForm" method="POST">
					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-user"
								aria-hidden="true"></i></span>
						<form:input path="email" placeholder="Email" class="form-control"/>
						</div>
					</div>

					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="fa fa-unlock-alt" aria-hidden="true"></i></span>
							<form:password path="password" placeholder="Password" class="form-control"/>
						</div>
					</div>

					<div class="form-group">
						<input class="btn btn-success" type="submit" name="submit" value="SignIn">
					</div>
				</form:form> 

				
							</div>
		</div>
	</div>



	
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <!-- <script src="/ProjectDemo/assets/script/autoSuggestion.js"></script> -->
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/jquery.validate.min.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/additional-methods.min.js"></script>
    <script src="<c:url value="/resources/script/validateClientSignIn.js"/>"></script>
    
    
   <!--  <script src="/ProjectDemo/assets/script/validateClientSignIn.js"></script> -->
    
    
</body>
</html>
