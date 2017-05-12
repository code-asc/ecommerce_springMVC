$(document).ready(function(){
  $(".nav li").click(function(event){
    $(".nav li").removeClass("active");

    if(!$(this).hasClass("active"))
    {
      $(this).addClass("active");
    }
  })
})
