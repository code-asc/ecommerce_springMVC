<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

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
                <c:set var="total" value="${0}"/>
                    <div class="container">
                    
                    <c:choose>
                    <c:when test="${not empty requestScope.retriveCart}">
                                       
                                <div class="row">
                                    <div class="col-md-12 col-sm-12 col-xm-12 col-lg-12">
                                        <h3 class="text-muted">My Shopping bag</h3>

                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-sm-8 col-md-8 col-xs-12 col-lg-5">
                                    
                                    <c:forEach items="${requestScope.retriveCart}" var="retrivecart"> 
                                            <!---Calucating price --->
                                            
                                            <c:set var="total" value="${total + retrivecart.afterDiscount * retrivecart.quantity}"/>

                                                <div class="col-sm-12 col-md-12 col-xs-12 col-lg-12 well well-sm">

                                                    <div class="col-sm-6 col-md-6 col-xs-6 col-lg-4">
                                                        <img src="${retrivecart.thumbNailPhoto}" class="img-responsive">
                                                    </div>

                                                    <div class="col-sm-6 col-md-6 col-xs-12 col-lg-6">
                                                    <c:set var="totalPrice" value="${retrivecart.afterDiscount * retrivecart.quantity}"/>
                                                    <c:set var="totalQuantityPrice" value="${retrivecart.detailID}${'paraID'}"/>
                                                    <c:set var="eachPrice" value="${retrivecart.detailID}${'eachPrice'}"/>
                                                            
                                                                    <h4>${retrivecart.brandName} ${retrivecart.productName}</h4>
                                                                    <p>Sold by:<strong>${retrivecart.supplierName}</strong></p>
                                                                    <p class="${retrivecart.detailID}">Price(each):Rs.<strong><span id="${eachPrice}">${retrivecart.afterDiscount}</span></strong></p>
                                                                    <p id="${totalQuantityPrice}">Total:Rs.<strong><c:out value="${totalPrice}"/></strong></p>
                                                                    <button value="${retrivecart.detailID}" class="increment"><i class="fa fa-plus" aria-hidden="true"></i></button>&nbsp Qty:<strong><p id="${retrivecart.detailID}" style="display:inline">${retrivecart.quantity}</p></strong>&nbsp
                                                                    <button value="${retrivecart.detailID}" class="decrement"><i class="fa fa-minus" aria-hidden="true"></i></button>
                                                                    <br/>
                                                                    <br/>
                                                                    <a href="deleteFromCart.html?removeFromCart=${retrivecart.detailID}" class="btn btn-warning"><i class="fa fa-trash" aria-hidden="true"></i>&nbsp Remove From Bag</a>

                                                    </div>

                                                </div>

                                       </c:forEach>
                                        
                                    </div>


                                        <div class="col-md-4 col-sm-4 col-xs-12 col-lg-4 alert alert-info">
                                            <cfoutput>
                                            <h5 class="text-muted"> PRICE DETAILS:</h5>
                                            <h6 class="text-success">Bag Total : Rs.<span id="totalPriceAll"><c:out value="${total}"></c:out></span></h6>
                                            <a class="btn btn-success" href="/view/userCart.cfm?buyAll">Place Order</a>
                                              </cfoutput>
                                        </div>


                                </div>
                                
                               
                            </c:when>
                            
                            <c:otherwise>
                             <div class="col-xs-8 col-md-8 col-lg-8 col-sm-8">
                                        <div class="alert alert-warning">
                                            Your bag is empty

                                        </div>
                                        </div>
                            </c:otherwise>
                    </c:choose>


                        </div>
                      </div>
                  
                   
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
        <script src="/ProjectDemo/assets/script/autoSuggestion.js"></script>
        <script src="/ProjectDemo/assets/script/deleteCartAjax.js"></script>
        <script src="/ProjectDemo/assets/script/incrementCart.js"></script>
        <script src="/ProjectDemo/assets/script/decrementCart.js"></script>
    </body>

    </html>