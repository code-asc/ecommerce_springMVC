window.onbeforeunload = function (event) {
    var message = 'You are about to close the browser window';

    if (typeof event == 'undefined') {
        event = window.event;
    }
    if (event || window.event.clientX<0 || window.event.clientY<0) {
      $.ajax({
        url:"/Controller/removeSession.cfc?method=onWindowClose",
      })
    }

};

$(function () {
    $("a, button, input[type='submit'], input[type='button']").click(function () {
        window.onbeforeunload = null;
    });
});
