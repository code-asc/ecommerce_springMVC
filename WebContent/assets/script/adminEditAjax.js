$(document).ready(function(){

var onlineUserCount=setInterval("ajaxCallForOnlineUser()",4000);
  $("select[name='products']").on("change",function()
{
  if($("#formEdit").valid())
ajaxCall();
})
});

function ajaxCall()
{
  $("#formDataShow").empty();
  var productID=parseInt($("select[name='products']").val());
$.ajax({
  url : 'getProductInfoByProductID.json',
  data : {productID:productID},
}).done(function(responseText,textStatus,jsXHR){
  //alert(responseText);

  $.each(responseText,function(index,key){
    $.each(key,function(index,value)
  {
    if(index=="productDesc")
    {
      $("#formDataShow").append("<div class='col-sm-12 col-md-12'><div class='form-group'><textarea class='form-control' form='formEdit' name='productDesc' id='productDesc' rows='4' cols='40'>"+value+"</textarea></div></div>")
    }
    else if (index=="unitPrice") {
      $("#formDataShow").append("<div class='col-sm-2 col-md-2'><div class='form-group'><label for='unitPrice'>Price</label><input class='form-control' form='formEdit' id='unitPrice' name='unitPrice' type='number' value="+value+"></div></div>")
    }

    else if(index=="unitInStock"){
          $("#formDataShow").append("<div class='col-sm-2 col-md-2'><div class='form-group'><label for='unitInStock'>Stock</label><input class='form-control' form='formEdit' id='unitInStock' name='unitInStock' type='number' value="+value+"></div></div>")
    }

    else if(index=="thumbNailPhoto"){
      $("#formDataShow").append("<div class='col-sm-11 col-md-11'><div class='form-group'><label for='thumbNailPhoto'>ThumbNail Path</label><input class='form-control' form='formEdit' id='thumbNailPhoto' name='thumbNailPhoto' type='text' value="+value+"></div></div>");

    }

    else if(index=="largePhoto"){
      $("#formDataShow").append("<div class='col-sm-11 col-md-11'><div class='form-group'><label for='largePhoto'>LargePhoto Path</label><input class='form-control' form='formEdit' id='largePhoto' name='largePhoto' type='text' value="+value+"></div></div>")
    }
    else if(index=="discount") {
          $("#formDataShow").append("<div class='col-sm-2 col-md-2'><div class='form-group'><label for='discount'>Discount</label><input class='form-control' form='formEdit' id='discount' name='discount' type='number' value="+value+"></div></div>")
    }
  })
  })
}).fail(function(jqXHR,textStatus,erroThrown){
  $("#formDataShow").append("<div class='col-sm-2 col-md-2'>Something went wrong . Please try later</div>")

})
}

function ajaxCallForOnlineUser()
{
  $.ajax({
    url:'onlineUsers.json',
    success:function(responseText){

      $("#getOnlineUserCount").empty();
      $("#getOnlineUserCount").append(responseText);
    },
    error:function(){
      alert("canot get online userInfo");
    }
  });
}
