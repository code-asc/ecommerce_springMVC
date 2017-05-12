$(document).ready(function()
{
    $("input[type='checkbox']").change(function(event)
  {
    $("#filterTarget").empty();
  var discountArr=[];
  var brandArr=[];
  var categoryArr=[];
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

$.each($("input[name='checkCategory']:checked"),function(index,val)
{
  categoryArr[index]=$(this).val();
})

$.ajax({
  url:"/Controller/viewFilterCategory.cfc?method=filterProduct",
  data:{brand:JSON.stringify(brandArr) , discount:JSON.stringify(discountArr) ,category:JSON.stringify(categoryArr)},
}).done(function(responseText,textStatus,jqXHR){
  //alert(categoryArr[1]);
  if(JSON.parse(responseText).length)
  {//alert(responseText)
  $.each(JSON.parse(responseText),function(index, val)
{
  $.each(val,function(index,value)
{
  if(index=="BRANDNAME")
  {
    brandName=value;
  }
  else if(index=="PRODUCTNAME")
  {
    productName=value;
  }
else if(index=="UNITPRICE")
  {
    unitPrice=value;
  }
  else if (index=="DISCOUNT") {
    discount=value;
  }
  else if(index=="PRODUCTDESC")
  {
    productDesc=value;
  }
  else if (index=="PRODUCTID") {
    productID="user_action_single.cfm?productID="+value;
  }
  else
  {
    thumbNailPhoto=value;
  }
})

if(discount>0)
{
$("#filterTarget").append('<div class="col-sm-3 col-md-3 col-xm-3 col-lg-3" style="float : left ; margin-bottom:30px" >'+'<a href='+productID+'><img src='+thumbNailPhoto+' class="img-responsive"></a><br/><strong>'+brandName+'</strong><p><strike>Rs.'+unitPrice+'</strike><strong>&nbsp Rs.'+(unitPrice-(unitPrice*(discount/100)))+'<h5>('+discount+'% <i>Off</i>)</h5><span class="label label-info">'+productName+'</span>');
}
else {
$("#filterTarget").append('<div class="col-sm-3 col-md-3 col-xm-3 col-lg-3" style="float : left ; margin-bottom:30px">'+'<a href='+productID+'><img src='+thumbNailPhoto+' class="img-responsive"></a><br/><strong>'+brandName+'</strong><p>Rs.'+unitPrice+'<br/><span class="label label-info">'+productName+'</span>');

}

});
}
else {

  $("#filterTarget").append('<div class="col-sm-4 col-md-4 col-xm-4 col-lg-4" style="float : left ; margin-bottom:30px" ><div class="alert alert-info"><p>No such Product exists</p></div></div>');


}
})
});
});
