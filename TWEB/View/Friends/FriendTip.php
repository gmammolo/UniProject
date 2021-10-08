<div class="friend <?php echo $type; ?>">
    <a href="?page=profile&AMP;id=<?php echo $pf->getId(); ?>">
        <div class="row image"><img src="<?php echo $pf->getProfile()->getAvatar(); ?>" alt="" /></div>
        <div class="row nome"><?php echo $pf->getProfile()->getNome(); ?></div>
    </a>
    <div class="row">
        <div class="username"><?php echo $pf->getUsername(); ?></div>
        <div class="sesso">
            <?php 
                $sesso = $pf->getProfile()->getGeneralita();
                switch ($sesso) {
                    case "uomo" :
                        echo "<img src=\"" . _HOME_URL_ ."Template/images/man.jpg\" ALT='sesso' />";
                        break;
                    case "donna":
                        echo "<img src=\"" . _HOME_URL_ ."Template/images/woman.jpg\" ALT='sesso'/>";
                    default:
                        echo "<img src=\"" . _HOME_URL_ ."Template/images/man.jpg\"  ALT='sesso'/>";
                }
            ?>
        </div>
    </div>
    <?php if($type == "pfriend") { ?>
        <div class="row buttonRequest pid<?php echo $pf->getId(); ?>"><input type="button" value="Invia Richiesta" onclick="FriendRequest.sendRequestFriend(<?php echo $pf->getId(); ?>)" /></div>
    <?php } else if($type == "rfriend") { ?>
        <div class="row buttonRequest pid<?php echo $pf->getId(); ?>"><input type="button" value="Accetta Richiesta" onclick="FriendRequest.acceptRequestFriend(<?php echo $pf->getId(); ?>)" /></div>
    <?php } ?>
</div>
