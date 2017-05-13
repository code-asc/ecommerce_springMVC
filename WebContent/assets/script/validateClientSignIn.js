$(document).ready(function(){

  $("#password , #email").keydown(function(event)
  {
    if(event.which==32)
    {
      return false;
    }
  });

  $.validator.setDefaults({
    errorClass:"help-block",
    errorElement:"span",
    errorPlacement: function(error, element) {
       if(element.parent('.input-group').length) {
           error.insertAfter(element.parent());
       } else {
           error.insertAfter(element);
       }
   },
    highlight:function(element){
      $(element).closest(".form-group").addClass("has-error");
    },
    unhighlight:function(element)
    {
      $(element).closest(".form-group").removeClass("has-error");
    }
  });
  $("#form_signin").validate({
    rules:{
      email:{
        required:true,
        email:true
      },
      password:{
        required:true
      }
    },
    messages:{
      email:{
        required:"Please enter an email address",
        email:"Please enter a valid email address"
      },
      password:{
        required:"Please enter the password"
      }
    }
  });
});
