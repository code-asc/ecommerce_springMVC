$(document).ready(function(){

  $("select[name='category']").on("change",function(){
    //alert($(this).val())
    var subCategoryID;
    var subCategoryType;
    $("#subcategory").find("option").remove();
    $("#subcategory").append("<option selected disabled>SubCategory</option>");
    $.ajax({url:'getSubCategoryBasedOnCategoryID.json',
      data:{categoryID : $(this).val()},
      success:function(responseText){
        //alert(responseText);
        $.each(responseText,function(index , key){
          $.each(key,function(index , value){
            if(index=="subCategoryType")
            {
              subCategoryType=value;
            }
            else if (index=="subCategoryID"){
              subCategoryID=value;
            }
          })
          $("#subcategory").append("<option value="+subCategoryID+">"+subCategoryType+"</option>")
        })
      }
    })
  })
})
