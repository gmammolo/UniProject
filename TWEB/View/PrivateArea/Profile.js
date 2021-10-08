/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function ProfileClass() {}

    ProfileClass.addForm = function(event)
    {
        $.ajax({
            type: "POST",
            data: {"ajaxRequest" : "FormProfile"},
            dataType: "html",
            success: function(risposta){
                $("#profile").html(risposta);
            },
            error: function(){
                alert("Chiamata fallita!!!");
            }
        });
    };


    ProfileClass.sendForm =function (profilo)
    {
        var update = false; 
        var username = document.forms["mod-profile"].Username;
        var email = document.forms["mod-profile"].Email;
        var gender = document.forms["mod-profile"].Gender;
        var residenza = document.forms["mod-profile"].Residenza;
        var data = document.forms["mod-profile"].Data;
        var oldPass = document.forms["mod-profile"].oldPass;
        var newPass = document.forms["mod-profile"].newPass;
        var cNewPass = document.forms["mod-profile"].cNewPass;

    //    throw new Error("Something went badly wrong!");
        if(profilo.username !== username.value || profilo.email !== email.value || (gender.value !== "nessuno" && profilo.gender != gender.value ) || profilo.residenza != residenza.value || profilo.data != data.value ) 
            update = true;

        if(/['\x22]+/.test(username.value))
        {
            alert("username non accettabile");
            username.focus();
            return false;
        }

        if(/['\x22]+/.test(residenza.value))
        {
            alert("Residenza non accettabile");
            residenza.focus();
            return false;
        }

        if(!/[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/.test(email.value))
        {
            alert("email non accettabile");
            email.focus();
            return false;
        }

        if(!/(0000-00-00)|(((19|20)\d\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01]))$/.test(data.value))
        {
            alert("Data di Nascita non accettabile");
            data.focus();
            return false;
        }

        if(!( gender.value === "uomo" || gender.value === "donna" || gender.value === "nessuno" || gender.value === "altro"))
        {
             alert("Problemi di manipolazione pagina");
             return false;
        }

        $(".mod-profile").append('<input type="hidden" name="Update" value="'+ update +'"/>');


        if(update)
            document.forms["mod-profile"].submit();
    }







    ProfileClass.changeAvatar= function ()
    {
        $("#change-password").hide();
        $("#change-photo").show();
    }

    ProfileClass.changePassword =function ()
    {
        $("#change-password").show();
        $("#change-photo").hide();
    }


    ProfileClass.sendPhotoRequest = function ()
    {
        radio = document.forms["change-photo-form"].choose;
//        if(radio.value == "image_file")
//        {
//            alert("Sono spiacente, ma questa funzionalità non è attiva in questa versione");
//            return false;
//        }
         document.forms["change-photo-form"].submit();
    }

    ProfileClass.sendPwdRequest = function ()
    {
        oldPwd = document.forms["change-password"].oldPass;
        newPwd =document.forms["change-password"].newPass;
        cPwd = document.forms["change-password"].cNewPass;

        if(!/(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{4,16}/.test(oldPwd.value))
        {
            alert("Password Errata");
            oldPwd.focus();
            return false;
        }
        if(!/(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{4,16}/.test(newPwd.value))
        {
            alert("Password Non accettabile");
            newPwd.focus();
            return false;
        }

        if(cPwd.value !== newPwd.value)
        {
            alert("Le password devono coincidere!");
            newPwd.focus();
            return false;
        }
        document.forms["change-password"].submit();
    }

    ProfileClass.selectURL = function ()
    {
        console.log($("input[name=change]"));
        $("input[name=choose]").filter('[value="image_url"]').prop('checked', true);
    }

    ProfileClass.selectFILE = function ()
    {
        $("input[name=choose]").filter('[value="image_file"]').prop('checked', true);
    }

    ProfileClass.closeFormAvatar = function ()
    {
        $("#change-photo").hide();
    }

    ProfileClass.closeFormPwd = function ()
    {
        $("#change-password").hide();
    }
    
    ProfileClass.addFriend = function(butt)
    {
	var id = $("#id").html();
        $.ajax({
          url: "?formValidate=sendFriendRequest&friendId="+id,
          dataType: "json",
          success: function(risposta){
            if(risposta) {
                $(butt.parentNode).html('<input type="button" value="Richiesta Inviata" disabled="true"/>');
	    }
          }
        });
    }   



function Profile  (username , avatar, email, residenza, data, sesso) {
    this.username = username;
    this.avatar = avatar;
    this.email = email;
    this.residenza= residenza;
    this.data = data;
    this.sesso = sesso;
}
