    <?php
    $id = filter_input(INPUT_GET, 'id');
        if(is_null($id))
        $id = User::getUser ()->getId (); 
    $utente = User::getUserByID($id);
    if(is_null($utente))
    {
        Utility::RedMessage("Utente non disponibile");
        header("location: " . _INDEX_URL_ . "?page=home"  );
        die();
    }
    
    if(User::getUser()->getId() != $utente->getId() && !User::checkAccessLevel(Role::Moderator) )
    {
        Utility::RedMessage("Non hai i permessi per visualizzare questo utente");
        echo "<script>window.location.href=\"" . _INDEX_URL_ . "?page=home\"; </script>";
//        header("location: " . _HOME_URL_ . "?page=home"  );
        die();
    }
    
    ?>
<script>
    var profilo = new Profile("<?php echo $utente->getProfile()->getNome(); ?>", "<?php echo $utente->getProfile()->getAvatar(); ?>" ,"<?php echo $utente->getProfile()->getEmail(); ?>", "<?php echo $utente->getProfile()->getResidenza(); ?>", "<?php echo $utente->getProfile()->getData(); ?>" , "<?php echo $utente->getProfile()->getGeneralita(); ?>");
</script>

<div id="change-photo">
    <form name="change-photo-form" method="POST" action="?formValidate=FormChangeAvatar&amp;id=<?php echo $utente->getId() ?>" enctype="multipart/form-data">
        <div class="change-avatar-url"><input type="radio" name="choose" value="image_url"><input type="url" name="image_url" placeholder="http://"  onclick="ProfileClass.selectURL();" /></div>
        <div class="change-avatar-url"><input type="radio" name="choose" value="image_file"><input type="hidden" name="MAX_FILE_SIZE" value="100000" /><input type="file" name="image_file" onclick="ProfileClass.selectFILE();"/></div>
        <input type="button" value="Cambia" onclick="ProfileClass.sendPhotoRequest()" />
        <input type="button" value="Cancel" onclick="ProfileClass.closeFormAvatar()" />
    </form>
    
</div>
<div id="change-password" >
    <?php if($utente == User::getUser()) : ?>   
    <form name="change-password" method="POST" action="?formValidate=FormChangePwd&amp;id=<?php echo $utente->getId() ?>">
            <div class="label-field  pass down-avatar"> Cambiare  Password: <input type="password" name="oldPass" placeholder="Vecchia Password" value="" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{4,16}" title="Deve contenere almeno un carattere Maiuscolo, uno minuscolo e un numero. (4-16 caratteri)." /></div>
            <div class="label-field  newpass down-avatar"><input type="password" name="newPass" placeholder="Nuova Password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{4,16}" title="Deve contenere almeno un carattere Maiuscolo, uno minuscolo e un numero. (4-16 caratteri)."  /><input type="password" name="cNewPass" placeholder="Conferma Password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{4,16}" title="Deve contenere almeno un carattere Maiuscolo, uno minuscolo e un numero. (4-16 caratteri)."  /></div>
            <input type="button" value="Cambia" onclick="ProfileClass.sendPwdRequest()" />
            <input type="button" value="Cancel" onclick="ProfileClass.closeFormPwd()" />
        </form>
    <?php endif; ?>
</div>

<script>
    $("#change-photo").hide();
    $("#change-password").hide();
</script>

<form method="POST" name="mod-profile" class="mod-profile" action="?formValidate=FormProfile&amp;id=<?php echo $utente->getId() ?>" >
        <div class="tab-profile">
            <div class="mod-avatar" >
                <img class="avatar" src="<?php echo $utente->getProfile()->getAvatar(); ?>" alt="photo">
                <input type="button" name="avatar" value="cambia Avatar" onclick="ProfileClass.changeAvatar()" />
               
            </div>
            <div class="generalita">
                <div class="label-field  name"><input type="text" name="Username" value="<?php echo $utente->getProfile()->getNome(); ?>" placeholder="Username" pattern="[^'\x22]+" /></div>
                <div class="label-field  username"> @<?php echo $utente->getUsername(); ?></div>
                <div class="label-field  pwd"> <?php if($utente == User::getUser()) { ?> <input type="button" value="Cambia Password" name="change-password" onclick="ProfileClass.changePassword()"/> <?php } ?></div>
            </div>
            
            <div class="label-field  sesso down-avatar"><div class="label-info">sesso:</div> <select name="Gender"> <option value="nessuno">Nessuno</option> <option value="uomo">Uomo</option> <option value="donna">Donna</option> <option value="altro">Altro</option> </select> </div>
            <div class="label-field  email down-avatar"><div class="label-info">email:</div><input type="email" name="Email" placeholder="Email" value="<?php echo $utente->getProfile()->getEmail(); ?>" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"/></div>
            <div class="label-field  residenza down-avatar"><div class="label-info">residente:</div><input type="text" name="Residenza" placeholder="Residenza" value="<?php echo $utente->getProfile()->getResidenza();  ?>" pattern="[^'\x22]+"/></div>
                <div class="label-field  data down-avatar"> <div class="label-info">Data di Nascita:</div><input type="text" name="Data" placeholder="Data di Nascita" value="<?php echo $utente->getProfile()->getData(); ?>" pattern="(0000-00-00)|(((19|20)\d\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01]))$" title="Inserire la data nel formato aaaa-mm-gg (da 1900-01-01 a 2099-12-31)" /></div>
        </div>
        <input name="submit" type="submit" value="Invia" onclick="ProfileClass.sendForm(profilo)"/> <input name="retry" type="button" value="retry" onclick="window.location.href = '/SocialProject/index.php?page=profile&amp;id=<?php echo $utente->getI&d(); ?>'"/>
    </form>

