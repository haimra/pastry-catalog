 /*
 TODO not working
 $(function() {
    $("#logoutLink").on("click",function(e) {
    // cancel the link itself
      e.preventDefault();
      var _csrf = {} ;
      _csrf.token = "token";
      var data = new Map();
      data.set("_csrf",_csrf);
      var template = "{{ _csrf.token }}";
      var form = Mustache.render(template, data);
      $.post(event.currentTarget.href,form,{});
    });
 }());*/