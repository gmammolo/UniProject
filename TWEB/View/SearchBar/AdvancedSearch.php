<div id="search_gump">
    <form name="advancedSearch" method="POST" action="?formValidate=advancedSearch">
        <div id="modsearch">
            <input type="text" name="bar_search" placeholder="Cerca.." pattern="[^\22']" title="Cerca" />
            <input type="button" name="advcerca" value="Cerca" onclick="AdvancedSearch.advSearch()"/> 
        </div>
        <fieldset  class="field_check">
            <legend>Campo:</legend>
            <div class="checkbox_field"><input type="radio" name="campo" value="utenti" checked="true" onclick="AdvancedSearch.userSearchOption()" />Utenti</div>
            <div class="checkbox_field"><input type="radio" name="campo" value="amici" onclick="AdvancedSearch.friendSearchOption()" />Amici</div>
            <div class="checkbox_field"><input type="radio" name="campo" value="post" onclick="AdvancedSearch.postSearchOption()"/>Post</div>
        </fieldset>
        <fieldset  class="field_filter">
            <legend>Filtra:</legend>
            <div class="checkbox_field"><input type="radio" name="order" value="normal" checked="true"/>Nessuno</div>
            <div class="checkbox_field"><input type="radio" name="order" value="nome"/>Nome</div>
            <div class="checkbox_field"><input type="radio" name="order" value="username"/>Username</div>
        </fieldset>
    </form>
    
</div>

<div id="search_result">
    
</div>