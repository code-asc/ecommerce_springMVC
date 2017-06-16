<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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

                <%@ include file="/WEB-INF/jsp/header.jsp" %>
                        <div class="container">
                            <form:form commandName="editProfile1" enctype="multipart/form-data" id="userEditForm" name="userEditForm" method="POST" >
                                <div class="row" style="border-bottom:1px solid #eaeaec ; margin-bottom:20px ; padding-bottom:15px">
                                    <h2>Edit Profile</h2>
                                </div>
                                <c:forEach items="${userInfo}" var="userInfo">
                                <c:set var="userProfilePhoto" value="${userInfo.userProfilePhoto}"/>
                                 <div class="row">
                                    <div class="col-sm-3 col-md-3 col-xm-12" style="margin-bottom:20px;">
                                    
                                         <c:choose>
                                              <c:when test="${fn:length(userProfilePhoto) > 0}">
                                            <div class="row">
                                                <div style="height: 140px;width: 140px;overflow: hidden; overflow:hidden">
                                                   
                                                    
                                                     <img class="img-rounded img-responsive" src= "${userProfilePhoto }"alt=" "/>
                                                </div>
                                            </div>
                                            </c:when>
                                </c:choose>
                                        <div class="row">
                                            <p>Upload a different photo
                                        </div>
                                        <div class="row" style="margin-top:8px;">
                                            <div class="col-md-10 col-sm-10 col-xs-10" style="padding-left:0px;">
                                                <div class="form-group">
                                                    <form:input type="file" name="profilePhoto" class="form-control" path="userProfilePhoto"/>
                                                </div>
                                            </div>
                                        </div>

                                    </div>

                                    <div class="col-sm-6 col-md-6 col-xm-6">
                                        <div class="row">
                                            <h3>Personal Info</h3>
                                        </div>
                                        <div class="col-sm-8 col-md-8 col-xs-8">
                                            <label for="firstName">First Name</label>
                                            <div class="form-group">
                                                <form:input name="firstName" id="firstName" placeholder="First Name" class="form-control" value="${userInfo.userFirstName}" path="userFirstName" />
                                            </div>
                                        </div>

                                        <div class="col-sm-8 col-md-8 col-xs-8">
                                            <label for="middleName">Middle Name</label>
                                            <div class="form-group">
                                                <form:input  name="middleName" id="middleName" placeholder="Middle Name" class="form-control" value="${userInfo.userMiddleName}" path="userMiddleName"/>
                                            </div>
                                        </div>

                                        <div class="col-sm-8 col-md-8 col-xs-8">
                                            <label for="lastName">Last Name</label>
                                            <div class="form-group">
                                                <form:input  name="lastName" id="lastName" placeholder="Last Name" class="form-control" value="${userInfo.userLastName}" path="userLastName" />
                                            </div>
                                        </div>

                                        <div class="col-sm-8 col-md-8 col-xs-8">
                                            <label for="email">Email Address</label>
                                            <div class="form-group">
                                                <form:input  id="email" name="email" placeholder="Email" class="form-control" value="${userInfo.userEmail}" path="userEmail"/>
                                            
                                            </div>
                                        </div>

                                        <div class="col-sm-8 col-md-8 col-xs-8">
                                            <label for="mobile">Mobile</label>
                                            <div class="form-group">
                                                <form:input  id="mobile" name="mobile" placeholder="mobile" class="form-control" value="${userInfo.userPhone}" path="userPhone"/>
                                            </div>
                                        </div>

                                        <div class="col-sm-12 col-md-12 col-xs-12">
                                            <div class="form-group">
                                                <input type="submit" class="btn btn-primary" name="submitUserEdit" id="submitUserEdit" value="Save Changes">
                                            </div>
                                        </div>

                                    </div>

                                </div>
                                
                                </c:forEach>
                               
                            </form:form>

                            <div id="showMessage">
                            </div>

                        </div>

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
        
        
   <!-- <script src="/ProjectDemo/assets/script/autoSuggestion.js"></script> -->
        
        <script src="<c:url value="/resources/script/autoSuggestion.js"/>"></script>
        
        <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/jquery.validate.min.js"></script>
        <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/additional-methods.min.js"></script>
        
        
<!--    <script src="/ProjectDemo/assets/script/validateClient.js"></script>
        <script src="/ProjectDemo/assets/script/userEditAjax.js"></script> -->
        
        <script src="<c:url value="/resources/script/validateClient.js"/>"></script>
        <script src="<c:url value="/resources/script/userEditAjax.js"/>"></script>
        
        
    </body>

    </html>
