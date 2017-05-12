$(document).ready(function(){

  $(".increment").click(function(event){
    var detailID=$(this).attr("value");


    $.ajax({
      url:'/Controller/authentication.cfc?method=incrementQuantity',
      data:{id:JSON.stringify(detailID) },
    }).done(function(responseText,textStatus,jsXHR){
      $.each(JSON.parse(responseText),function(index,val){
      $.each(val,function(index,key)
            {
              if(index=="QUANTITY"){
              $("#"+detailID).html(key);
            }
    else if (index=="SUM") {
           $("#totalPriceAll").text(key);
            }
    else if(index=="TOTALCART")
    {
      $("#traceCount").text(key);
    }
    else {
      $("#"+detailID+"paraID").html("Total:Rs.<strong><span id="+detailID+"SingleProduct"+">"+key+"</span></strong>");

    }

            })
          })
        }).fail(function(jsXHR,textStatus,errorThrown){
          console.log(jsXHR.responseText);
        })


  });
})
