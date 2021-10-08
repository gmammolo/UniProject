<?php

//################################�&nbsp;
//DEFAULT TEMPLATE
$icon = "Template/images/";

MenageTemplate::addTabMenu("Home",_INDEX_URL_ ,  $icon ."home.png" , Role::Unverified );
MenageTemplate::addTabMenu("News", _INDEX_URL_ ."?page=news" ,  $icon . 'file%203.png' , Role::Register);
MenageTemplate::addTabMenu("Friends",_INDEX_URL_ . "?page=friends" ,   $icon . 'user.png' , Role::Register);
MenageTemplate::addTabMenu("Amministrazione",_INDEX_URL_ . "?page=admin",  $icon ."shield.png" , Role::Administrator );
MenageTemplate::addTabMenu("Statistiche",null,  $icon ."stats%204.png" , Role::Register );
MenageTemplate::addTabMenu("Attivita",_INDEX_URL_ . "?page=statistiche&grafico=attivity",  $icon ."stats%204.png" , Role::Register ,"Statistiche");
MenageTemplate::addTabMenu("classifica post",_INDEX_URL_ . "?page=statistiche&grafico=postatoreProlifico",  $icon ."stats%204.png" , Role::Register ,"Statistiche" );
MenageTemplate::addTabMenu("Logout", _INDEX_URL_ . '?Logout=true', $icon ."display%20down.png" , Role::Unverified);

MenageTemplate::addJavascript("Template/js/jquery.min.js");
MenageTemplate::addJavascript("Template/js/jquery.scrolly.min.js");
MenageTemplate::addJavascript("Template/js/jquery.scrollzer.min.js");
MenageTemplate::addJavascript("Template/js/jquery.form.js"); 
MenageTemplate::addJavascript("Template/js/scripts.js");
MenageTemplate::addJavascript("View/Notify/Notify.js");
MenageTemplate::addJavascript("View/SearchBar/search.js");

MenageTemplate::addCss("Template/css/style.css");
MenageTemplate::addCss("Template/css/pure-min.css");
MenageTemplate::addCss("View/SearchBar/search.css");


$formValidate = filter_input(INPUT_GET, 'formValidate');
if(isset($formValidate))
{
    require_once _DIR_CONTROLLER_ . 'formValidate.php';
    die();
}

$ajaxRequest = filter_input(INPUT_POST, 'ajaxRequest');
if(isset($ajaxRequest))
{
    require_once _DIR_CONTROLLER_ . 'ajaxRequest.php';
    die();
}
$login = filter_input(INPUT_GET, 'Login');
if(isset($login) && !User::hasAccess(Role::Unverified) )
{
    require_once _DIR_CONTROLLER_ . 'LoginController.php';
}

$join = filter_input(INPUT_GET, 'Join');
if(isset($join))
{
    require_once _DIR_CONTROLLER_ . 'JoinController.php';
}

$logout = filter_input(INPUT_GET, 'Logout');
if(isset($logout))
{
    Session::destroy();
    header("Location: "._INDEX_URL_);
    die();
}

//#############################�&nbsp;
//GESTIONE ACCESSI 
if( User::checkAccessLevel(Role::Register) ) {
    MenageTemplate::addCss("Template/css/style-site.css");
    $page = filter_input(INPUT_GET, 'page');
    managePages ($page);
    require_once  "Template/page.php";
    
}
else if(User::checkAccessLevel(Role::Unverified)){
    
    MenageTemplate::addCss("Template/css/style-site.css");
    $page = filter_input(INPUT_GET, 'page');
    managePages ($page);
    require_once  "Template/page.php";
}
else {
    //NO LOGIN EFFETTUATO
    MenageTemplate::addJavascript("View/Account/Login.js");
    MenageTemplate::addJavascript("View/Account/Join.js");
    MenageTemplate::addContent(_DIR_VIEW_."Account/Account.php");
    MenageTemplate::addCss("Template/css/style-login.css");
    require_once  "Template/login-page.php";
}


