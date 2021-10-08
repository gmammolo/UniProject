/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function loadAccountList()
{
    $.ajax({
      type: "POST",
      data: {"ajaxRequest" : "getAccountList" , search_cerca_account : ""},
      dataType: "html",
      success: function(risposta){
        $("#accountList").html(risposta);
      }
    });
}



function load_search_user(event)
{

    if(/['\x22]+/.test($("input[name='search_cerca_account']").val()))
        return null;
        
    $.ajax({
      type: "POST",
      data: {"ajaxRequest" : "getAccountList"  ,  search_cerca_account : $("input[name='search_cerca_account']").val()  },
      dataType: "html",
      success: function(risposta){
        $("#accountList").html(risposta);
      }
    });
    
    
}

function user_vista(username, name , icon, id )
{
    this.username = username;
    this.name= name;
    this.icon=icon;
    this.id=id;
}


function updateSendAcLevel(id)
{
    
    ac = $("select[name='ruolo'].u"+id).val();
    if(ac == "Nessuno")
        return false;
    href =  $("#updateAcLevel.u"+id).attr('href');
    $("#updateAcLevel.u"+id).attr('href', href + "&ruolo="+ac);
}