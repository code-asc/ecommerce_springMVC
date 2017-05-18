$(document).ready(function()
{
  $("#onAddCart").click(function()
{

  $.ajax(
    {
    url:'addToCart.json',
  }).done(function(responseText,textStatus,jqXHR){
     $("#infoAboutCart").html("<div class='alert alert-success'>Added To Cart</div>").delay(4000).fadeOut();
     $("#onAddCart").attr("disabled",true).text("addedToCart");
//   val=parseInt($("#traceCount").html())+1;
  //  $("#traceCount").html(val);
     }
     )
    .fail(function(jqXHR,textStatus,errorThrown){
      alert("something went wrong :-( ");
    });

});
});
