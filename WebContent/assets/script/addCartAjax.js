$(document).ready(function()
{
  $("#onAddCart").click(function()
{

  $.ajax(
    {
    url:'/Controller/authentication.cfc?method=addToCart',
  }).done(function(responseText,textStatus,jqXHR){
     $("#infoAboutCart").html("<div class='alert alert-success'>Added To Cart</div>");
    //alert("works");
  //  console.log($("#traceCount").html());
   val=parseInt($("#traceCount").html())+1;
    $("#traceCount").html(val);})
    .fail(function(jqXHR,textStatus,errorThrown){
      alert("something went wrong :-( ");
    });

  $(this).attr("disabled",true).text("addedToCart");
});
});
