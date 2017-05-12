$(document).ready(function(){
subCategoryList();
})

var subCategoryList=function()
{
  $("#subCategoryListTable").empty();
  $("#subCategoryListTable").append("<tr><th>Available SubCategory</th></tr>");
  $.ajax({
    url:"/Controller/retriveProduct.cfc?method=getAllSubCategory",
  }).done(function(responseText,textStatus,jqXHR){
    
    $.each(JSON.parse(responseText),function(key,value){
      $.each(value,function(name,val){
      $("#subCategoryListTable").append("<tr><td>"+val+"</td></tr>")
      })
    })


  }).fail(function(jqXHR,textStatus,errorThrown){
    $("#subCategoryListTable").append("<tr><td>Could not able to retrive SubCategory</td></tr>");
  })
}
