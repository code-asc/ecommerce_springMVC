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

    <!---<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>--->
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.2/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" href="/ProjectDemo/assets/css/adminCSS.css">
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    	    	
      </head>

<body>


                    <body>
                    <%@ include file="/WEB-INF/jsp/header.jsp" %>
                    <%@ include file="/WEB-INF/jsp/adminMenu.jsp"%>
                       
                                <div class="container-fluid">


                                    <div class="col-md-8 col-sm-8">
                                        <div class="panel panel-primary">
                                            <div class="panel-heading">
                                                <h4 class="panel-title">DashBoard</h4>
                                            </div>
                                            <div class="panel-body">

                                               <div class="row">
												    <table>
												    <tr>
												    <td><div id="chart_div"></div></td>
												    <td> <div id="chart_div_1"></div></td>
												    </tr>
												    </table>
												    
   
    											</div>

                                                <div class="row text-center">
                                                    <div class="col-md-3 col-sm-3 text-center colorBlue" id="customerInfo">
                                                        <div class="col-sm-8 col-md-8">
                                                                <h3><c:out value="${requestScope.customer }"/></h3>
                                                                <h4 class="adminHeaderInfo">Registered</h4>
                                                        </div>
                                                        <div class="col-md-3 col-sm-3" id="iTagDiv">
                                                            <span class="fa-stack fa-2x">
  <i class="fa fa-circle-thin fa-stack-2x setCircle"></i>

  <i class="fa fa-user fa-stack-1x setCircle"></i>
</span>

                                                        </div>
                                                    </div>

                                                    <div class="col-md-3 col-sm-3 text-center colorGreen" id="customerInfo">
                                                        <div class="col-sm-8 cl-md-8">
                                                            <cfoutput>
                                                                <h3><c:out value="${requestScope.product }"/></h3>
                                                                <h4 class="adminHeaderInfo">Products</h4>
                                                        </div>
                                                        <div class="col-md-2 col-sm-2" id="iTagDiv">
                                                            <span class="fa-stack fa-2x">
<i class="fa fa-circle-thin fa-stack-2x setCircle"></i>

<i class="fa fa-file-text-o fa-stack-1x setCircle"></i>
</span>

                                                        </div>
                                                        </cfoutput>
                                                    </div>

                                                    <div class="col-md-3 col-sm-3 text-center colorShip" id="customerInfo">
                                                        <div class="col-sm-8 cl-md-8">
                                                            <cfoutput>
                                                                <h3>${requestScope.shipping }</h3>
                                                                <h4 class="adminHeaderInfo">shipping</h4>
                                                        </div>
                                                        <div class="col-md-2 col-sm-2" id="iTagDiv">
                                                            <span class="fa-stack fa-2x">
<i class="fa fa-circle-thin fa-stack-2x setCircle"></i>

<i class="fa fa-truck fa-stack-1x setCircle"></i>
</span>

                                                        </div>
                                                        </cfoutput>
                                                    </div>

                                                    <div class="col-md-3 col-sm-3 text-center colorDarkGreen" id="customerInfo">
                                                        <div class="col-sm-8 cl-md-8">
                                                            <cfoutput>
                                                                <h3><span id="getOnlineUserCount"></span></h3>
                                                                <h4 class="adminHeaderInfo">Online</h4>
                                                        </div>
                                                        <div class="col-md-2 col-sm-2" id="iTagDiv">
                                                            <span class="fa-stack fa-2x">
<i class="fa fa-circle-thin fa-stack-2x setCircle"></i>

<i class="fa fa-users fa-stack-1x setCircle"></i>
</span>

                                                        </div>
                                                        </cfoutput>
                                                    </div>

                                                    <div class="col-md-3 col-sm-3 text-center colorRed" id="customerInfo">
                                                        <div class="col-sm-8 cl-md-8">
                                                            <cfoutput>
                                                                <h3>${requestScope.category}</h3>
                                                                <h4 class="adminHeaderInfo">Category</h4>
                                                        </div>
                                                        <div class="col-md-2 col-sm-2" id="iTagDiv">
                                                            <span class="fa-stack fa-2x">
<i class="fa fa-circle-thin fa-stack-2x setCircle"></i>

