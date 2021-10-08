<?php
    $Amicizie = Relationship::getFriendsListWithSearch(User::getUser()->getId(), $search);
    $notFriend = Relationship::getNotFriendListWithSearch(User::getUser()->getId(), $search);
    $postList = Post::getPostByHashTag($search);
?>
<div class="search_Friend">
    <?php if(count($Amicizie) > 0) echo '<div class="row title">Amici</div>';
     foreach ( $Amicizie as $pg )  { ?>   
    <div class="row user-row cliccabile" onclick="Search.redirectUser(<?php echo $pg->getId(); ?>);">
        <img class="image" src="<?php  echo $pg->getProfile()->getAvatar(); ?>" alt=""/>
        <div class="multi_row">
            <div class="row nome"><?php  echo $pg->getProfile()->getNome(); ?></div>
            <div class="row username"><?php echo $pg->getUsername(); ?></div>
        </div>
    </div>
     <?php } ?>   
</div>
<div class="search_OtherUser">
    <?php if(count($notFriend) > 0) echo '<div class="row title">Altri</div>';
    foreach($notFriend as $pg) { ?>
        <div class="row user-row cliccabile" onclick="Search.redirectUser(<?php echo $pg->getId(); ?>);">
            <img class="image" src="<?php  echo $pg->getProfile()->getAvatar(); ?>" alt=""/>
            <div class="multi_row">
                <div class="row nome"><?php  echo $pg->getProfile()->getNome(); ?></div>
                <div class="row username"><?php echo $pg->getUsername(); ?></div>
            </div>
        </div>
    <?php } ?>
</div>
<div class="search_HashTag">
     <?php if(count($postList) > 0) echo '<div class="row title">Altri</div>';
     foreach($postList as $post) { ?>
    <div class="row post-row cliccabile" onclick="Search.goHashTag()">
            <img class="image" src="<?php echo $post->getAuthor()->getProfile()->getAvatar(); ?>" alt="" /><span class="name"><?php echo $post->getAuthor()->getProfile()->getNome(); ?></span>
            ha scritto: <span class="text">
            <?php 
                echo $text = substr( $post->getText(), 0, 20 );
                if(strlen($post->getText())>20) echo "...";
            ?></span>
        </div>
     <?php } ?>
</div>