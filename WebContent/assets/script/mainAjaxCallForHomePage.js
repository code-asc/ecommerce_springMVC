$.extend({
  xResponse:function(urlComponent,componentMethodName){
    var returnVariable=null;
    $.ajax({
      url:urlComponent,
      data:{method:componentMethodName},
      async: false,
      success:function(responseText)
      {

        returnVariable=responseText;
      }
    });

    return returnVariable;
  }
});
