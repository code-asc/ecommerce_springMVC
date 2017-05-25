$(document).ready(function(){
brandList();
categoryList();
})

var brandList=function()
{
  $("#brandListTable").empty();
  $("#brandListTable").append("<tr><th>Available Brand</th></tr>");
  $.ajax({
    url:"getOnlyBrand.json",
  }).done(function(responseText,textStatus,jqXHR){
    $.each(responseText,function(key,value){
      $.each(value,function(name,val){
     if(name  == 'brandName')
      $("#brandListTable").append("<tr><td>"+val+"</td></tr>")
      })
    })


  }).fail(function(jqXHR,textStatus,errorThrown){
    $("#brandListTable").append("<tr><td>Could not able to retrive Brands</td></tr>");
  })
}

var categoryList=function()
{
  $("#categoryListTable").empty();
  $("#categoryListTable").append("<tr><th>Available Category</th></tr>");
  $.ajax({
    url:"getCategoryOnly.json",
  }).done(function(responseText,textStatus,jqXHR){
    $.each(responseText,function(key,value){
      $.each(value,function(name,val){
    	  if(name == 'categoryType')
      $("#categoryListTable").append("<tr><td>"+val+"</td></tr>")
      })
    })


  }).fail(function(jqXHR,textStatus,errorThrown){
      $("#categoryListTable").append("<tr><td>Could not able to retrive Category</td></tr>")
  })
}
