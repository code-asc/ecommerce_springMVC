$(document).ready(function(){
  var brand=[];
  var i=0;
  $.ajax({
    url:"/Controller/authentication.cfc",
    data:{method:"getAllBrand"},
    success:function(responseText){
  //  console.log(responseText);
    $.each(JSON.parse(responseText),function(index,val)
  {
    $.each(val,function(index,value)
  {
    if(index=="BRAND")
    brand[i]=value;
  })
  i=i+1;
  })

    }
  })
  $("#getSuggestion").autocomplete({source:brand});
});
