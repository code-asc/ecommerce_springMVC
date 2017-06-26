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
                <%@ include file="/WEB-INF/jsp/adminMenu.jsp"%>

                    <div class="col-md-8 col-sm-8">
                        <div class="panel panel-warning">
                            <div class="panel-heading">
                                <h4 class="panel-title">Delete Item</h4>
                            </div>
                            <div class="panel-body">

                                <form id = "formDelete" method = "POST">

                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <select name="category" id="category" class="form-control" form="formDelete">
                                                <option selected disabled>select Category</option>
                                                <c:forEach items = "${requestScope.category}" var = "categoryOption">
                                                        <option value="${categoryOption.categoryID}"><c:out value="${categoryOption.categoryType}"/></option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <select name="subcategory" id="subcategory" class="form-control" form="formDelete">
                                                <option selected disabled>SubCategory Type</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <select name="products" id="products" class="form-control" form="formDelete">
                                                <option selected disabled>select Product</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="col-sm-6 col-md-6">
                                        <div class="form-group">
                                            <input class="btn btn-danger" type="submit" name="submitDelete" value="Delete Product">
                                        </div>
                                    </div>
                                </form>

                                <div id="formDataShow">
                                </div>

                            </div>
                        </div>
                    </div>

            </div>
      
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>


<!-- <script src="/ProjectDemo/assets/script/adminAddMenu.js"></script>
<script src="/ProjectDemo/assets/script/getSubCategoryAjax.js"></script>
<script src="/ProjectDemo/assets/script/getProductAjax.js"></script> -->

<script src="<c:url value="/resources/script/getSubCategoryAjax.js"/>"></script>
<script src="<c:url value="/resources/script/getProductAjax.js"/>"></script>


<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/jquery.validate.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/additional-methods.min.js"></script>


<!-- <script src="/ProjectDemo/assets/script/adminValidate.js"></script> -->

<script src="<c:url value="/resources/script/adminValidate.js"/>"></script>
</sec:authorize>
</body>

</html>
