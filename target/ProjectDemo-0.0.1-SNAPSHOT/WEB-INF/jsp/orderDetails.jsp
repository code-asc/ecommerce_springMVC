<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

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

<c:set var="start" value="${0}"/>
        <c:set var="totalPerPage" value="${4}"/>
        <c:set var="page" value="${1}"/>
        <c:set var="end" value="${requestScope.start+totalPerPage }"/>
        <fmt:formatNumber var="totalPage" value="${total/totalPerPage}" pattern="#"/>
        <%@ include file="/WEB-INF/jsp/header.jsp" %>
      
          

            
                <c:choose>
                <c:when test="${total < 1}">
                <div class="col-sm-12 col-md-12 col-lg-12">
                                            <div class="alert alert-info text-center">
                                              No previous purchases....

                                          </div>
                                      </div>
                </c:when>
                <c:otherwise>
               

                                          
                         <div class="container" style="margin-left:80px">
                            <c:forEach items="${requestScope.detailQuery }" var="detailquery">
                            <!---  <div class="row well well-sm alert well-dismissible">--->
                                <div class="row well well-sm">
                               


                                        <div class="col-sm-8 col-md-8 col-xm-8 col-lg-12" style="margin-bottom:10px">
                                            <div class="col-sm-2 col-md-2 col-lg-5">
                                                <img src="${detailquery.thumbNailPhoto}" alt="not found" class="img-responsive">
                                            </div>
                                            <div class="col-sm-5 col-md-5 col-lg-6">
                                              <h2>Order ID :${detailquery.orderID}</h2>
                                                <h4>${detailquery.brandName} ${detailquery.productName}</h4>
                                                <p>Price:${detailquery.detailPrice}</p>
                                                <p>Qty:${detailquery.quantity}</p>
                                          </div>
                                        </div>
                                        <br/>
                                    <div class="text-center">
                                        <h5>Shipping Address:</h5> ${detailquery.customerAddress1}
                                        <br/>
                                        <c:set var="address_2" value="${detailquery.customerAddress2 }"/>
                                        <c:choose>
                            <c:when test = "${fn:length(address_2) > 0}">
                            <c:out value = "${detailquery.customerAddress2}"/>
                            </c:when>
                            </c:choose>
                                        ${detailquery.customerCity}
                                        <br/> ${detailquery.customerState}
                                        <br/> ${detailquery.customerCountry}
                                        <br/> Ordered Date :${detailquery.orderDate}
                                        <br/> Total : Rs.<strong>${detailquery.orderAmount}</strong>
                                    </div>
                                </div>
                                </c:forEach>
                        </div>
                              
                              <c:choose>
                              <c:when test="${totalPage > 1}">
                              	   <div class="container-fluid">
                                                      <div class="row text-center">
                                                		<div id="paging" class="col-sm-12 col-md-12 col-xm-12 col-lg-12">
                                                		<c:set var="start" value="${0}"/>
                                                		<c:forEach begin="${1}" end="${totalPage }" var="page">
                                                		<c:choose>
                                                		<c:when test="${page == requestScope.page}">
                                                		
                                                			<c:out value="${page}"/>
                                                		
                                                		</c:when>
                                                		<c:otherwise>
                                                			<a href="orderDetails.html?&start=${start}&page=${page}">${page}</a>
                                                		</c:otherwise>
                                                		</c:choose>
                                                		
                                                		<c:choose>
                                                		<c:when test="${page != totalPage }">
                                                		|
                                                		</c:when>
                                                		</c:choose>
                                                		<c:set var="start" value="${start + totalPerPage }"/>
                                                		</c:forEach>
                                                		
                                                		

                                                		</div>
                                                  </div>
                                                </div><br>
                              </c:when>
                              </c:choose>
                                               
                                    
                        
                           </c:otherwise>
                           </c:choose>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    
    
   <!--<script src="/assets/script/deleteFromOrderHistory.js"></script> -->
   <script src="<c:url value="/resources/script/deleteFromOrderHistory.js"/>"></script>
    
    
</body>
</html>
                           