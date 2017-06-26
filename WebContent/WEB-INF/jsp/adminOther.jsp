<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
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
		<sec:authorize access="hasRole('ROLE_ADMIN')">
        <%@ include file="/WEB-INF/jsp/header.jsp" %>
            <div class="container-fluid">
            <%@ include file="/WEB-INF/jsp/adminMenu.jsp" %>

                    <div class="col-md-8 col-sm-8">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h4 class="panel-title">Add Other</h4>
                            </div>
                            <div class="panel-body">

                                <div class="row">
                                    <form id="formOtherBrand">
                                        <div class="col-sm-3 col-md-5 col-lg-5">
                                            <div class="form-group">
                                                <input class="form-control" name="brandName" id="brandName" type="text" required placeholder="BrandName">
                                            </div>
                                        </div>

                                        <div class="col-sm-5 col-md-5">
                                            <div class="form-group">
                                                <input class="btn btn-success" type="button" id="addBrand" name="addBrand" value="Add Brand">
                                            </div>
                                        </div>
                                    </form>
                                </div>

                                <div class="row">
                                    <form id="formOtherCategory">
                                        <div class="col-sm-3 col-md-5 col-lg-5">
                                            <div class="form-group">
                                                <input class="form-control" name="category" id="category" type="text" required placeholder="Category">
                                            </div>
                                        </div>

                                        <div class="col-sm-5 col-md-5">
                                            <div class="form-group">
                                                <input class="btn btn-success" type="button" id="addCategory" name="addCategory" value="Add Category">
                                            </div>
                                        </div>
                                    </form>
                                </div>

                                <div class="row">
                                <div id="formOtherData">
                                </div>
                              </div>

                              <div class="row">
                                <div class="col-sm-12 col-md-6 col-xm-6 col-lg-6">
                                  <div id="brandList">
                                  <table class="table-bordered table-condensed table-responsive col-md-6 col-lg-6 col-sm-12 col-xs-12" id="brandListTable">

                                  </table>
                                  </div>
                                </div>

                                <div class="col-sm-12 col-md-6 col-xm-6 col-lg-6">
                                  <div id="categoryList">
                                  <table class="table-bordered table-condensed table-responsive col-md-6 col-lg-6 col-sm-12 col-xs-12" id="categoryListTable">

                                  </table>
                                  </div>
                                </div>
                              </div>

                            </div>
                        </div>
                    </div>
                    </div>
       
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/jquery.validate.min.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/additional-methods.min.js"></script>
    
    <script src="<c:url value="/resources/script/adminValidate.js"/>"></script>
    <script src="<c:url value="/resources/script/brandAndCategoryList.js"/>"></script>
    <script src="<c:url value="/resources/script/adminOther.js"/>"></script>
    
    </sec:authorize>
</body>
</html>
