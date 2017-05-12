$(document).ready(function(){

  $("select[name='category']").on("change",function(){
    //alert($(this).val())
    var subCategoryID;
    var subCategoryType;
    $("#subcategory").find("option").remove();
    $("#subcategory").append("<option selected disabled>SubCategory</option>");
    $.ajax({url:'/Controller/adminData.cfc?method=getSubCategory',
      data:{categoryID:$(this).val()},
      success:function(responseText){
        //alert(responseText);
        $.each(JSON.parse(responseText),function(index,key){
          $.each(key,function(index,value){
            if(index=="SUBCATEGORYTYPE")
            {
              subCategoryType=value;
            }
            else if (index=="SUBCATEGORYID"){
              subCategoryID=value;
            }
          })
          $("#subcategory").append("<option value="+subCategoryID+">"+subCategoryType+"</option>")
        })
      }
    })
  })
})
