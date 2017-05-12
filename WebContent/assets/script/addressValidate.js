$(document).ready(function(){

  $("#city , #state , #country , #address , #pincode , #address2").keydown(function(event)
  {
    if(event.which==32)
    {
      return false;
    }
  });

$.validator.setDefaults({
  errorClass:"help-block",
  highlight:function(element)
  {
    $(element).closest(".form-group").addClass("has-error");
  },
  unhighlight:function(element)
  {
    $(element).closest(".form-group").removeClass("has-error");
  }
});

  $("#cf_form_address").validate({
    rules:{
      city:{
        required:true,
        lettersonly:true
      },
      state:{
        lettersonly:true,
        required:true
      },
      country:{
        lettersonly:true,
        required:true
      },
      address:{
        required:true,
      },
      pincode:{
        required:true,
        maxlength:6,
      }
    },
    messages:{
      city:{
        required:"This field cannot be empty"
      },
      state:{
        required:"This field cannot be empty"
      },
      country:{
        required:"This field cannot be empty"
      },
      address:{
        required:"This field cannot be empty"
      },
      pincode:{
        required:"This field cannot be empty",
      }
    }
  });
}
);
