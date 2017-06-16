$(document).ready(function(){

$.validator.setDefaults({
  errorClass:"help-block",
  highlight:function(element){
    $(element).closest(".form-group").addClass("has-error");
  },
  unhighlight:function(element){
    $(element).closest(".form-group").removeClass("has-error");
  }
})

  $("#formOption , #formAddSubCategory ,#editProductAdmin , #formEdit ,#formDelete ,#formOtherBrand").validate({
    rules:{
      productName:{
        required:true,
      },
      brand:{
        required:true,
      },
      category:{
        required:true,
      },
      shipping:{
        required:true,
      },
      supplier:{
        required:true,
      },
      subcategory:{
        required:true,
      },
      price:{
        required:true,
        number:true,
        min:0,
      },
      unitPrice:{
        required:true,
        number:true,
        min:0,
      },
      stock:{
        required:true,
        number:true,
        min:0,
      },
      quantity:{
        required:true,
        number:true,
        min:0,
      },
      discount:{
        required:true,
        number:true,
        range:[0,100],
      },
      rating:{
        required:true,
        number:true,
        range:[0,5],
      },
      thumbNail:{
        required:true,
      },
      thumbNailType:{
        required:true,
      },
      largePhoto:{
        required:true,
      },
      largePhotoType:{
        required:true,
      },
      productDesc:{
        required:true,
      },
      products:{
        required:true,
      },
      brandName:{
        required:true,
      }
    },
    messages:{
      productName:{
        required:"Please fill this field",
      },
      brand:{
        required:"Please choose a option",
      },
      category:{
        required:"Please choose a option",
      },
      shipping:{
        required:"Please choose a option",
      },
      supplier:{
        required:"Please choose a option",
      },
      subcategory:{
        required:"Please choose a option",
      },
      price:{
        required:"Please enter the price",
        number:"Please enter a valid number",
      },
      quantity:{
        required:"Please enter the quantity",
        number:"Please enter a valid number",
      },
      discount:{
        required:"Please enter the discount",
        number:"Please enter a valid number",
      },
      rating:{
        required:"Please enter the rating",
        number:"Please enter a valid number",
        range:"Range must be between 0 to 5",
      },
      thumbNail:{
        required:"Please fill this field",
      },
      thumbNailType:{
        required:"Please fill this field",
      },
      largePhoto:{
        required:"Please fill this field",
      },
      largePhotoType:{
        required:"Please fill this field",
      },
      productDesc:{
        required:"Please fill this field",
      }
    }
  });


$("input[name='submitEdit']").click(function(event){
  return $("#formEdit").valid();
})

})
