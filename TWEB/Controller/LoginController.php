<?php
$username = filter_input(INPUT_POST, 'Username');
$password = filter_input(INPUT_POST, 'Password');

$user_regular = preg_match("/[^'\x22]+/", $username);
$pass_regular = preg_match('/(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(.){4,16}/', $password);
if ($user_regular && $pass_regular) {
    $username = preg_replace("/ /", "-", $username);
    if(User::checkUserValid($username, $password))
    {
        
        $user = User::getUserByLogin($username, $password);
        $user = Session::set('user', $user);
        header("Location: "._INDEX_URL_);
        die();
    }
    else
        Utility::RedMessage ("Account non esistente");
} else {
    Utility::RedMessage("LogIn fallito: Dati inesatti.".PHP_EOL."N.B. Si consiglia l'abilitazione di Javascript");
}
