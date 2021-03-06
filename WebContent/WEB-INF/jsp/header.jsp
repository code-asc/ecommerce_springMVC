<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- <link rel="stylesheet" href="/ProjectDemo/assets/css/csstyle.css">
<link rel="stylesheet" href="/ProjectDemo/assets/css/notification.css">
<link rel="stylesheet" href="/ProjectDemo/assets/css/signup.css"> -->

<link rel="stylesheet"
	href="<c:url value ="/resources/css/csstyle.css"/>">
<link rel="stylesheet"
	href="<c:url value ="/resources/css/notification.css"/>">
<link rel="stylesheet"
	href="<c:url value ="/resources/css/signup.css"/>">

<!---<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>--->

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link
	href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css"
	rel="Stylesheet"></link>

<!-- <script src="/ProjectDemo/assets/script/headerMenuData.js"></script> -->
<script src="<c:url value="/resources/script/headerMenuData.js"/>"></script>


</head>

<body style="margin-top: 110px">

	<nav class="navbar navbar-default navbar-fixed-top navbar-static-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button class="btn btn-default navbar-toggle" data-toggle="collapse"
				data-target="#myMenu">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<!-- <a href="index.html"><img class=" img-size" src="/ProjectDemo/assets/images/logo.png"></a> -->
			<a href="index.html"><img class=" img-size"
				src="<c:url value ="/resources/images/logo.png"/>"></a>
		</div>

		<div class="collapse navbar-collapse" id="myMenu">
			<ul class="nav navbar-nav">
				<li><a data-toggle="modal" data-target="#menModal">Men</a> <!-- Modal -->
					<div class="modal fade" id="menModal" role="dialog">
						<div class="modal-dialog">

							<!-- Modal content-->
							<div class="modal-content">

								<div class="modal-body" id="menMenuList"></div>

							</div>

						</div>
					</div></li>

				<li><a data-toggle="modal" data-target="#womenModal">Women</a>

					<!-- Modal -->
					<div class="modal fade" id="womenModal" role="dialog">
						<div class="modal-dialog">

							<!-- Modal content-->
							<div class="modal-content">

								<div class="modal-body" id="womenMenuList"></div>

							</div>

						</div>
					</div></li>

				<li><a data-toggle="modal" data-target="#electronicsModal">Electronics</a>

					<!-- Modal -->
					<div class="modal fade" id="electronicsModal" role="dialog">
						<div class="modal-dialog">

							<!-- Modal content-->
							<div class="modal-content">

								<div class="modal-body" id="electronicMenuList"></div>

							</div>

						</div>
					</div></li>
			</ul>
			<form class="navbar-form navbar-left" action="search.html"
				method="GET">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="Search"
						name="searchVal" id="getSuggestion">
					<div class="input-group-btn">
						<button class="btn btn-default btn-search-color" type="submit"
							style="height: 34px">
							<i class="glyphicon glyphicon-search"></i>
						</button>
					</div>
				</div>
			</form>


			<ul class="nav navbar-nav">
				<c:set var="userProfilePhoto"
					value="${sessionScope.userProfilePhoto}" />
				<li><a href="#" type="button" class="dropdown-toggle"
					data-toggle="dropdown" data-target="loginButton"
					style="padding-top: 10px"> <c:choose>
							<c:when
								test="${fn:length(userProfilePhoto) > 0 && not empty sessionScope.isUserLoggedIn}">
								<span style="float: left"><img
									class="img-circle img-responsive" src="${userProfilePhoto}"
									alt=" "></span>
							</c:when>
							<c:otherwise>
								<i class="fa fa-user" aria-hidden="true"></i>
							</c:otherwise>
						</c:choose> <c:choose>
							<c:when test="${sessionScope.isUserLoggedIn}">
								<c:choose>
									<c:when test="${ empty sessionScope.userProfilePhoto}">

										<span><i class="fa fa-caret-down" aria-hidden="true"></i></span>
									</c:when>

								</c:choose>

							</c:when>
							<c:otherwise>&nbsp Login</c:otherwise>
						</c:choose>

				</a>

					<ul class="dropdown-menu" id="loginButton"
						style="margin-left: 20px; width: 280px;">



						<c:choose>
							<c:when
								test="${sessionScope.isUserLoggedIn &&  sessionScope.role == 'admin'}">
								<li><span id="fullName"><c:out
											value="${sessionScope.userFullName}" /></span></li>
								<li class="divider" />

								<li><a href="admin.html"><span>Admin Edit</span></a></li>

								<li class="divider" />
								<li><a href="logout.html"><i class="fa fa-sign-out"
										aria-hidden="true"></i> &nbsp SignOut</a></li>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${sessionScope.isUserLoggedIn}">
										<li><span id="fullName"><c:out
													value="${sessionScope.userFullName}" /></span></li>
										<li class="divider" />
										<li><a href="userProfileEdit.html"><i
												class="fa fa-pencil" aria-hidden="true"></i>&nbsp Edit
												Profile</a></li>
										<li class="divider" />
										<li><a href="orderDetails.html?page=1"><i
												class="fa fa-credit-card" aria-hidden="true"></i>&nbsp
												Purchases</a></li>
										<li class="divider" />
										<li><a href="logout.html"><i class="fa fa-sign-out"
												aria-hidden="true"></i> &nbsp SignOut</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="login.html"><i class="fa fa-sign-in"
												aria-hidden="true"></i>&nbsp SignIn</a></li>
										<li><a href="signup.html"><i class="fa fa-plus"
												aria-hidden="true"></i>&nbsp SignUp</a></li>
									</c:otherwise>
								</c:choose>
							</c:otherwise>

						</c:choose>
					</ul></li>

				<!--Notifications-->
				<c:choose>
					<c:when
						test="${sessionScope.isUserLoggedIn &&  sessionScope.role != 'admin'}">

						<li><a id="dLabel" role="button" data-toggle="dropdown"
							data-target="#"> <i class="glyphicon glyphicon-bell"
								style="padding-top: 4px"></i>&nbsp

						</a>

							<ul class="dropdown-menu notifications" role="menu"
								aria-labelledby="dLabel">

								<div class="notification-heading">
									<h4 class="menu-title">Notifications</h4>

								</div>
								<li class="divider"></li>
								<div class="notifications-wrapper" id="addNotification"></div>
								<li class="divider"></li>

							</ul></li>
					</c:when>
				</c:choose>

				<c:choose>
					<c:when
						test="${sessionScope.isUserLoggedIn && sessionScope.role != 'admin'}">
						<li><a href="userCart.html"><i
								class="fa fa-shopping-cart" aria-hidden="true"></i>&nbsp
								Cart&nbsp <c:choose>
									<c:when test="${not empty sessionScope.cartCount}">
										<span class="badge" id="traceCount"> <c:out
												value="${sessionScope.cartCount}" />
										</span>
									</c:when>
								</c:choose> </a></li>
					</c:when>
				</c:choose>

			</ul>

		</div>
	</div>
	</nav>


	<!--  <script src="/ProjectDemo/assets/script/onWindowClose.js"></script>
        <script src="/ProjectDemo/assets/script/notificationHeader.js"></script>
        
        <script src="/ProjectDemo/assets/script/onNotificationClick.js"></script>
        <script src="/ProjectDemo/assets/script/headerNotification_user.js"></script> -->

	<script src="<c:url value="/resources/script/onWindowClose.js"/>"></script>
	<script src="<c:url value="/resources/script/notificationHeader.js"/>"></script>
	<script src="<c:url value="/resources/script/onNotificationClick.js"/>"></script>
	<script
		src="<c:url value="/resources/script/headerNotification_user.js"/>"></script>


</body>

</html>
