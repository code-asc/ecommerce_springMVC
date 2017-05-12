<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!---
FileName      :signUp.jsp
Functionality : It will show sign up page
--->
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">


    <!-- Bootstrap -->

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.2/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

                   <%@ include file="/WEB-INF/jsp/header.jsp"%>

                <div class="container">
                    <div class="row" id="row_show_error">
                 
                    </div>
                 
                    <div class="row text-center">
                    <div class="col-md-4" id="form-border" style="margin:auto ; left:0 ; right:0  ; position : absolute">
                     <c:choose>
                    <c:when test="${requestScope.status}">
                    <div class="row">
                    <div class="col-sm-12 col-md-12 col-lg-12">
                    <div class="alert alert-warning">
                    <c:out value="${requestScope.signUpStatus}"/>
                    </div>
                    </div>
                    </div>
                    </c:when>
                    </c:choose>
                        
                            
                            
                            
                            <c:choose>
                            <c:when test="${requestScope.hideAndOnlyShowMessage}">
                            <div class="alert alert-warning">
                    <c:out value="${requestScope.signUpStatus}"/>
                    </div>
                            
                            </c:when>
                            <c:otherwise>
                            <h2>SignUp Here</h2>
                            <form:form commandName="signUpDetails" method="POST">
                            
                            <div class="form-group input-field">
                                  <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user" aria-hidden="true"></i></span>
                                <form:input path="firstName" id="firstName"  class="form-control" placeholder="Your FirstName"/>
                                </div>
                              </div>
                              
                                <div class="form-group input-field">
                                  <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user" aria-hidden="true"></i></span>
                                <form:input path="middleName"  class="form-control"  name="middleName" placeholder="Your MiddleName (Optional)"/>
                                </div>
                              </div>
                              
                              <div class="form-group input-field">
                                  <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user" aria-hidden="true"></i></span>
                               <form:input path="lastName" class="form-control"  name="lastName" placeholder="Your LastName (Optional)"/>
                                </div>
                              </div>
                                      
                              <div class="form-group input-field">
                                  <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-globe" aria-hidden="true"></i></span>
                                    <form:input path="email" class="form-control"  name="email" placeholder="Email id"/>
                                </div>
                              </div>
                            
                               <div class="form-group input-field">
                                  <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-lock" aria-hidden="true"></i></span>
                                <form:password path="password" class="form-control" name="password" placeholder="Password"/>
                                </div>
                              </div>  
                              
                              <div class="form-group input-field">
                                  <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-mobile" aria-hidden="true"></i></span>
                                <form:input path="mobile" class="form-control"  name="mobile" placeholder="Mobile" />
                                </div>
                              </div> 
                              
                               <div class="form-group">
                                    <input class="btn btn-success" type="submit" name="submit" value="Register">
                                </div>                   
                            </form:form>
                            </c:otherwise>
                            </c:choose>
                                <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->


</body>
</html>
    