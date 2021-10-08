<?php 
if(User::getUser()->hasAccess(Role::Register)) { ?>
<div id="newComment">
    <form name="newComment" method="POST" action="?formValidate=addPost" enctype="multipart/form-data">
        <div class="row">
            <span>
                <select name="switchUpload" onchange="WriteComment.switchUploadFunction()">
                    <option value="p_url">URL</option>
                    <option value="p_file">Immagine</option>
                </select>
            </span>
            <span><input type="file" name="p_file" /></span>
            <span><input type="url" name="p_url" placeholder="http://" /></span>
        </div>
        <div class="row"></div>
        <div class="row"><textarea name="text" placeholder="A cosa stai pensando?" onKeyPress="WriteComment.abilityHelpUser(event)" onkeyup="WriteComment.ricercaUtenti(event)"></textarea></div>
        <div class="help_input"></div>
        <div class="row"><input type="text" name="luogo" title="Luogo dove è stata scattata" placeholder="Dove sei?" pattern="/[^'\x22]+/" /> </div>
        <div class="row">
            <span class="finalRow">
                Privacy:
                <select name="privacy">
                    <option value="privato">Privato</option>
                    <option value="amici" selected="selected">Amici</option>
                    <option value="amiciplus">Amici +</option>
                    <option value="globale">Pubblico</option>
                </select>
            </span>
            <input type="button" name="invia" value="Invia" onclick="WriteComment.sendPost()" />
        </div>
    </form>
    
    <script>
        $("input[name='p_file']").hide();
    </script>
</div>

<?php } ?>


<div id="Showcase" >
    <div id="Showcase-div">
    </div>
    <div id="Showcase-other" onclick="News.showOther()">Altro..</div>
</div>
<script>
    var ascolto= false;
    var search ="";
    News.showOther();
</script>