<i class="fa fa-list fa-stack-1x setCircle"></i>
</span>

                                                        </div>
                                                        </cfoutput>
                                                    </div>

                                                    <div class="col-md-3 col-sm-3 text-center colorLightBlue" id="customerInfo">
                                                        <div class="col-sm-8 cl-md-8">
                                                            <cfoutput>
                                                                <h3>${requestScope.subCategory }</h3>
                                                                <h4 class="adminHeaderInfo">SubCategory</h4>
                                                        </div>
                                                        <div class="col-md-2 col-sm-2" id="iTagDiv">
                                                            <span class="fa-stack fa-2x">
<i class="fa fa-circle-thin fa-stack-2x setCircle"></i>

<i class="fa fa-list-ol fa-stack-1x setCircle"></i>
</span>

                                                        </div>
                                                        </cfoutput>
                                                    </div>

                                                </div>

                                                <!--- web sockets--->
                                                <div class="row">
                                                    <div class="col-md-12 col-sm-12">
                                                        <div class="well well-lg">
                                                            <cfwebsocket name="myworld" onmessage="msgHandler" onopen="openHandler" subscribeTo="world" />
                                                            <script>
                                                            var msgHandler=function(message)
                                                            {
                                                              
                                                            }
                                                              var sendMessageTo = function() {
                                                                $("#postedDetail").empty();
                                                                $("#postedDetail").css({"display":"block"});
                                                                  var message = document.getElementById("updateForAll").value;
                                                                  $("#updateForAll").val(" ");
                                                                  if(message.trim())
                                                                  {
                                                                  myworld.authenticate("admin", "admin");
                                                                  if(myworld.publish("world", message))
                                                                  {
                                                                    $("#postedDetail").append("<div class='alert alert-success'>Posted....</div>").delay(5000).fadeOut();
                                                                  }
                                                                }

                                                              }
                                                          </script>

                                                            <h3 style="margin-top:0px ; margin-bottom:20px">Post Notifications</h3>
                                                            <div class='form-group'>
                                                                <textarea class='form-control' name='updateForAll' id='updateForAll' rows='5' cols='42'></textarea>
                                                            </div>

                                                            <input id="postForAll" class="btn btn-success" type="button" onclick="sendMessageTo()" value="Post It">
                                                        </div>
                                                    </div>
                                                </div>
                                                <!--- --->

                                            </div>
                                        </div>
                                        <div class="row">
                                          <div id="postedDetail">
                                    </div>



                                      </div>
                                    </div>
                                </div>

    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script type="text/javascript">

      // Load the Visualization API and the corechart package.
      google.charts.load('current', {'packages':['corechart']});

      google.charts.setOnLoadCallback(drawChart);
      google.charts.setOnLoadCallback(drawChart2);
      
      
      function drawChart() {
    	  var brandName;
		  var total;
    	  var data = new google.visualization.DataTable();
			data.addColumn('string' , 'Brands');
			data.addColumn('number' , 'Quantity');
			$.ajax({
					 url : "getHighestSoldProduct.json",
					 async : false,
			}).done(function(responseText , textStatus , jqXHR){
					$.each(responseText , function(name , value){
						$.each(value , function(index , key){
							if(index == 'brandName')
								{
									brandName = key ;
									console
								}
							else if(index == 'total')
								{
								    
									total = key;
								}
						})
						data.addRow([brandName , parseInt(total)]);
					})
					

					});
			
			
			var option = {'title' : 'Most Purchased Brand' , width : 400 , height : 300};
			var chart = new google.visualization.PieChart(document.getElementById("chart_div"));
			chart.draw(data , option);

      }
      
      function drawChart2() {
    	  var brandName;
		  var total;
    	  var data = new google.visualization.DataTable();
			data.addColumn('string' , 'Country');
			data.addColumn('number' , 'Total');
			$.ajax({
					 url : "getCountrySoldProduct.json",
					 async : false,
			}).done(function(responseText , textStatus , jqXHR){
					$.each(responseText , function(name , value){
						$.each(value , function(index , key){
							if(index == 'customerCountry')
								{
									brandName = key ;
									console
								}
							else if(index == 'total')
								{
								    
									total = key;
								}
						})
						data.addRow([brandName , parseInt(total)]);
					})
					

					});
			
			
			var option = {'title' : 'Sale in Countries' , width : 400 , height : 300};
			var chart = new google.visualization.PieChart(document.getElementById("chart_div_1"));
			chart.draw(data , option);

      }

    </script>
    
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    <script src="/ProjectDemo/assets/script/adminEditAjax.js"></script>
    <script src="/ProjectDemo/assets/script/autoSuggestion.js"></script>

    </body>

</html>
