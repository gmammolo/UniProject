function Hashtag() {}

    Hashtag.showOther =function () {
        numrow = $(".post").size();
        rangeLimit= 30;
        infLimit = numrow; 
        supLimit = numrow + rangeLimit;
        $.ajax({
            type: "POST",
            data: { "ajaxRequest" : "getHashPost" , "hashtag": $("#hashtag").html() , "infLimit" : infLimit , "supLimit" : supLimit },
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