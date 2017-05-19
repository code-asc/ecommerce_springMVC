$(document).ready(function(){
  $(".decrement").click(function(event){
    var detailID=$(this).attr("value");
    $.ajax({
      url:'decrementQuantity.json',
      data:{detailID:detailID},
    }).done(function(responseText,textStatus,jsXHR){

      var eachPrice=parseInt($("#"+detailID+"eachPrice").html());

      $.each(responseText,function(index,val){
        $.each(val,function(index,key)
      {
        if(index=="quantity"){
$("#"+detailID).html(key);
}
else if (index=="sum") {
$("#totalPriceAll").text(key);
}
else if(index=="totalCart")
{
$("#traceCount").text(key);
}
else if(index == "totalPriceForEachProduct"){
$("#"+detailID+"paraID").html("Total:Rs.<strong><span id="+detailID+"SingleProduct"+">"+key+"</span></strong>");

}
      })
    })
    })
  });
})
