$(document).ready(function(){
	getMenuList(2 , 3 , 'menMenuList');
	getMenuList(4,5,'womenMenuList');
	getMenuListforOnlyOneType(1,'electronicMenuList');
})

function getMenuList(category_1 , category_2 , divID)
{
	var subCategoryID;
	var categoryType;
	var subCategoryType;
	var temp="";
	$("#"+divID).empty();
	$.ajax({url:"getCategoryType.json",
		data:{categoryID_1:category_1 , categoryID_2:category_2},
		}).done(function(responseText,textStatus,jsXHR){
			
			$.each(responseText , function(key , value){
				$.each(value , function(index , result){
					if(index=='categoryType')
						{
						categoryType=result;
						if(categoryType!=temp)
							{
							temp=categoryType;
							}
						else
							{
							categoryType="";
							}
						}
					else if(index=='subCategoryID')
						{
						subCategoryID=result;
						}
					else if(index=='subCategoryType')
						{
						subCategoryType=result;
						}
				})
				
				$("#"+divID).append('<div class="row"><div class="col-md-6 col-sm-6 col-xm-6 col-lg-6">'+
				'<h4>'+categoryType+'</h4>'+
				'<a href="user_action.html?subCategoryType='+subCategoryType+'&subCategoryID='+subCategoryID+'">'+subCategoryType+'</a><br/></div></div>');
				
			})
		})
}

function getMenuListforOnlyOneType(category_1 , divID)
{
	var subCategoryID;
	var categoryType;
	var subCategoryType;
	var temp="";
	$("#"+divID).empty();
	$.ajax({url:"getCategoryOfOnlyOneType.json",
		data:{categoryID_1:category_1},
		}).done(function(responseText,textStatus,jsXHR){
			
			$.each(responseText , function(key , value){
				$.each(value , function(index , result){
					if(index=='categoryType')
						{
						categoryType=result;
						if(categoryType!=temp)
							{
							temp=categoryType;
							}
						else
							{
							categoryType="";
							}
						}
					else if(index=='subCategoryID')
						{
						subCategoryID=result;
						}
					else if(index=='subCategoryType')
						{
						subCategoryType=result;
						}
				})
				
				$("#"+divID).append('<div class="row"><div class="col-md-6 col-sm-6 col-xm-6 col-lg-6">'+
				'<h4>'+categoryType+'</h4>'+
				'<a href="user_action.html?subCategoryType='+subCategoryType+'&subCategoryID='+subCategoryID+'">'+subCategoryType+'</a><br/></div></div>');
				
			})
		})
}