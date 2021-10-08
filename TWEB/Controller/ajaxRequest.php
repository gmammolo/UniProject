<?php

if($ajaxRequest === "FormProfile" && User::hasAccess(Role::Register) )
{
    require_once _DIR_VIEW_ . 'PrivateArea/FormProfile.php';
    
}

else if($ajaxRequest == "search" && User::hasAccess(Role::Register)  ) {
    $search = filter_input(INPUT_POST, "search" );
    require_once _DIR_VIEW_ . 'SearchBar/search.php';
}

else if($ajaxRequest == "getAccountList" && User::hasAccess(Role::Register)  ) {
        $seach_string = filter_input(INPUT_POST, 'search_cerca_account') ; 
        if(preg_match("/['\x22]+/", $seach_string))
                die();
        require_once _DIR_VIEW_ . 'PrivateArea/ajaxAccountList.php';
        MenageTemplate::resize();
        die();
}
    
    
else if( $ajaxRequest == "getShowcase" && User::hasAccess(Role::Register) ) {
    $infLimit = filter_input(INPUT_POST, 'infLimit');
    $supLimit = filter_input(INPUT_POST, 'supLimit');
    $id = filter_input(INPUT_POST, 'id');
    if(!isset($id) || !is_numeric($id))
        $id = User::getUser ()->getId ();
    $postList = Showcase::getShowcasePost($id,$infLimit, $supLimit ); 
    require_once _DIR_VIEW_ .  'Showcase/Showcase.php';
    MenageTemplate::resize();
    die();
}
    
else if( $ajaxRequest == "getFriendNews" && User::hasAccess(Role::Register) ) {
    $infLimit = filter_input(INPUT_POST, 'infLimit');
    $supLimit = filter_input(INPUT_POST, 'supLimit');
    $postList = Post::getNewPost($infLimit, $supLimit ); 
    require_once _DIR_VIEW_ .  'Showcase/Showcase.php';
    MenageTemplate::resize();
    die();
}

else if ( $ajaxRequest ==  "addLikePost" )
{
    $id = filter_input(INPUT_POST, 'postid');
    $post = Post::getPostByID($id);
    if(isset($post)) {
        $post->addLike();
    }
    echo $post->getLikeit();
    die();
}


else if ( $ajaxRequest ==  "addLikeComment" )
{
    $id = filter_input(INPUT_POST, 'commentid');
    $comment = Comment::getCommentByID($id);
    if(isset($comment)) {
        $comment->addLike();
    }
    echo $comment->getLikeit();
    die();
}

else if ( $ajaxRequest ==  "getFriends" && User::hasAccess(Role::Register)) 
{
    $inf = filter_input(INPUT_POST, "infLimit");
    $sup = filter_input(INPUT_POST, "supLimit");
    $friendRequestList = Relationship::getFriendshipRequest(User::getUser()->getId(), $inf, $sup); 
    $type = "rfriend";
    foreach($friendRequestList as $friendship) { 
        $pf = $friendship->getApplicant();
        require _DIR_VIEW_ . 'Friends/FriendTip.php';
    }
    $friendList = Friendship::getFriendsList(User::getUser(), $inf, $sup);
    $type = "ffriend";
    foreach($friendList as $friendship) { 
        $pf = $friendship->getFriend();
        require _DIR_VIEW_ . 'Friends/FriendTip.php';
    }
    die();
}


else if ( $ajaxRequest ==  "getPossibleFriends" && User::hasAccess(Role::Register)) 
{
        
        $possFriends = Relationship::getRandomNotRelated(User::getUser()->getId());
        $type = "pfriend";
        foreach($possFriends as $pf) {
            require _DIR_VIEW_ . 'Friends/FriendTip.php';
        }
        die();
        
}


else if( $ajaxRequest ==  "getHashPost" && User::hasAccess(Role::Register))  {
    $infLimit = filter_input(INPUT_POST, 'infLimit');
    $supLimit = filter_input(INPUT_POST, 'supLimit');
    $hashtag = filter_input(INPUT_POST, 'hashtag');
    if(preg_match('/#[A-Za-z0-9]*$/', $hashtag)) {
        $postList = Post::getHashtagPost($hashtag, $infLimit, $supLimit ); 
        require_once _DIR_VIEW_ .  'Showcase/Showcase.php';
        MenageTemplate::resize();
    }
    die();
}

else if( $ajaxRequest ==  "getUpdateNews" && User::hasAccess(Role::Register))  {
   $like = Notify::getNumNewLikeForUser(User::getUser()->getId()) ;
   $post = Notify::getNumNewPost(User::getUser()->getId());
   $comment= Notify::getNumNewComment(User::getUser()->getId());
   $num = $like+$post+$comment;
   if($num > 0) echo $num;
}

else if( $ajaxRequest ==  "getUpdateFriend" && User::hasAccess(Role::Register))  {
   $num = Notify::getNumFriendRequest(User::getUser()->getId());
   if($num > 0) echo $num;
}

else if( $ajaxRequest ==  "getUpdateAmm" && User::hasAccess(Role::Register))  {
    if(User::getUser()->hasAccess(Role::Administrator) ) {
        $num = Notify::getNumNewUser();
        if($num > 0) echo $num;
        
    }   
    die();
}


else if( $ajaxRequest ==  "prolificPoster" && User::hasAccess(Role::Register))  { 
    $list = User::getMustPosters();
    echo json_encode($list);
    die();
}

else if( $ajaxRequest ==  "attivity" && User::hasAccess(Role::Register))  { 
    $list = User::getAttivity();
    echo json_encode($list);
    die();
}