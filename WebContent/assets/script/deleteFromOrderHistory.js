$(document).ready(function(){
  $(".deleteFromHistory").click(function(event){

deleteOnAJAXCall($(this).attr("id"))
  })
})

function deleteOnAJAXCall(orderID)
{
  alert(orderID);
  $.ajax({
    url:"/Controller/orderDetails.cfc?method=deleteOrderHistory",
    data:{orderID:orderID},
  }).done(function(responseText,textStatus,jqXHR){
    console.log(textStatus);
  }).fail(function(jqXHR,textStatus,errorThrown){
    alert("something went wrong :-( ");
  })
}
