<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
  <head>
    <!--Load the AJAX API-->
    
    
    
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
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
			
			
			var option = {'title' : 'Most Purchased Brand' , width : 400 , height : 300};
			var chart = new google.visualization.PieChart(document.getElementById("chart_div_1"));
			chart.draw(data , option);

      }

    </script>
  </head>

  <body>
    <!--Div that will hold the pie chart-->
    <div>
    <table>
    <tr>
    <td><div id="chart_div"></div></td>
    <td> <div id="chart_div_1"></div></td>
    </tr>
    </table>
    
   
    </div>
  </body>
</html>