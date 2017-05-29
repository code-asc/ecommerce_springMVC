<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">


        <!-- Bootstrap -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="/ProjectDemo/assets/css/transformEffect.css">

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
             
                                                
                                                <c:choose>
                                                <c:when test="${not empty retriveProduct}">
                                                    <div class="row">
                                                    
                                                    <c:forEach items="${retriveProduct}" var="product">
                                                        <div class="col-md-4 col-sm-4 col-xm-4 col-lg-6" style="float:left">
                                                            <img src="${product.largePhoto}" alt="image not found" class="img-responsive">
                                                        </div>

                                                        <div class="col-md-4 col-sm-4 col-xm-4 col-lg-4">
                                                            <h3>${product.brandName} ${product.productName}</h3>
                                                           
                                                            
                                                            <c:choose>
                                                            <c:when test="${product.discount > 0}">
                                                            	<strike>Rs.${product.unitPrice}</strike>
                                                                <strong>Rs.<c:out value="${product.unitPrice-product.unitPrice*product.discount/100}"/></strong>
                                                                <h4>(${product.discount}% OFF)</h4> ${product.productName}
                                                            </c:when>
                                                            <c:otherwise>
                                                             	<strong>Rs.${product.unitPrice}</strong>
                                                                <br/>${product.productName}
                                                            </c:otherwise>
                                                            </c:choose>
                                                            </p>
                                                            <strong class="label label-primary">Product Info</strong>
                                                            <br/> ${product.productDesc}
                                                            
                                                            
                                                            <!-- <cfif StructKeyExists(SESSION, "stLoggedInUser") AND SESSION.stLoggedInUser.userRole EQ 'admin'>
                                                                <h4>(Left :#LOCAL.retriveProduct.unitInStock#)</h4></cfif>
                                                                
                                                                 -->
                                                                 
                                                                 <c:choose>
                                                                 <c:when test="${ sessionScope.role == 'admin'}">
                                                                  <h4>(Left :${product.unitInStock})</h4>
                                                                 </c:when>
                                                                 </c:choose>
                                                            <br/>
                                                        </div>

                                                        <div class="col-md-3 col-sm-3 col-xm-3 col-lg-3">

                                                            <br/>

                                                            
                                                            <c:choose>
                                                            <c:when test="${not sessionScope.isUserLoggedIn}">
                                                            	<button class="btn btn-success" disabled="true"><i class="fa fa-credit-card" aria-hidden="true"></i> &nbspBuy Now</button>

                                                                <button class="btn btn-info" id="onAddCart" disabled="true"><i class="fa fa-shopping-cart" aria-hidden="true"></i> &nbspAdd To Cart</button>
                                                            </c:when>
                                                            
                                                            <c:otherwise>
                                                            
                                                            <c:choose>
                                                            <c:when test="${sessionScope.isUserLoggedIn  && sessionScope.role == 'admin' }">
                                                            	
                                                            	<a class="btn btn-primary" href="adminProductEdit.html?productID=${productID}"><i class="fa fa-pencil" aria-hidden="true"></i> &nbspEdit</a>
                                                                <a class="btn btn-danger" href="user_action_single_delete.html?removeProduct=${productID}"><i class="fa fa-trash" aria-hidden="true"></i> &nbspRemove</a>
                                                            	
                                                            </c:when>
                                                            
                                                            <c:otherwise>
                                                            
                                                            
                                                            <c:choose>
                                                            <c:when test="${product.unitInStock < 1}">
                                                            <h4>Out Of Stock</h4>
                                                            </c:when>
                                                            <c:otherwise>
                                                            <a href="addressConfirm.html?buy=singleBuy" class="btn btn-success"><i class="fa fa-credit-card" aria-hidden="true"></i> &nbspBuy Now</a>
                                                                <button class="btn btn-info" id="onAddCart"><i class="fa fa-shopping-cart" aria-hidden="true"></i> &nbspAdd To Cart</button>
                                                           </c:otherwise>
                                                            </c:choose>
                                                            
                                                            </c:otherwise>
                                                            </c:choose>
                                                            
                                                            </c:otherwise>
                                                            </c:choose>
                                                        </div>

                                                        <div class="col-md-3 col-sm-3 col-xm-3 col-lg-4" id="infoAboutCart">
                                                        </div>

                                                </cfoutput>
                                                </c:forEach>
                                                </div>
                                                
                                                		<div class="row" style="margin-top:35px;margin-bottom:40px;border-top:1px solid #eaeaec">
                                                <c:choose>
                                                <c:when test="${not empty suggestProduct}">
                                                
                                                        <h4>Similar Products</h4>
                                                        <c:forEach items="${requestScope.suggestProduct}" var="suggestProduct">
                                                            <div class="col-sm-3 col-md-3 col-xs-2">
                                                                 <a href="user_action_single.html?productID=${suggestProduct.productID}">
                                                                     <div class="itemthumb"> <img src="${suggestProduct.thumbNailPhoto}" class="img-responsive"></div>
                                                                </a>
                                                                <br/>
                                                                <strong style="color:black">${suggestProduct.brandName}</strong>
                                                                <br/>

															<c:choose>
															<c:when test="${suggestProduct.discount > 0}">
															<strike>Rs.${suggestProduct.unitPrice}</strike>
															 <p><strong>Rs.${suggestProduct.unitPrice-suggestProduct.unitPrice*suggestProduct.discount/100}</strong></p>
															<h5>(${suggestProduct.discount}% <i>Off</i>)</h5>
															</c:when>
															<c:otherwise>
															<strong>Rs.${suggestProduct.unitPrice}</strong>
															</c:otherwise>
															</c:choose>
															</div>
															</c:forEach>
															</c:when>
															</c:choose>
															</div>
                                                
                                                </c:when>
                                                <c:otherwise>
                                                	<%@ include file="/WEB-INF/jsp/productNotFound.jsp"%>
                                                </c:otherwise>
                                                </c:choose>
                                                </div>


                                                
												


<!--<div class="container-fluid">
    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12 col-lg-12">
          <cfcache action="cache" timespan="#createTimespan(0,14,0,0)#" >
            <cfinclude template="/common/footer.cfm" />
          </cfcache>
        </div>
    </div>
</div> -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <script src="/ProjectDemo/assets/script/autoSuggestion.js"></script>
    <script src="/ProjectDemo/assets/script/addCartAjax.js"></script>
    <script src="/ProjectDemo/assets/script/singleBuyAjax.js"></script>

  </body>
</html>
