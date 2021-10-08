/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function loginAction()
{

    if (loginCheckFields())
    {
        document.forms["login"].action = getHome() + "?Login=true";
        document.forms["login"].submit();
    }
    
}

function loginCheckFields()
{
    /** @type HTMLInputElement */
    var username = document.forms["login"].Username;
    /** @type HTMLInputElement */
    var password = document.forms["login"].Password;
    
    if(/['\x22]+/.test(username.value)) { 
        alert("Errore: campo Username non accettabile!"); 
        username.focus(); 
        return false; 
    }
    
    if(!/(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(.){4,16}/.test(password.value)) { 
        if(password.value.length < 4 || password.value.length > 16 )
        {
            alert("La Password deve contenere da 4 a 16 caratteri");
        }
        else if(!/(?=.*[A-Z])/.test(password.value))
        {
            alert("La Password deve contenere almeno una lettera Maiuscola");
        }
        else if(!/(?=.*[a-z])/.test(password.value))
        {
            alert("La Password deve contenere almeno una lettera minuscola");
        }
        else if(!/(?=.*[0-9])/.test(password.value))
        {
            alert("La Password deve contenere almeno un numero");
        }
        else
        {
            alert("Attenzione, la password contiene caratteri illegali.");
        }
        password.focus(); 
        return false; 
    }
    return true;
    
}