$(document).ready(function(){
  var brand=[];
  var i=0;
  $.ajax({
    url:"http://localhost:8081/ProjectDemo/getOnlybrands.json",
    data:{method:"getAllBrand"},
    success:function(responseText){
    	
    $.each(responseText,function(index,val)
  {
    $.each(val,function(index,value)
  {
    if(index=="brandName")
    brand[i]=value;
  })
  i=i+1;
  })

    }
  })
  $("#getSuggestion").autocomplete({source:brand,
	  open:function(){
		  $(".ui-autocomplete").css("z-index",99999999);
	  }
	  });
});
