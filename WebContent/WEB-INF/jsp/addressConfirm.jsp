<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>

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
                <div class="col-md-offset-3 col-sm-4 col-lg-4 col-xm-6 jumbotron text-center">
                <h4>Shipping Address:</h4>
        
        <c:choose>
        <c:when test="${ not empty requestScope.addressQuery}">
        <c:forEach items="${requestScope.addressQuery}" var="addressquery">
        
                    <div>

                            ${addressquery.customerAddress1}
                            <br/>
                            <c:set value="${addressquery.customerAddress2}" var="address_2"/>
                            <c:choose>
                            <c:when test = "${fn:length(address_2) > 0}">
                            <c:out value = "${addressquery.customerAddress2}"/>
                            </c:when>
                            </c:choose>
                            <br/>
                            ${addressquery.customerCity}
                            <br/> ${addressquery.customerState}
                            <br/> ${addressquery.customerCountry}
                            <br/>
                            
                         <c:choose>
                         <c:when test="${allowSingleBuy}">
                       
                        <div class="row">
                            <a href=payment.html?newAddress class="btn btn-info"><i class="fa fa-pencil" aria-hidden="true"></i>&nbsp Edit</a>
                                <a href="address.html" class="btn btn-primary">New Address</a>
                                <a href="payment.html?buy=singleBuy" class="btn btn-success">Proceed</a>
                        </div>
                          </c:when>
                          
                          <c:otherwise>
                            <div class="row">
                            <a href="payment.html?addressAll" class="btn btn-info"><i class="fa fa-pencil" aria-hidden="true"></i>&nbsp Edit</a>
                                <a href="address.html" class="btn btn-primary">New Address</a>
                                <a href="payment.html?buy=cartBuy" class="btn btn-success">Proceed</a>
                        </div>
                          </c:otherwise>
                         </c:choose>
                    </div>
                    </c:forEach>
			</c:when>
			<c:otherwise>
			No Address Exists. Need to edit Address field
			<div class="row">
			<a href="payment.html?addressAll" class="btn btn-info"><i class="fa fa-pencil" aria-hidden="true"></i>&nbsp Edit</a>
			</div>
			</c:otherwise>
        </c:choose>
            </div>
        </div>
        </div>

        <%-- <div class="container-fluid">
            <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12 col-lg-12">
                  <cfcache action="cache" timespan="#createTimespan(0,14,0,0)#" >
                    <cfinclude template="/common/footer.cfm" />
                  </cfcache>
                </div>
            </div>
        </div> --%>
        
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>
