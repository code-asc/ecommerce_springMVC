$(document).ready(function()
{
  $("input[type='checkbox']").change(function(event)
  {
    $("#filterTarget").empty();
  var discountArr=[];
  var brandArr=[];
  var brandName;
  var productName;
  var unitPrice;
  var discount;
  var productDesc;
  var productID;
  var thumbNailPhoto;
$.each($("input[name='checkDiscount']:checked"),function(index,val){
  discountArr[index]=$(this).val();

})

$.each($("input[name='checkBrand']:checked"),function(index,val)
{
  brandArr[index]=$(this).val();
})


$.ajax({
  url:"filterProducts.json",
  data:{brandID:JSON.stringify(brandArr) , discount:JSON.stringify(discountArr)},
}).done(function(responseText,textStatus,jsXHR){

if(responseText.length)
  $.each(responseText,function(index, val)
{
  $.each(val,function(index,value)
{
  if(index=="brandName")
  {
    brandName=value;
  }
  else if(index=="productName")
  {
    productName=value;
  }
else if(index=="unitPrice")
  {
    unitPrice=value;
  }
  else if (index=="discount") {
    discount=value;
  }
  else if(index=="productDesc")
  {
    productDesc=value;
  }
  else if (index=="productID") {
    productID="user_action_single.html?productID="+value;
  }
  else if (index=="thumbNailPhoto")
  {
    thumbNailPhoto=value;
  }
})

if(discount>0)
{
$("#filterTarget").append('<div class="col-sm-3 col-md-3 col-xm-3 col-lg-3" style="float : left ; margin-bottom:30px" >'+'<a href='+productID+'><div class="itemthumb"><img src='+thumbNailPhoto+' class="img-responsive"></div></a><br/><strong>'+brandName+'</strong><p><strike>Rs.'+unitPrice+'</strike><strong>&nbsp Rs.'+(unitPrice-(unitPrice*(discount/100)))+'<h5>('+discount+'% <i>Off</i>)</h5><span class="label label-info">'+productName+'</span>');
}
else {
$("#filterTarget").append('<div class="col-sm-3 col-md-3 col-xm-3 col-lg-3" style="float : left ; margin-bottom:30px" >'+'<a href='+productID+'><div class="itemthumb"><img src='+thumbNailPhoto+' class="img-responsive"></div></a><br/><strong>'+brandName+'</strong><p>Rs.'+unitPrice+'<br/><span class="label label-info">'+productName+'</span>');

}
});

else {
  $("#filterTarget").append('<div class="col-sm-12 col-md-12 col-xm-12 col-lg-10"><div class="row"><img src="/ProjectDemo/assets/images/notFound.png" class="img-responsive" style="position:absolute ; margin:auto ; left:0 ; right:0"></div><div class="row text-center" style="margin-top:80px"><h3>We couldnt find the product</h3><h3>Something went wrong :-( </h3></div></div>');
}

}).fail(function(jsXHR,textStatus,errorThrown){
  $("#filterTarget").append('<div class="col-sm-3 col-md-3 col-xm-3 col-lg-3" style="float : left ; margin-bottom:30px" ><h2>Something went wrong :-(</h2><p>Please try later</p></div>');

})
});
});
