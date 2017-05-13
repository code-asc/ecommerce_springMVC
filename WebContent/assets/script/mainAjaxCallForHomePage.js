$.extend({
  xResponse:function(urlComponent){
    var returnVariable=null;
    $.ajax({
      url:urlComponent,
    
      async: false,
      success:function(responseText)
      {

        returnVariable=responseText;
      }
    });

    return returnVariable;
  }
});
