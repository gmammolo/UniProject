<?php
$user = User::getUser(); ?>
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
                echo("@import \"$css\";");
            }
        echo '</style>';
        
        ?>
    </head>
    <body>
        <aside class="skel-layers-fixed">
            <div class="top">
                <div id="logo">
                    <span id="avatar"><img src="<?php echo $user->getProfile()->getAvatar();  ?>" alt="avatar" /></span>
                    <div>
                        <h1 class="name"><?php echo $user->getProfile()->getNome(); ?></h1>
                        <p class="username">@<?php echo $user->getUsername(); ?></p>
                    </div>
                </div>
                <div id="search">
                    <input id="bar_search" type="search" name="search" pattern="[^'\x22]+" placeholder="Cerca" value="" onkeyup="Search.searchRequest(event)"/>
                    <input id="button_search" type="button" value=" " onclick="Search.goAdvancedSearch()" />
                    <div id="search-result"></div>
                    <script>$("#search-result").hide();</script>
                </div>
                <nav> 
                    <?php echo "<ul>";
                    foreach (MenageTemplate::getMenu() as $key => $submenues) {
                            $chiave = $key;
                            if (is_a($submenues, "Menu") && !is_null($submenues->getHtml()) &&  User::hasAccess($submenues->getAccessLevel() )) {
                                $chiave = '<a href="'.$submenues->getHtml().'" target="_self">'.$submenues->getIcon(). $key.' </a> ';
                                echo "<li class = \"menutab t$key \">$chiave</li>" . PHP_EOL;   
                            }
                            else if (is_array($submenues)) :
                                $icon = "";
                                if(count($submenues) > 0 && is_string($submenues[0])  )
                                    $icon ='<img src="'.array_shift ($submenues).'"  alt=" " />';
                                echo "<li class = \"menutab-no-cursor \">".$icon.$key."</li>" . PHP_EOL;  
                                echo "<li class = \"no-visible\"><ol>" . PHP_EOL;
                                foreach ($submenues as $alias => $submen) {
                                    if(User::hasAccess($submen->getAccessLevel()))
                                        echo "<li class = \"submenutab hidden $key t$alias\"><a href=\"".$submen->getHtml()."\" > ".$submen->getIcon().$submen->getName()."</a></li>".PHP_EOL;
                                }
                                echo"</ol></li>" . PHP_EOL;
                            endif;
                        
                    }
                    echo("</ul>");  ?>
                    <script>
                        var click = function(event){ window.location.href = $(this)[0].childNodes[0].href;};
                        $(".menutab").click(click);
                        $(".submenutab").click(click);
                        $(".menutab").mouseover(function(event){ event.stopPropagation(); $(this).addClass("empathize");   });
                        $(".menutab-no-cursor").mouseover(function(event){ event.stopPropagation(); $(this).addClass("empathize");   });
                        $(".submenutab").mouseover(function(event){ event.stopPropagation(); $(this).addClass("empathize");   });
                        $(".menutab").mouseout(function(event){ event.stopPropagation(); $(this).removeClass("empathize");   });
                        $(".submenutab").mouseout(function(event){ event.stopPropagation(); $(this).removeClass("empathize");   });
                        $(".menutab-no-cursor").mouseout(function(event){ event.stopPropagation(); $(this).removeClass("empathize");   });
                        $(".menutab-no-cursor").click(function(event){ 
                          var classe = $(this)[0].lastChild.data;       
                          if( $("."+classe).hasClass("hidden"))
                            $("."+classe).removeClass("hidden");
                          else
                            $("."+classe).addClass("hidden"); 
                        });
                    </script>
                </nav>

            </div>

        </aside>

        <!-- Main -->
        <div id="container">
            <header>
                <div id="title"><h1>Social Project</h1></div>
            </header>
            <main>
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
                
            </main>
            <script> 
                $(function(){
                    var $window = $(window).on('resize', function(){
                       var document_height = $( document ).height();
                       $("#container").height(document_height);
                       $("aside").height(document_height );
                       $("footer").css("top", document_height-15);
                    }).trigger('resize'); //on page load

                });
                $(".tFriends").append('<div id="notify_friend" class="notify_counter"></div>');
                $(".tFriends").ready(function() {
                    setInterval("Notify.updateFriendRequest()",15000);
                });
                Notify.updateFriendRequest();
                $(".tNews").append('<div id="notify_news" class="notify_counter"></div>');
                $(".tNews").ready(function() {
                    setInterval("Notify.updateNews()",15000);
                });
                Notify.updateNews();
                <?php ?>
                    $(".tAmministrazione").append('<div  id="notify_amm" class="notify_counter"></div>');
                    $(".tAmministrazione").ready(function() {
                        setInterval("Notify.updateRequest()",15000);
                    });
                    Notify.updateRequest();
                <?php ?>    
                    

 
            </script>
        </div>
       
        <footer>

            <!-- Copyright -->
            <div class="copyright">
                <p>Copyright © 2015 Mammolo Giuseppe. Tutti i diritti riservati.</p>
            </div>

        </footer>
    </body>
</html>
