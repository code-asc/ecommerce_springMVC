$(document).ready(function(){
  $("select[name='subcategory']").on("change",function(){
  //  alert($(this).val());
  var brandName;
  var photoID;
  var productName;
  var productID;

  $("#products").find("option").remove();
  $("#products").append("<option selected disabled>Products</option>");
  $.ajax({url:'/Controller/adminData.cfc?method=getProduct',
data:{subCategoryID:$(this).val()},
success:function(responseText)
{
  $.each(JSON.parse(responseText),function(index,key){
    $.each(key,function(index,value){
      if(index=="BRANDNAME")
      {
        brandName=value;
      }
      else if (index=="PHOTOID") {
        photoID=value;
      }
      else if (index=="PRODUCTID") {
        productID=value;
      }
      else if(index=="PRODUCTNAME")
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
