var msgHandler=function(message)
{
  var dataSocket=message.data;
  var postTime;
  var content;
  if(dataSocket)
  {
    $(".notifications-wrapper").empty();
    $.ajax({
      url:"/Controller/adminData.cfc?method=notificationData",
      data:{content:dataSocket},

    }).done(function(responseText,textStatus,jsXHR){
      $.each(JSON.parse(responseText),function(index,key){
        $.each(key,function(index,value){
        if(index=="POSTTIME")
        {
          postTime=value;
        }
        else if (index=="CONTENT") {
          content=value;
        }
        })

$(".notifications-wrapper").append(' <a class="content" href="#"><div class="notification-item"><h4 class="item-title"><span><i class="fa fa-calendar" aria-hidden="true"></i></span>'+postTime+'</h4><p class="item-info">'+content+'</p></div></a>');
$("#notify").addClass("badge-notify");
$("#notify").text("new");
      })
    }).fail(function(jqXHR,textStatus,errorThrown){
      console.log(jqXHR.responseText);
    })
  }
}

var openHandler=function()
{

}
