
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title></title>

        <!-- Bootstrap -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="/assets/css/slideRender.css">
        <link rel="stylesheet" href="/assets/css/transformEffect.css">
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
      <!-- <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.2/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script> -->
    <![endif]-->
    </head>

    <body>
        <!---  <div ng-include="'header.cfm'">

    </div>
    --->


                <div class="container-fluid" id="changeMargin" style="margin-bottom:100px">
                    <div class="row">

                        <div id="slider" class="carousel slide" style="width: 1000px ;margin: 0 auto" data-ride="carousel">

                            <ol class="carousel-indicators">
                            </ol>

                            <div class="carousel-inner">

                            </div>

                            <a href="#slider" class="left carousel-control" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
                            <a href="#slider" class="right carousel-control" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>

                        </div>

                    </div>

                    <div class="container">
                        <div class="row" id="thumbNailRow">

                        </div>
                    </div>
                </div>

                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-12 col-sm-12 col-xs-12 col-lg-12">
                          <cfcache action="cache" timespan="#createTimespan(0,14,0,0)#" >
                            <cfinclude template="/common/footer.cfm" />
                          </cfcache>
                        </div>
                    </div>
                </div>

                <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

                <!-- Include all compiled plugins (below), or include individual files as needed -->
                <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

                <script src="/assets/script/homeAjax.js"></script>
                <script src="/assets/script/mainAjaxCallForHomePage.js"></script>
                <script src="/assets/script/autoSuggestion.js"></script>

    </body>

    </html>
