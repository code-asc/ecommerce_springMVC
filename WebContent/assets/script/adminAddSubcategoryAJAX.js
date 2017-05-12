$(document).ready(function() {
    $("#addSubCategory").click(function(event) {

            $("#formDataStatusShow").empty();
            $("#formDataStatusShow").css({"display":"block"});
        if ($("#formAddSubCategory").valid()) {
            $.ajax({
                url: "/Controller/adminData.cfc?method=addSubCategoryToDatabase",
                data: {
                    categoryID: $("#category").val(),
                    subCategory:$("#subcategory").val(),
                },
            }).done(function(responseText,textStatus,jqXHR){
              if(JSON.parse(responseText))
              {
              $("#formDataStatusShow").append("<div class='alert alert-success'>New SubCategory added to Database</div>").delay(4000).fadeOut();
            }
            else {
              $("#formDataStatusShow").append("<div class='alert alert-success'>Something went wrong :( </div>").delay(4000).fadeOut();

            }
            subCategoryList();
            }).fail(function(jqXHR,textStatus,errorThrown){
              $("#formDataStatusShow").append("<div class='alert alert-success'>Something went wrong :( </div>").delay(4000).fadeOut();
            })
        }
    });

  })
