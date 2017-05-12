$(document).ready(function(){
brandList();
categoryList();
})

var brandList=function()
{
  $("#brandListTable").empty();
  $("#brandListTable").append("<tr><th>Available Brand</th></tr>");
  $.ajax({
    url:"/Controller/retriveProduct.cfc?method=getAllBrand",
  }).done(function(responseText,textStatus,jqXHR){
    $.each(JSON.parse(responseText),function(key,value){
      $.each(value,function(name,val){
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
    url:"/Controller/retriveProduct.cfc?method=getAllCategory",
  }).done(function(responseText,textStatus,jqXHR){
    $.each(JSON.parse(responseText),function(key,value){
      $.each(value,function(name,val){
      $("#categoryListTable").append("<tr><td>"+val+"</td></tr>")
      })
    })


  }).fail(function(jqXHR,textStatus,errorThrown){
      $("#categoryListTable").append("<tr><td>Could not able to retrive Category</td></tr>")
  })
}
