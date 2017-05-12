$(document).ready(function()
{
  $("input[name='checkDiscount']").on("change",function()
{
  //alert("wow");
  //$(this).parent().sibling("input[name='checkDiscount']").removeAttr("checked");
 $("input[name='checkDiscount']").not(this).attr("checked",false);

});
});
