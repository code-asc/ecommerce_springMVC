
$(document).ready(function()
{
addImageToCarousel();

addThumbNailImageToHome();
});

//This is used to add images dynamically to carousel
var addImageToCarousel=function()
{
  var i=0;
  var data=$.xResponse("/Controller/authentication.cfc","homePageContent");
  var JSONdata=JSON.parse(data);
  $.each(JSONdata,function(key,val)
{
  $.each(val,function(key,val){
  // alert(val);
  $('<div class="item"><img src="'+val+'"></div>').appendTo('.carousel-inner');
   $('<li data-target="#slider" data-slide-to="'+i+'"></li>').appendTo('.carousel-indicators');
  i=i+1;

  })

})
$('.item').first().addClass('active');
$('.carousel-indicators > li').first().addClass('active');
$('#slider').carousel();
}



var addThumbNailImageToHome= function()
{
  var i=0;
  var brandID=0;
  var path=null;
  var subCategoryID=0;
  var data=$.xResponse("/Controller/authentication.cfc","homePageThumbNailInfo");
  var JSONdata=JSON.parse(data);
  $.each(JSONdata,function(index,item)
{
  $.each(item,function(key,val){
  if(key=='BRANDID')
  {
    brandID=val;
  }
  else if (key=="SUBCATEGORYID") {
subCategoryID=val;
  }
  else
  {
    path=val;
  }
  })
  $("#thumbNailRow").append('<div class="col-sm-4 col-md-4 col-xm-4 col-lg-4"><a href="./view/user_action.cfm?subCategoryID='+subCategoryID+'&checkBrand='+brandID+'"><div class="itemthumb"><img class="img-responsive" src="'+path+'"></div></a></div>');
});
}
