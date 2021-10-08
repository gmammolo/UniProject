<?php
function advancedSearchUserList($userList) {
    echo '<div id="accountList">';
    for($i=0; $i< count($userList); $i++ ) {  ?>
        <div class="redirectElement" onclick=" window.location.href = '/SocialProject/index.php?page=profile&AMP;id=<?php echo $userList[$i]->getId(); ?>'">
            <img class="avatar" src="<?php echo $userList[$i]->getProfile()->getAvatar(); ?>" alt="photo">
            <div class="generalita">
                <div class="row"><div class="label-field  name"> <?php echo $userList[$i]->getProfile()->getNome(); ?></div></div>
                <div class="row"><div class="label-field"><div class="username">@<?php echo $userList[$i]->getUsername(); ?></div></div></div>
            </div>
        </div>
    <?php }
    echo '</div>';
}


function advancedSearchFriendList($userList) {
    echo '<div id="accountList">';
    for($i=0; $i< count($userList); $i++ ) {  ?>
        <div class="redirectElement"onclick=" window.location.href = '/SocialProject/index.php?page=profile&AMP;id=<?php echo $userList[$i]->getId(); ?>'">
            <img class="avatar" src="<?php echo $userList[$i]->getProfile()->getAvatar(); ?>" alt="photo">
            <div class="generalita">
                <div class="row"><div class="label-field  name"> <?php echo $userList[$i]->getProfile()->getNome(); ?></div><span> Email Profilo:</span> <div class="profile-email"><?php echo $userList[$i]->getProfile()->getEmail(); ?> </div> </div>
                <div class="row"><div class="label-field"><div class="username">@<?php echo $userList[$i]->getUsername(); ?></div><div id="gender" class="gender"></div> </div><span> Residenza:</span> <div class="user-email"><?php echo $userList[$i]->getProfile()->getResidenza() ; ?> </div></div>
            </div>
        </div>
    <?php }
    echo '</div>';
}
