<?php
$hashtag = filter_input(INPUT_GET, "hashtag");
if(isset($hashtag) && $hashtag != "") { ?>
    <div id="hashtag" style="display:none;"><?php echo "#".$hashtag; ?></div>
    <div id="Showcase" >
        <div id="Showcase-div">
        </div>
        <div id="Showcase-other" onclick="Hashtag.showOther()">Altro..</div>
    </div>
    <script>
        var ascolto= false;
        var search ="";
        Hashtag.showOther();
    </script>
<?php } ?>