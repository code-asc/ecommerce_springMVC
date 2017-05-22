<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

                
                                        <div class="row">
                                            <div class="col-md-8 col-sm-8 col-lg-8 col-xm-8">
                                                <div class="alert alert-info">
                                                    Create a new Delivery Address below
                                                </div>

                                                <div class="col-md-4 col-sm-4 col-lg-6 col-xm-4">

                                                    <h2>Delivery Address</h2>

                                                    
																		<form:form id="cf_form_address" commandName="addressField">
																		<div class="form-group">
																		<form:input path="country" name="country" id="country" placeholder="country" class="form-control"/>
																		</div>
																		
																		<div class="form-group">
																		<form:input path="state" id="state" placeholder="state" class="form-control"/>
																		</div>
																		
																		<div class="form-group">
																		<form:input path="city" id="city" placeholder="city" class="form-control"/>
																		</div>
																		
																		<div class="form-group">
																		<form:textarea path="address" id="address" placeholder="Address" class="form-control"/>
																		</div>
																		
																		<div class="form-group">
																		<form:textarea path="address2" id="address2" placeholder="Address2" class="form-control"/>
																		</div>
																		
																		<div class="form-group">
																		<form:input path="pincode" id="pincode" placeholder="pincode" class="form-control"/>
																		</div>
																		
																		<div class="row">
																		<div class="col-md-1 col-sm-12" style="margin-right:40px">
																		<div class="form-group">
																		<input type="submit" class="btn btn-success" name="submit" value="Done">
																		</div>
																		</div>
																		</div>
																		</form:form>
																		                                                        
          

                                                </div>

                                            </div>
                                        </div>
            </div>

            <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

            <!-- Include all compiled plugins (below), or include individual files as needed -->
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
            <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
            <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
            <script src="/ProjectDemo/assets/script/autoSuggestion.js"></script>
            <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/jquery.validate.min.js"></script>
            <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/additional-methods.min.js"></script>

            <script src="/ProjectDemo/assets/script/addressValidate.js"></script>
    </body>

    </html>
