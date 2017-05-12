$(document).ready(function(){

  $("#password , #email , #mobile , #firstName , #middleName , #lastName").keydown(function(event)
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
  highlight:function(element)
  {
    $(element).closest(".form-group").addClass("has-error");
  },
  unhighlight:function(element)
  {
    $(element).closest(".form-group").removeClass("has-error");
  }
});

  $("#cf_form").validate({
    rules:{
      firstName:{
        required:true,
        nowhitespace:true,
        lettersonly:true
      },
      middleName:{
        nowhitespace:true,
        lettersonly:true
      },
      lastName:{
        nowhitespace:true,
        lettersonly:true
      },
      email:{
        nowhitespace:true,
        required:true,
        email:true,

      },
    password:{
      required:true,
      minlength:5,
      nowhitespace:true
    },
      mobile:{
        required:true,
        phoneUS:true,
        nowhitespace:true
      }
    },
    messages:{
      firstName:{
        required:"Please enter FirstName"
      },
      email:{
        required:"Please enter an email address",
        email:"Please enter a valid email address"
      },
      password:{
        required:"Please enter the password",
        minlength:"Minimum length should be 5 characters"
      },
      mobile:{
       required:"Please enter mobile number",
      }
    }
  });
}
);
