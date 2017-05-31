var source = new EventSource("emitterMapping.htm");
source.addEventListener('spring' , function(event){
	
$("#addNotification").empty();
	
	$.ajax({
		url : "getNotification.json"
	}).done(function(responseText , textStatus , jqXHR){
		var content;
		var postTime;
		var totalRead;;
		
		if(responseText.length > 0){
			
		totalRead = responseText[0]["count"];
		if(totalRead)
			{
				$("#notify").remove();
				$("#dLabel").append('<span class="badge badge-notify" id="notify">new</span>');
			}
		else
			{
				$("#notify").remove();
			}
		$.each(responseText , function(index , value){
			$.each(value , function(key , val){
				if(key == 'content')
					{
						content = val;
					}
				else if(key == 'postTime')
					{
						postTime = val;
					}
				else if(key == 'count')
					{
						totalRead = val;
					}
			})
			
			$("#addNotification").append('<a class="content" href="##"><div class="notification-item">'+
					'<h4 class="item-title"><span><i class="fa fa-calendar" aria-hidden="true"></i></span>'+
					postTime+'</h4><p class="item-info">'+
					content+'</p></div></a>');
		})
		}else
			{
			$("#addNotification").append('<a class="content" href="##">  <div class="notification-item">'+
                    '<h4 class="item-title"></h4>'+
                    '<p class="item-info">No notifications</p>'+
                '</div></a>');
			}
	})
	
} , false)
