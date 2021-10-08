function Showcase () {}

 

    Showcase.showOther =function () {
        numrow = $(".post").size();
        rangeLimit= 30;
        infLimit = numrow; 
        supLimit = numrow + rangeLimit;
         $.ajax({
          type: "POST",
          data: { "ajaxRequest" : "getShowcase" , "infLimit" : infLimit , "supLimit" : supLimit, "id" : $("#profile_id").html() },
          dataType: "html",
          success: function(risposta){
            old =  $("#Showcase-div").html();
            var oldNum= $(".post").size();
            $("#Showcase-div").html(old+risposta);
            if(oldNum == $(".post").size() )
                $("#Showcase-other").hide();
          }
        });
    };

    Showcase.zoomPhoto = function (event)
    {
        var image = event.target;
        var divParent = image.parentElement;
        if ($(divParent).hasClass("zoomed")) {
            $(divParent).removeClass("zoomed");
            $(image).removeClass("zoomed");
        }
        else {
            $(divParent).addClass("zoomed");
            $(image).addClass("zoomed");
        }
        resize();

    };

    Showcase.deletePost = function (button) {
        var form  =  $(button).parent();
        form.children("input[name='baseuri']").val(form[0].baseURI);
        form.submit();

    };
    
    Showcase.deleteComment = function(button) {
        var form = $(button).parent();
        form.children("input[name='baseuri']").val(form[0].baseURI);
        form.submit();
    };


    Showcase.sendComment = function( button ) {
        var form =  $(button).parent();
        var text = form.children("textarea");
        var idpost = form.children("input[name='postid']");
        form.children("input[name='baseurl']").val(form[0].baseURI);
        
        if(/['\x22]+/.test(text.val())) { 
            alert("Errore: commento non accettabile!"); 
            text.focus(); 
            return false; 
        }

        form.action = getHome() + "?formValidate=addComment";
        form.submit();
    };
    
    
    Showcase.addlikePost = function (bottone){
        var form =  $(bottone).parent();
        var id= form.children("input[name='postid']").val();
        var url = form[0].baseURI;
        $.ajax({
          type: "POST",
          data: { "ajaxRequest" : "addLikePost" , "postid" : id },
          dataType: "html",
          success: function(risposta){
            form.children("div.counter-like").html(risposta);
          }
        });
    };
            
    Showcase.addlikeComment = function (bottone){
        var form =  $(bottone).parent();
        var id= form.children("input[name='commentid']").val();
        var url = form[0].baseURI;
        $.ajax({
          type: "POST",
          data: { "ajaxRequest" : "addLikeComment" , "commentid" : id },
          dataType: "html",
          success: function(risposta){
            form.children("div.counter-like").html(risposta);
          }
        });   
        
    };
