<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.2/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    </head>


        <body>
        <%@ include file="/WEB-INF/jsp/header.jsp" %>
            
                <div class="container-fluid">
                <%@ include file="/WEB-INF/jsp/adminMenu.jsp" %>

                        <div class="col-md-8 col-sm-8">
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    <h4 class="panel-title">Add Item</h4>
                                </div>
                                <div class="panel-body">
                                
                                <form id="formOption">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                            <input  class="form-control" name="productName" id="productName"  placeholder="Product Name"/>
                                            </div>
                                        </div>

                                        <div class="col-md-6">
                                            <div class="form-group">
                                            	<select name="brand" id="brand" class="form-control" form="formOption">
                                            		<option selected disabled>Brand</option>
                                            			<c:forEach items="${requestScope.brand}" var="brandOption">
                                                        	<option value="${brandOption.brandID}"><c:out value="${brandOption.brandName}"/></option>
                                                 		</c:forEach>
                                                    
                                            	</select>
                                                    
                                            </div>
                                        </div>

                                        <div class="col-md-6">
                                            <div class="form-group">
                                            <select  name="category" id="category" class="form-control" form="formOption">
                                            
                                                    <option selected disabled>Category Type</option>
                                                    
                                                    <c:forEach items = "${requestScope.category}" var = "categoryOption">
                                                        <option value="${categoryOption.categoryID}"><c:out value="${categoryOption.categoryType}"/></option>
                                                       </c:forEach>
                                             </select>
                                            </div>
                                        </div>

                                        <div class="col-md-6">
                                            <div class="form-group">
                                            <select  name="shipping" id="shipping" class="form-control" form="formOption">
                                            	<option selected disabled>Shipping</option>
                                                    <c:forEach items = "${requestScope.shipping}" var = "shippingOption">
                                                        <option value="${shippingOption.shippingID}"><c:out value="${shippingOption.companyName}"/></option>
                                                       </c:forEach>
                                            </select>
                                                    
                                            </div>
                                        </div>

                                        <div class="col-md-6">
                                            <div class="form-group">
                                            <select  name="supplier" id="supplier" class="form-control" form="formOption">
                                            	<option selected disabled>Supplier</option>
                                            	 <c:forEach items = "${requestScope.supplier}" var = "supplierOption">
                                                        <option value="${supplierOption.supplierID}"><c:out value="${supplierOption.supplierName}"/></option>
                                                       </c:forEach>
                                                    
                                            </select>
                                                
                                                    
                                                
                                            </div>
                                        </div>

                                        <div class="col-md-6">
                                            <div class="form-group">
                                            <select  name="subcategory" id="subcategory" class="form-control" form="formOption">
                                            	<option selected disabled>SubCategory Type</option>
                                            </select>
                                                    
                                            </div>
                                        </div>

                                        <div class="col-md-2 col-sm-2">
                                            <div class="form-group">
                                            	<input class="form-control" name="price" id="price"  placeholder="Price"/>
                                            </div>
                                        </div>

                                        <div class="col-md-2 col-sm-2">
                                            <div class="form-group">
                                            	<input  class="form-control" name="quantity" id="quantity" placeholder="Quantity"/>
                                               
                                            </div>
                                        </div>

                                        <div class="col-md-2 col-sm-2">
                                            <div class="form-group">
                                            	<input class="form-control" name="discount" id="discount" placeholder="Discount"/>
                                            </div>
                                        </div>

                                        <div class="col-md-2 col-sm-2">
                                            <div class="form-group">
                                            	<input  class="form-control" name="rating" id="rating" placeholder="Rating"/>
                                                
                                            </div>
                                        </div>



                                        <div class="form-group">
                                            <input type="file" name="thumbNail" id="thumbNail" class="file" style="visibility: hidden ; position:absolute">
                                                <div class="input-group col-sm-5 col-md-5" style="margin-left:17px">

                                                    <input type="text" id="thumbNailText" class="form-control input-md" disabled placeholder="Upload ThumbNail Image">
                                                    <span class="input-group-btn">
       <button class="browse btn btn-primary input-md" type="button"><i class="glyphicon glyphicon-search"></i> Browse</button>
     </span>
                                                </div>
                                        </div>

                                        <div class="col-md-12 col-sm-12">
                                            <div class="form-group">
                                            	<input  class="form-control" name="thumbNailType" id="thumbNailType"  placeholder="ThumbNail Type"/>
                                                
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <input type="file" name="largePhoto" id="largePhoto" class="file" style="visibility: hidden ; position:absolute">
                                                <div class="input-group col-sm-5 col-md-5" style="margin-left:17px">

                                                    <input type="text" id="largePhotoText" class="form-control input-md" disabled placeholder="Upload Large Image">
                                                    <span class="input-group-btn">
       <button class="browse btn btn-primary input-md" type="button"><i class="glyphicon glyphicon-search"></i> Browse</button>
     </span>
                                                </div>
                                        </div>

                                        <div class="col-md-6 col-sm-6">
                                            <div class="form-group">
                                            	<input class="form-control" name="largePhotoType" id="largePhotoType"  placeholder="LargePhoto Type"/>
                                                
                                            </div>
                                        </div>

                                        <div class="form-group">
                                        <textarea class="form-control" name="productDesc" id="productDesc" placeholder="productDesc"></textarea>
                                            
                                        </div>

                                        <div class="text-center">
                                            <div class="form-group">
                                                <input class="btn btn-primary" type="button" name="submitData" value="Add Product">
                                            </div>
                                        </div>
                                    </form>

                                    <div id="formData">
                                    </div>

                                </div>
                            </div>
                        </div>

                </div>
                

    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    
    <script src="<c:url value="/resources/script/adminAddMenu.js"/>"></script>
    <script src="<c:url value="/resources/script/getSubCategoryAjax.js"/>"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/jquery.validate.min.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/additional-methods.min.js"></script>
    <script src="<c:url value="/resources/script/adminValidate.js"/>"></script>
    <script src="<c:url value="/resources/script/uploadImage.js"/>"></script>
    <script src="<c:url value="/resources/script/autoSuggestion.js"/>"></script>
    
 <!--    <script src="/ProjectDemo/assets/script/adminAddMenu.js"></script>
    <script src="/ProjectDemo/assets/script/getSubCategoryAjax.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/jquery.validate.min.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/additional-methods.min.js"></script>
    <script src="/ProjectDemo/assets/script/adminValidate.js"></script>
    <script src="/ProjectDemo/assets/script/uploadImage.js"></script>
    <script src="/ProjectDemo/assets/script/autoSuggestion.js"></script> -->
    </body>

    </html>
