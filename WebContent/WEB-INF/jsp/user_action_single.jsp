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
                                                                <strong>Rs.<c:out value="${product.unitPrice-(product.unitPrice*(product.discount/100))}"/></strong>
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
                                                            <br/>
                                                        </div>

                                                        <div class="col-md-3 col-sm-3 col-xm-3 col-lg-3">

                                                            <br/>
<!--                                                             <cfif NOT StructKeyExists(SESSION, "stLoggedInUser")>
                                                                <button class="btn btn-success" disabled="true"><i class="fa fa-credit-card" aria-hidden="true"></i> &nbspBuy Now</button>

                                                                <button class="btn btn-info" id="onAddCart" disabled="true"><i class="fa fa-shopping-cart" aria-hidden="true"></i> &nbspAdd To Cart</button>
                                                                <cfelse>

                                                                    - Condition for admin -
                                                                    <cfif NOT SESSION.stLoggedInUser.userRole EQ 'admin'>
                                                                        <cfif LOCAL.retriveProduct.unitInStock GT 0>
                                                                            <a class="btn btn-success" href="/view/user_action_single.cfm?buyNow"><i class="fa fa-credit-card" aria-hidden="true"></i> &nbspBuy Now</a>
                                                                              <button class="btn btn-info" id="onAddCart"><i class="fa fa-shopping-cart" aria-hidden="true"></i> &nbspAdd To Cart</button>
                                                                            <cfelse>
                                                                                <button class="btn btn-warning" disabled="true">No Stock</button>

                                                                        </cfif>



                                                                        <cfelse>

                                                                            <a class="btn btn-primary" href="/view/adminProductEdit.cfm?productID=#url.productID#"><i class="fa fa-pencil" aria-hidden="true"></i> &nbspEdit</a>
                                                                            <a class="btn btn-danger" href="/view/user_action_single.cfm?removeProduct=#LOCAL.retriveProduct.productID#"><i class="fa fa-trash" aria-hidden="true"></i> &nbspRemove</a>
                                                                            
                                                                           
                                                                    </cfif>
                                                            </cfif> -->
                                                            
                                                            <c:choose>
                                                            <c:when test="${not sessionScope.isUserLoggedIn}">
                                                            	<button class="btn btn-success" disabled="true"><i class="fa fa-credit-card" aria-hidden="true"></i> &nbspBuy Now</button>

                                                                <button class="btn btn-info" id="onAddCart" disabled="true"><i class="fa fa-shopping-cart" aria-hidden="true"></i> &nbspAdd To Cart</button>
                                                            </c:when>
                                                            
                                                            <c:otherwise>
                                                            <button class="btn btn-success" ><i class="fa fa-credit-card" aria-hidden="true"></i> &nbspBuy Now</button>

                                                                <button class="btn btn-info" id="onAddCart"><i class="fa fa-shopping-cart" aria-hidden="true"></i> &nbspAdd To Cart</button>
                                                            </c:otherwise>
                                                            </c:choose>
                                                        </div>

                                                        <div class="col-md-3 col-sm-3 col-xm-3 col-lg-4" id="infoAboutCart">
                                                        </div>

                                                </cfoutput>
                                                </c:forEach>
                                                </div>

<!--                                                 -Similar Products -

                                                <div class="row" style="margin-top:35px;margin-bottom:40px;border-top:1px solid #eaeaec">
                                                  <cfif LOCAL.retriveProduct.recordCount GT 0>
                                                    <cfset LOCAL.suggestProduct=LOCAL.productQuery.similarProducts(subCategoryID=#LOCAL.retriveProduct.subCategoryID#,productID=#LOCAL.retriveProduct.productID#)>

                                                    <cfif NOT isNull(LOCAL.suggestProduct)>
                                                        <h4>Similar Products</h4>

                                                        <cfoutput query="LOCAL.suggestProduct">
                                                            <div class="col-sm-3 col-md-3 col-xs-2">
                                                                <a href="/view/user_action_single.cfm?productID=#LOCAL.suggestProduct.productID#">
                                                                    <div class="itemthumb"> <img src="#LOCAL.suggestProduct.thumbNailPhoto#" class="img-responsive"></div>
                                                                </a>
                                                                <br/>
                                                                <strong style="color:black">#LOCAL.suggestProduct.brandName#</strong>
                                                                <br/>
                                                                <cfif LOCAL.retriveProduct.discount GT 0>
                                                                    <strike>Rs.#LOCAL.retriveProduct.unitPrice#</strike>
                                                                    <p><strong>Rs.#LsNumberFormat(precisionEvaluate(LOCAL.suggestProduct.unitPrice-(LOCAL.suggestProduct.unitPrice*(LOCAL.suggestProduct.discount/100))),"0.00")#</strong></p>
                                                                    <h5>(#LOCAL.retriveProduct.discount#% <i>Off</i>)<h5>
  <cfelse>
    <strong>Rs.#LOCAL.suggestProduct.unitPrice#</strong>
</cfif>
</div>
    </cfoutput>
</cfif>
</cfif>
</div>
<cfelse>
<div class="row">
  <cfinclude template="/common/productNotFound.cfm" />
</div>
</cfif>
</cfif>


- -

</cfif>
<div class="container-fluid">
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
