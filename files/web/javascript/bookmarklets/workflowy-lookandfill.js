javascript: (function() {
  /* JQUERY LOAD DYNAMICALLY */
  var done = false;
  var v = "1.12.4";
  /* IF PAGE HAS NO JQUERY OR JQUERY VERSION LOW */
  if (window.jQuery == undefined || window.jQuery.fn.jQuery < v) {
    var script = document.createElement("script");
    /* script.src = "http://ajax.googleapis.com/ajax/libs/jquery/" + v + "/jquery.min.js"; */
    script.src = "https://ajax.googleapis.com/ajax/libs/jquery/" + v + "/jquery.min.js";
    script.onload = script.onReadyStateChange = function() {
      if (!done && (!this.readyState || this.readyState == "loaded" || this.readyState == "complete")) {
        done = true;
        init();
      }
    }; /* END OF DEFINITION OF SCRIPT ONLREADY FUNCTION */
    document.getElementsByTagName("head")[0].appendChild(script);
  } else {
    /* END OF JQUERY VERSION CHECK */
    init();
  }

  function init() {
    $('.content').each(function(i, j) {
      var html = $(j).html();
      console.log(i + " : " + html);
      if (html.indexOf("@RED") >= 0) {
        $(j).attr("style", "color:red;");
      } else if (html.indexOf("@BLUE") >= 0) {
        $(j).attr("style", "color:blue;");
      }
    });
  } /* END OF FUNCTION init */
})(); /* END OF BOOKMARKLET */
