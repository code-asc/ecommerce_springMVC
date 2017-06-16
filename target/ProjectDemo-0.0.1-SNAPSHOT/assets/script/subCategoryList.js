$(document).ready(function(){
subCategoryList();
})

var subCategoryList=function()
{
  $("#subCategoryListTable").empty();
  $("#subCategoryListTable").append("<tr><th>Available SubCategory</th></tr>");
  $.ajax({
    url:"getSubCategoryOnly.json",
  }).done(function(responseText,textStatus,jqXHR){
    
    $.each(responseText , function(key,value){
      $.each(value,function(name,val){
    	
    	  if(name == "subCategoryType")
    	  $("#subCategoryListTable").append("<tr><td>"+val+"</td></tr>")
      })
    })


  }).fail(function(jqXHR,textStatus,errorThrown){
    $("#subCategoryListTable").append("<tr><td>Could not able to retrive SubCategory</td></tr>");
  })
}
