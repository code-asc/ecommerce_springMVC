$(document).ready(function(){
  $("#postForAll").click(function(event){
postMessage();
  })
})

function postMessage()
{
  adminSidePost.authenticate("admin","admin");
  adminSidePost.publish("serverUpdateInfo",$("#updateForAll").val().trim());
}
