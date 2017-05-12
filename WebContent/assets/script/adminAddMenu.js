$(document).ready(function() {
    $("input[name='submitData']").click(function(event) {
        //  alert($("#thumbNailText").val());
        if ($("#formOption").valid())
            ajaxAddCall();
    });
})

function ajaxAddCall() {
    $("#formData").empty();
    $("#formData").css({"display":"block"});
    var productName = $("#productName").val();
    var brandID = $("#brand").val();
    var categoryID = $("#category").val();
    var shippingID = $("#shipping").val();
    var supplierID = $("#supplier").val();
    var subcategoryID = $("#subcategory").val();
    var price = $("#price").val();
    var quantity = $("#quantity").val();
    var discount = $("#discount").val();
    var rating = $("#rating").val();
    var thumbNail = $("#thumbNailText").val();
    var largePhoto = $("#largePhotoText").val();
    var productDesc = $("#productDesc").val();
    var thumbNailType = $("#thumbNailType").val();
    var largePhotoType = $("#largePhotoType").val();

    $.ajax({
        url: "/Controller/adminData.cfc?method=addToDatabase",
        data: {
            productName: productName,
            productDesc: productDesc,
            supplierID: supplierID,
            subCategoryID: subcategoryID,
            unitPrice: price,
            thumbNail: thumbNail,
            thumbNailType: thumbNailType,
            largePhotoType: largePhotoType,
            largePhoto: largePhoto,
            quantity: quantity,
            discount: discount,
            rating: rating,
            brandID: brandID
        },
    }).done(function(responseText,textStatus,jsXHR){
      if(JSON.parse(responseText)=="success")
      {
    //  alert(responseText);
      $("#formData").append("<div class='alert alert-success'>New Product added to Database</div>").delay(4000).fadeOut();
    }
      else {
        alert(responseText)
        $("#formData").append("<div class='alert alert-success'>Something went wrong :-(</div>").delay(4000).fadeOut();
      }
    }).fail(function(jsXHR,textStatus,errorThrown){
      $("#formData").append("<div class='alert alert-success'>Error</div>")

    });
}
