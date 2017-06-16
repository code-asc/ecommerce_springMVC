$(document).ready(function(){

  $(".increment").click(function(event){
    var detailID=$(this).attr("value");


    $.ajax({
      url:'incrementQuantity.json',
      data:{detailID:detailID },
    }).done(function(responseText,textStatus,jsXHR){
      $.each((responseText),function(index,val){
      $.each(val,function(index,key)
            {
              if(index=="quantity"){
              $("#"+detailID).html(key);
            }
    else if (index=="sum") {
           $("#totalPriceAll").text(key);
            }
    else if(index=="totalCount")
    {
      $("#traceCount").text(key);
    }
    else if(index == "totalPriceForEachProduct"){
      $("#"+detailID+"paraID").html("Total:Rs.<strong><span id="+detailID+"SingleProduct"+">"+key+"</span></strong>");

    }

            })
          })
        }).fail(function(jsXHR,textStatus,errorThrown){
        	alert("Something went wrong :-(")
        })


  });
})
