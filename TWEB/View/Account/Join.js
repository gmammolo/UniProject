/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function joinAction()
{
    if (joinCheckFields())
    {
        document.forms["join"].action = getHome() + "?Join=true";
        document.forms["join"].submit();
    }
    
}

function joinCheckFields()
{
    /** @type HTMLInputElement */
    var username = document.forms["join"].username;
   /** @type HTMLInputElement */
    var email = document.forms["join"].email;
    /** @type HTMLInputElement */
    var password = document.forms["join"].password;
    /** @type HTMLInputElement */
    var cpassword = document.forms["join"].cpassword;
    
    
    
    if(/['\x22]+/.test(username.value)) { 
        alert("Errore: campo Username non accettabile!"); 
        username.focus(); 
        return false; 
    }
    
    if(!/[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/.test(email.value)) { 
        alert("Errore: campo Email non valido"); 
        email.focus(); 
        return false; 
    }

    if(password.value !== cpassword.value) { 
        alert("Errore: Le Password non coincidono"); 
        password.focus();
        return false; 
    }

    if(password.value === username.value) { 
        alert("Errore: La Password deve essere diversa dall' Username"); 
        password.focus(); 
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