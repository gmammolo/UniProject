<?php 
    $id = filter_input(INPUT_GET, 'id');
    $utente = User::getUser();
    if(!is_null($id))
       $utente = User::getUserByID($id);
    if(is_null($utente))
    {
        Utility::RedMessage("Utente non disponibile");
        header("location: " . _INDEX_URL_ . "?page=home"  );
        die();
    }

?>

<div id="profile">  
    <?php
    if(User::getUser()->getId() !== $utente->getId() && User::checkAccessLevel(Role::Moderator) )
    {
        echo '<div class="retry" onclick ="window.location.href=\''. _INDEX_URL_ .'?page=admin\'"><img src="Template/images/arrow%202.png" alt=" "> Torna Indietro</div>';
    }
    ?>
    <div class="tab-profile">
    <img class="avatar" src="<?php echo $utente->getProfile()->getAvatar(); ?>" alt="photo">
    <div class="generalita">
        <div class="label-field  name"> <?php echo $utente->getProfile()->getNome(); ?></div>
        <div class="label-field"><span class="username">@<?php echo $utente->getUsername(); ?></span><span id="gender" class="gender"></span> </div>
    </div>
    <?php if($utente->getId() == User::getUser()->getId() ||  Friendship::IsFriend($utente) || User::hasAccess(Role::Moderator)) { ?>
    <div class="label-field  email down-avatar"><div class="label-info">email:</div> <?php echo $utente->getProfile()->getEmail(); ?></div>
    <div class="label-field  residenza down-avatar"><div class="label-info">residente:</div> <?php echo $utente->getProfile()->getResidenza(); ?></div>
        <div class="label-field  data down-avatar"> <div class="label-info">Data di Nascita:</div> <?php echo $utente->getProfile()->getData(); ?></div>
    </div>
    <?php  if(User::getUser()->getId() == $utente->getId() || User::checkAccessLevel(Role::Moderator) ) { ?>
        <input name="buttom" type="button" value="Modifica" onclick="ProfileClass.addForm(event)"/>
    <?php }
    } ?>
        
</div>
<?php 
 if(User::getUser()->getId() != $utente->getId() &&  Relationship::getRelationship(User::getUser()->getId(), $utente->getId())==null  )  { ?>
    <div id="id" style="display:none"><?php echo $utente->getId(); ?></div>
    <div><input name="addFriend" type="button" value="Aggiungi Amico" onclick="ProfileClass.addFriend(this)"/></div>
<?php  } ?>
<script>
  //get gender
  var gender = "<?php echo $utente->getProfile()->getGeneralita(); ?>";
  if(gender === "uomo")
        $("#gender").html("<img src=\"<?php echo _HOME_URL_ ; ?>Template/images/man.jpg\" ALT='sesso' />");
  else if(gender === "donna")
        $("#gender").html("<img src=\"<?php echo _HOME_URL_ ; ?>Template/images/woman.jpg\" ALT='sesso'/>");
  else
        $("#gender").html("<img src=\"<?php echo _HOME_URL_ ; ?>Template/images/man.jpg\"  ALT='sesso'/>");
</script>

<div id="profile_id" style="display: none;"><?php echo $id; ?></div>
<div id="Showcase" >
    <div id="Showcase-div">
    </div>
    <div id="Showcase-other" onclick="Showcase.showOther()">Altro..</div>
</div>
<script>
    var ascolto= false;
    var search ="";
    Showcase.showOther();
</script>



