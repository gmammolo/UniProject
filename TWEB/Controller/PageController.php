<?php


function managePages($page)
{
    $user = User::getUser();
    if($user->getAccessLevel() == Role::Unverified)
    {
        Utility::YellowMessage("Attendi che un Moderatore ti abiliti ad avere accesso completo al sito!");
    }
//    if(!isset($page) || $page =="home" || $page == "")
//    {
//        //DEPRECATA: reindirizza al profilo
//        MenageTemplate::addContent(_DIR_VIEW_."Home/Home.php");
//        MenageTemplate::addCss("View/Home/Home.css");
//        MenageTemplate::addJavascript("View/Home/Home.js");
//
//    }
    else if( ( !isset($page) || $page =="home" || $page == "" || $page=="profile" ) && $user->getAccessLevel() >= Role::Unverified)
    {
        MenageTemplate::addContent(_DIR_VIEW_."PrivateArea/Profile.php");
        MenageTemplate::addCss("View/PrivateArea/Profile.css");
        MenageTemplate::addJavascript("View/PrivateArea/Profile.js");
        MenageTemplate::addCss("View/Showcase/Showcase.css");
        MenageTemplate::addJavascript("View/Showcase/Showcase.js"); 


    }
    else if($page=="admin" && $user->getAccessLevel() >= Role::Moderator)
    {
        MenageTemplate::addContent(_DIR_VIEW_."PrivateArea/AccountList.php");
        MenageTemplate::addCss("View/PrivateArea/AccountList.css");
        MenageTemplate::addJavascript("View/PrivateArea/AccountList.js");
    }
    else if($page=="friends" && $user->getAccessLevel() >= Role::Register)
    {
        MenageTemplate::addContent(_DIR_VIEW_."Friends/Friends.php");
        MenageTemplate::addCss("View/Friends/Friends.css");
        MenageTemplate::addJavascript("View/Friends/Friends.js");
    }
    else if($page=="news" && $user->getAccessLevel() >= Role::Register)
    {
        MenageTemplate::addContent(_DIR_VIEW_."News/News.php");
        MenageTemplate::addCss("View/News/News.css");
        MenageTemplate::addJavascript("View/News/News.js");
        MenageTemplate::addCss("View/News/WriteComment.css");
        MenageTemplate::addJavascript("View/News/WriteComment.js");
        MenageTemplate::addCss("View/Showcase/Showcase.css");
        MenageTemplate::addJavascript("View/Showcase/Showcase.js");
        //update delle notifiche:
        $user->updateLastNewsAccess();
    }
    else if($page=="hashtag" && $user->getAccessLevel() >= Role::Register)
    {
        MenageTemplate::addContent(_DIR_VIEW_."Hashtag/Hashtag.php");
        MenageTemplate::addCss("View/Showcase/Showcase.css");
        MenageTemplate::addJavascript("View/Showcase/Showcase.js");
        MenageTemplate::addJavascript("View/Hashtag/Hashtag.js");
    }
    else if($page=="advancedSearch" && $user->getAccessLevel() >= Role::Register)
    {
        MenageTemplate::addContent(_DIR_VIEW_."SearchBar/AdvancedSearch.php");
        MenageTemplate::addCss("View/SearchBar/AdvancedSearch.css");
        MenageTemplate::addJavascript("View/SearchBar/AdvancedSearch.js");
        MenageTemplate::addCss("View/Showcase/Showcase.css");
        MenageTemplate::addJavascript("View/Showcase/Showcase.js");
    }
    else if($page=="statistiche" && $user->getAccessLevel() >= Role::Register)
    {
        MenageTemplate::addJavascript("Template/js/jsapi.js");
        $grafico = filter_input(INPUT_GET, "grafico");
        switch($grafico) {
            case "postatoreProlifico":
                MenageTemplate::addContent(_DIR_VIEW_."Statistiche/Grafici/PostatoreProlifico.php");
                break;
            case "attivity":
                MenageTemplate::addContent(_DIR_VIEW_."Statistiche/Grafici/attivity.php");
                break;
            default:
                MenageTemplate::addContent(_DIR_VIEW_."Statistiche/Statistiche.php");
                break;
        }
        
    }
    
}
