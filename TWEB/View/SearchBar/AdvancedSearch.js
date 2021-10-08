/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function AdvancedSearch () {}

AdvancedSearch.advSearch = function() {
    if(/[\22']+/.test($("input[name='bar_search']").val()))
    {
        alert("Campo non valido oppure obbligatorio!");
        $("input[name='bar_search']").focus();
        return false;
    }
    
    switch($("input[name='campo']")) {
        case "utenti":
            break;
        case "amici":
            if(/['\x22]/.test($("input[name='luogo']").val()))
            {
                alert("Campo non valido!");
                $("input[name='luogo']").focus();
                return false;
            }
            break;
        case "post":
            break;
    }
//    $("form[name='advancedSearch']").submit();
    $("form[name='advancedSearch']").ajaxSubmit({
            target: '#search_result',
            success:  function(risposta) {
                $("#search_result").html(risposta); 
            }
        });
  
};

AdvancedSearch.postSearchOption = function () {
    $(".field_filter").html('<legend>Filtra:</legend>\n\
                            <div class="checkbox_field"><input type="radio" name="order" value="normal" checked="true"/>Nessuno</div>\n\
                            <div class="checkbox_field"><input type="radio" name="order" value="author"/>Autore</div>\n\
                            <div class="checkbox_field"><input type="radio" name="order" value="data"/>Data</div>\n\
                            <div class="checkbox_field"><input type="radio" name="order" value="likeit"/>LikeIt</div>');
    };

AdvancedSearch.userSearchOption = function () {
    $(".field_filter").html('<legend>Filtra:</legend>\n\
                            <div class="checkbox_field"><input type="radio" name="order" value="normal" checked="true"/>Nessuno</div>\n\
                            <div class="checkbox_field"><input type="radio" name="order" value="nome"/>Nome</div>\n\
                            <div class="checkbox_field"><input type="radio" name="order" value="username"/>Username</div>');
    };

AdvancedSearch.friendSearchOption = function () {
    $(".field_filter").html('<legend>Filtra:</legend>\n\
                            <fieldset class="filter-block"><legend>Order By:</legend>\n\
                            <div class="checkbox_field"><input type="radio" name="order" value="normal" checked="true"/>Nessuno</div>\n\
                            <div class="checkbox_field"><input type="radio" name="order" value="nome"/>Nome</div>\n\
                            <div class="checkbox_field"><input type="radio" name="order" value="username"/>Username</div>\n\
                            <!-- <div class="checkbox_field"><input type="radio" name="order" value="data"/>Data Amicizia</div> DA IMPLEMENTARE! --> \n\
                            </fieldset>\n\
                            <fieldset  class="filter-block">\n\
                                <legend>Altri Filtri:</legend>\n\
                                <div>Residenza</div>\n\
                                <input type="text" name="luogo" placeholder="Residenza" pattern="[^\22\']" title="Residenza da cercare"/>\n\
                            </fieldset>');
    };
