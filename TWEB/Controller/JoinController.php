<?php


$username = filter_input(INPUT_POST, 'username');
$email = filter_input(INPUT_POST, 'email');
$password = filter_input(INPUT_POST, 'password');
$cpassword = filter_input(INPUT_POST, 'cpassword');

if(!preg_match("/['\x22]/", $username ) &&
        preg_match('/[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/',$email) &&
        $password == $cpassword &&
        preg_match('/(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(.){4,16}/', $password))
{
    $username = preg_replace("/ /", "-", $username);
    if(User::checkUser($username, $password))
        Utility::RedMessage("Account Già esistente");
    else
    {
        Utility::GreenMessage("Registrazione in corso");
        $user = User::createAccount($username, $password, $email);
        if(!$user){
            Utility::RedMessage ("Errore nella creazione Account. Si prega di contattare un amministratore");
        }
        else if(is_numeric ($user) && $user == -1) {
            Utility::RedMessage ("Errore nella creazione del profilo. Si prega di contattare un amministratore");
        }
        else
        {
           Session::set('user', $user);
           Notify::addNotify(-1, $user->getId(), NotifyType::accessRequest, $user->getId());
           Utility::GreenMessage("Registrazione Completata con Successo. Benvenuto!");
           header("Location: " . _INDEX_URL_ . "?page=profile" );
           die();
        }
    }
}
else
{
    Utility::RedMessage("Crezione Account fallita: Dati inesatti.".PHP_EOL."N.B. Si consiglia l'abilitazione di Javascript");
}