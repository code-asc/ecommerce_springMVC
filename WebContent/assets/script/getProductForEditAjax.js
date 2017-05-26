$(document).ready(function(){
  $("select[name='subcategory']").on("change",function(){
  //  alert($(this).val());
  var brandName;
  var productID;
  var productName;

  $("#products").find("option").remove();
  $("#products").append("<option selected disabled>Products</option>");
  $.ajax({url : 'getProductInfoBySubCategoryID.json',
data:{subCategoryID : $(this).val()},
success:function(responseText)
{
  $.each(responseText , function(index,key){
    $.each(key,function(index,value){
      if(index=="brandName")
      {
        brandName=value;
      }
      else if (index=="productID") {
        productID=value;
        //alert(value);
      }
      else if(index=="productName")
      {
        productName=value;
      }
    })
    $("#products").append("<option value="+productID+">"+"(Product ID :"+productID+")"+brandName+" "+productName+"</option>")
  })
}
}
)
  })
})
