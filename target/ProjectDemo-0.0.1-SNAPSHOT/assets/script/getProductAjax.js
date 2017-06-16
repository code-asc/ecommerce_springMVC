$(document).ready(function(){
  $("select[name='subcategory']").on("change",function(){
  //  alert($(this).val());
  var brandName;
  var photoID;
  var productName;
  var productID;

  $("#products").find("option").remove();
  $("#products").append("<option selected disabled>Products</option>");
  $.ajax({url : 'getProductInfoBySubCategoryID.json',
data:{subCategoryID : $(this).val()},
success:function(responseText)
{
  $.each(responseText , function(index,key){
    $.each(key,function(index,value){
      if(index == "brandName")
      {
        brandName=value;
      }
      else if (index == "photoID") {
        photoID=value;
      }
      else if (index == "productID") {
        productID=value;
      }
      else if(index == "productName")
      {
        productName=value;
      }
    })
    $("#products").append("<option value="+productID+">"+brandName+" "+productName+"</option>")
  })
}
}
)
  })
})
