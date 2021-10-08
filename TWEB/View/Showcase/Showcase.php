<?php
    
    foreach($postList as $singlePost ) { ?>
        <div class="post">
            <div class="Author">Postato da : <img class="Avatar" src="<?php echo $singlePost->getAuthor()->getProfile()->getAvatar();  ?>" alt="" ><span class="AuthorName"><a href="?page=profile&AMP;id=<?php echo $singlePost->getAuthor()->getId(); ?>"><?php echo $singlePost->getAuthor()->getProfile()->getNome(); ?></a></span></div>
            <form name ="removePost" method="POST" action="?formValidate=deletePost">
                <div class="delete" onclick="Showcase.deletePost(this)"> X </div>
                <input type="hidden" name="baseuri" value="" />
                <input type="hidden" name="id" value="<?php echo $singlePost->getId(); ?>" />
            </form>
            <?php $image = $singlePost->getImage(); 
            if($image != "") { ?>
            <div class="Image" onclick="Showcase.zoomPhoto(event)"><img class="Image" src="<?php echo $image; ?>" alt=""/></div>
            <?php } ?>
            <div class="Testo"> 
                <?php
                    $text = $singlePost->getText();
                    foreach($singlePost->getHashtag() as $hash){
                       preg_match("/(?<=#).*$/", $hash, $out);
                       $str_hash=$out[0];
                       $text =  str_replace($hash, "<a href = '?page=hashtag&amp;hashtag=$str_hash'>".$hash."</a>", $text);
                    }
                    echo  $text;   
                ?>
            </div>
            <div class="row">
                <div class="like">
                    <div class="counter-like"><?php echo $singlePost->getLikeit() ?></div>
                    <input type="button" name="addLike" value="like!" onclick="Showcase.addlikePost(this)" />
                    <input type="hidden" name="postid" value="<?php echo $singlePost->getId(); ?>" />
                </div>
                <div class="luogo"><?php echo $singlePost->getLocate(); ?></div>
                <div class="data"><?php echo $singlePost->getDate(); ?></div>
                <div class="privacy">
                    <?php 
                        $pri = $singlePost->getPrivacy(); 
                        switch ($pri) {
                            case Privacy::privato :
                                echo '<img src="'. _HOME_URL_.'Template/images/private_fb.png" alt=" "/>';
                                break;
                            case Privacy::amici :
                                echo '<img src="'. _HOME_URL_.'Template/images/friend_fb2.png" alt=" "/>';
                                break;
                            case Privacy::amiciplus :
                                echo '<img src="'. _HOME_URL_.'Template/images/friend_fb2.png" alt=" "/>';
                                break;
                            case Privacy::globale :
                                echo '<img src="'. _HOME_URL_.'Template/images/global_fb.png" alt=" "/>';
                                break;
                        }
                    ?>
                </div>
            </div>
        </div>
        <div class="comments">
            <?php $comments = Comment::getCommentsByPostID($singlePost->getId());
            foreach ($comments as $comment) {  ?>
                <div class="comment">
                    <div>
                        <img class="Avatar" src="<?php echo $comment->getAuthor()->getProfile()->getAvatar();  ?>" alt="" >
                        <span class="Author"><?php echo $comment->getAuthor()->getProfile()->getNome(); ?>:</span>
                        <p><?php echo $comment->getText(); ?><p>
                    </div>
                    <?php if(User::getUser()==$comment->getAuthor() || User::getUser() == $singlePost->getAuthor()   || User::hasAccess(Role::Moderator) ) { ?>
                    <form name="deleteComment" method="POST" action="?formValidate=deleteComment">    
                        <div class="delete" onclick="Showcase.deleteComment(this)"> X </div>
                        <input type="hidden" name="baseuri" value="" />
                        <input type="hidden" name="id" value="<?php echo $comment->getId(); ?>" />
                    </form>  
                    <?php }   ?>
                    <div class="data"><?php echo $comment->getDate() ?> </div>
                    <div class="like">
                        <div class="counter-like"><?php echo $comment->getLikeit() ?></div>
                        <input type="button" name="addLike" value="like!" onclick="Showcase.addlikeComment(this)" />
                        <input type="hidden" name="commentid" value="<?php echo $comment->getId(); ?>" />
                    </div>
                </div>    
            <?php } ?>
            <form name="sendComment" method="POST" action="?formValidate=addComment">
               <textarea name="commentText"  value="" draggable="false" />
                <input type="button" name="Invia" value="Invia" onclick="Showcase.sendComment(this)" />
                <input type="hidden" name="postid" value="<?php echo $singlePost->getId(); ?>" />
               <input type="hidden" name="baseurl" value="" />
            </form>
        </div>

    <?php } ?>