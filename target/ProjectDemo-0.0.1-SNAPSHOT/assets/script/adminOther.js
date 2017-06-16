$(document).ready(function() {
    $("#addBrand").click(function(event) {

            $("#formOtherData").empty();
            $("#formOtherData").css({"display":"block"});
        if ($("#formOtherBrand").valid()) {

            $.ajax({
                url: "addBrand.json",
                data: {
                    brandName: $("#brandName").val()
                },
            }).done(function(responseText,textStatus,jqXHR){
              if(responseText){
              $("#formOtherData").append("<div class='alert alert-success'>New Brand added to Database</div>").delay(4000).fadeOut();
            }
            else {
              $("#formOtherData").append("<div class='alert alert-success'>Something went wrong :( </div>").delay(4000).fadeOut();

            }
            brandList();
            }).fail(function(jqXHR,textStatus,errorThrown){
              $("#formOtherData").append("<div class='alert alert-success'>Something went wrong :( </div>").delay(4000).fadeOut();
            })
        }
    });

    $("#addCategory").click(function(event) {
      $("#formOtherData").empty();
      $("#formOtherData").css({"display":"block"});
        if ($("#formOtherCategory").valid()) {

            $.ajax({
                url: "addCategory.json",
                data: {
                    categoryType: $("#category").val()
                },
            }).done(function(responseText,textStatus,jqXHR){

              if(JSON.parse(responseText))
              {
              $("#formOtherData").append("<div class='alert alert-success'>New Category added to Database</div>").delay(4000).fadeOut();
            }
            else {
              $("#formOtherData").append("<div class='alert alert-success'>Something went wrong :( </div>").delay(4000).fadeOut();

            }
            categoryList();
            }).fail(function(jqXHR,textStatus,errorThrown){
              $("#formOtherData").append("<div class='alert alert-success'>Something went wrong :( </div>").delay(4000).fadeOut();
            })
        }
    })
});
