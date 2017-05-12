$(document).ready(function(){
  $("#submitUserEdit").click(function(event){

  $.ajax({
    url:"/Controller/userInfo.cfc?method=updateUserDetail",
    data:{firstName:$("input[name='firstName']").val(),
  middleName:$("input[name='middleName']").val(),
lastName:$("input[name='lastName']").val(),
email:$("input[name='email']").val(),
phone:$("input[name='mobile']").val()},
  }).done(function(responseText,textStatus,jqXHR){
    $("#showMessage").append("<div class='alert alert-success'>Changes Saved</div>").delay(4000).fadeOut();

  }).fail(function(jsXHR,textStatus,errorThrown){
    $("#showMessage").append("<div class='alert alert-success'>Something went wrong :-( )</div>").delay(4000).fadeOut();
  })
  })
})
