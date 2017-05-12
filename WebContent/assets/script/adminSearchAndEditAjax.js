$(document).ready(function(){
  $("input[name='submitEditProduct']").click(function(event){
  
    if($("#editProductAdmin").valid())
    {
      submitEditProductAJAXCall();
    }
  })
})

function submitEditProductAJAXCall()
{
  $("#infoAboutEdit").empty();
  $("#infoAboutEdit").css({"display":"block"});


  var productID=$("#productID").val();
  var categoryID=$("#category").val();
  var subcategoryID=$("#subcategory").val();
  var unitPrice=$("#unitPrice").val();
  var stock=$("#stock").val();
  var discount=$("#discount").val();
  var thumbNail=$("#thumbNailText").val();
  var largePhoto=$("#largePhotoText").val();
  var productDesc=$("#productDesc").val();

  $.ajax({
    url:"/Controller/adminData.cfc?method=editProductRemote",
    data:{productID:productID,productDesc:productDesc,unitPrice:unitPrice,unitInStock:stock,discount:discount,thumbNailPhoto:thumbNail,largePhoto:largePhoto},
  }).done(function(responseText,textStatus,jsXHR){
    if(responseText==1){
    $("#infoAboutEdit").append("<div class='alert alert-success'>Change Saved</div>").delay(4000).fadeOut();
  }

    else {
      $("#infoAboutEdit").append("<div class='alert alert-success'>Something went wrong</div>");
    }
  }).fail(function(jsXHR,textStatus,errorThrown){
    $("#infoAboutEdit").append("<div class='alert alert-success'>Error</div>");
  });
}
