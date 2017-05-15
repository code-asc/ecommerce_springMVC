<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">


        <!-- Bootstrap -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="/ProjectDemo/assets/css/panel.css">
        <link rel="stylesheet" href="/ProjectDemo/assets/css/transformEffect.css">
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.2/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    </head>

    <body>
<%@include file="/WEB-INF/jsp/header.jsp" %>

                    <div class="container-fluid">
                        <div class="row">
                
                             
                            <c:choose>
                            <c:when test="${not empty requestScope.formBrands}">

                                <div class="col-md-2 col-sm-2 col-xm-2 col-lg-2" style="margin-bottom:80px">
                                    <div class="panel panel-primary behclick-panel">
                                        <div class="panel-heading">
                                            <h4 class="panel-title"><i class="fa fa-filter" aria-hidden="true"></i>&nbspFilter By</h4>
                                        </div>
                                        <div class="panel-body">
                                            <div class="panel-heading">
                                                <h4 class="panel-title"><a href="#collapse0" data-toggle="collapse"><i class="fa fa-caret-down" aria-hidden="true"></i>&nbsp Brand</a></h4>
                                            </div>
                                            <div id="collapse0" class="panel-collapse collapse in">

                                         <c:forEach items="${requestScope.formBrands}" var="getBrands">
                                         <ul class="list-group">
                                                            <li class="list-group-item">
                                                                <div class="checkbox">
                                                                    <label>
                                                                        <input type="checkbox" value='${getBrands.brandID}' name="checkBrand" class="checkBrand"> ${getBrands.brandName}
                                                                    </label>
                                                                </div>
                                                            </li>
                                                        </ul>
                                         </c:forEach>
                                             
                                                
                                            </div>

                                            <div class="panel-heading">
                                                <h4 class="panel-title"><a href="#collapse1" data-toggle="collapse"><i class="fa fa-caret-down" aria-hidden="true"></i>&nbsp Discount</a></h4>
                                            </div>
                                            <div id="collapse1" class="panel-collapse collapse in">

                                                <ul class="list-group">
                                                    <li class="list-group-item">
                                                        <div class="checkbox">
                                                            <label>
                                                                <input type="checkbox" value='10' name="checkDiscount" class="checkDiscount"> Flat 10%
                                                            </label>
                                                        </div>
                                                    </li>
                                                    <li class="list-group-item">
                                                        <div class="checkbox">
                                                            <label>
                                                                <input type="checkbox" value='40' name="checkDiscount" class="checkDiscount"> Flat 40%
                                                            </label>
                                                        </div>
                                                    </li>
                                                    <li class="list-group-item">
                                                        <div class="checkbox">
                                                            <label>
                                                                <input type="checkbox" value='50' name="checkDiscount" class="checkDiscount"> Flat 50%
                                                            </label>
                                                        </div>
                                                    </li>
                                                </ul>

                                            </div>


                                        </div>
                                    </div>
                                </div>

                                <div id="filterTarget">


<c:forEach items="${requestScope.product}" var="retriveProduct">
<div class="col-sm-3 col-md-3 col-xm-3 col-lg-3" style="float : left ; margin-bottom:30px">

                <a href="user_action_single.html?productID=${retriveProduct.productID}">
                    <div class="itemthumb"> <img src="${retriveProduct.thumbNailPhoto}" class="img-responsive img-rounded"></div>
                </a>
                       <br/>
                <strong>${retriveProduct.brandName}</strong>
                <p>
                <c:choose>
                <c:when test="${retriveProduct.discount > 0}">
                         <strike>Rs.${retriveProduct.unitPrice}</strike>
                         <strong>Rs.<c:out value="${retriveProduct.unitPrice-(retriveProduct.unitPrice*(retriveProduct.discount/100))}"/></strong>
                         <h5>(${retriveProduct.discount}% <i>Off</i>)<h5>
                         <span class="label label-info">${retriveProduct.productName}</span>
                </c:when>
                <c:otherwise>
                <strong>Rs.${retriveProduct.unitPrice}</strong>
          <div class="label label-success">${retriveProduct.productName}</div>
                </c:otherwise>
                </c:choose>
                </div>
</c:forEach>
</div>
</c:when>
<c:otherwise>
  <cfinclude template="/common/productNotFound.cfm" />
  </c:otherwise>
  </c:choose>
  
</div>
</div>



    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <!---<script src="./script/singleSelectOption.js"></script>--->
    <script src="/ProjectDemo/assets/script/categoryAjax.js"></script>
    <script src="/ProjectDemo/assets/script/autoSuggestion.js"></script>
  </body>
</html>