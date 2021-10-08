<!DOCTYPE HTML>
<html>
    <head>
        <title>Social Project</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <?php
        foreach (MenageTemplate::getJavascript() as $js) {
            echo "<script src=\"$js\"></script>". PHP_EOL;
        }
        echo '<style type="text/css">';
            
            foreach (MenageTemplate::getCss() as $css)
            {
                echo("@import \"$css\";".PHP_EOL);
            }
        echo '</style>';
        
        ?>
    </head>
    <body>
        <div id="main">
            <div id="message">
                <?php
                $redmessage = &Session::get('redMessage', 'array');
                foreach ($redmessage as $message) {
                    echo '<div class="message-field redmessage">'.$message.'</div>';
                }
                $redmessage = array();
                
                $yellowmessage = &Session::get('yellowMessage', 'array');
                foreach ($yellowmessage as $message) {
                    echo '<div class="message-field yellowmessage">'.$message.'</div>';
                }
                $yellowmessage = array();
                
                $greenmessage = &Session::get('greenMessage', 'array');
                foreach ($greenmessage as $message) {
                    echo '<div class="message-field greenmessage">'.$message.'</div>';
                }
                $greenmessage = array();
                
                ?>
            </div>
            
            <?php
            foreach (MenageTemplate::getContents() as $content)
            {
                echo '<div class="section">'.PHP_EOL;
                include $content;
                echo '</div>'.PHP_EOL;
            }

            ?>
        </div>
    </body>
</html>