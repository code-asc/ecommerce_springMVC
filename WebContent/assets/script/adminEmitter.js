
/*var source = new EventSource("emitterMapping.htm");
source.addEventListener('spring' , function(event){
	
	
} , false)

*/

var sendMessage = function()
	{
		$("#postedDetail").empty();
		$("#postedDetail").css({ "display" : "block" });
		var message = document.getElementById("updateForAll").value;
		if(message.trim().length > 0)
			{
			message = message.trim();
				$.ajax({
					url : "adminPost.json",
					data : {notification : message},
				}).done(function(responseText , textStatus , jqXHR){
					
					if(responseText)
						{
							getAllNotification();
							$("#postedDetail").append("<div class='alert alert-success'>Notification Posted....</div>").delay(5000).fadeOut();
						}
				})
			}
		else
			{
			$("#postedDetail").append("<div class='alert alert-warning'>Need to enter text....</div>").delay(5000).fadeOut();
			}
	}