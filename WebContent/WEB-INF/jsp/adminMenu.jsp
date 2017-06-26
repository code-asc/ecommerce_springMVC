<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- <link rel="stylesheet" href="/ProjectDemo/assets/css/adminCSS.css"> -->
    
    <link rel="stylesheet" href="<c:url value ="/resources/css/adminCSS.css"/>" >
    <!-- Bootstrap -->
    <!---<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
--->
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.2/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
        <div class="col-md-2 col-sm-2 sidebar">
            <ul class="nav nav-pills nav-stacked" style="border: 1px solid #337ab7 ; border-radius:8px">
                <li class="active"><a href="admin.html">DashBoard</a></li>
                <li><a href="adminAdd.html">Add Item</a></li>
                <li><a href="adminOther.html">Add Brand And Category</a></li>
                <li><a href="adminSubCategory.html">Add SubCategory</a></li>
                <li><a href="adminEditForm.html">Edit Item</a></li>
                <li><a href="adminRemoveForm.html">Remove Item</a></li>

            </ul>
        </div>
        </sec:authorize>       
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!---  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
<!--     <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
 -->    

</body>
</html>