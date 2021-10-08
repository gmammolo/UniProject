/**
 * Convalida il testo per verificare che non ci siano caratteri non accettabili
 * @see Per convalidare con altre Espressioni regolari usare <code>re.test(string)</code> 
 * @param {String} string
 * @returns {boolean}
 */
function validatePassword(string)
{
    var re = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}/;
    return !re.test(string);
}

function validateRegularString(string)
{
    var re = /\w*/i ;
    return re.test(string) ;
}


function jsRedirect(param)
{
    $.ajax({
        type: "GET",
        url: getHome(),
        data: { 'page' : param },
        dataType: "html",
        success: function(risposta){
            $("#container").html(risposta);
        },
        error: function(){
            alert("Chiamata fallita!!!");
        }
    });
}

function resize()
{
    var document_height = $( document ).height();
    $("#container").height(document_height);
    $("aside").height(document_height );
    $("footer").css("top", document_height-15);
}