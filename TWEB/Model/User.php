<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
require_once 'Model.php';


/**
 * Description of User
 *
 * @author giuseppe
 */
class User extends Model implements \JsonSerializable{
    //put your code here
    
    protected $username;
    protected $password; 
    protected $roles;
    protected $accessLevel;
    protected $email;
    protected $profile;
    protected $cookie;
    protected $cookieExpire;
    protected $lastnewsaccess;
    
    
    
    public function __construct($ar = array()) {
            $this->id = $ar['id'];
            $this->username = $ar['username'];
            //TODO:: VALUTARE LA PRESENZA DI QUESTO CAMPO PER MOTIVI DI SICUREZZA...
            //$this->password = $ar['password'];
            $this->roles = unserialize($ar['roles']);
            $this->email = $ar['email'];
            $this->profile = Profile::getProfileByID($ar['profile']);
            $this->accessLevel = $ar['accessLevel'];
            if(isset($ar['cookie'])) 
                $this->cookie =  $ar['cookie'];
            if(isset($ar['$cookie_expire'])) 
                $this->cookieExpire =  $ar['$cookie_expire'];  
            if(isset($ar['lastnewsaccess']))
                $this->lastnewsaccess = $ar['lastnewsaccess'];

    }

    /**
     * 
     * @return \Role
     */
    function getAccessLevel() {
        return $this->accessLevel;
    }

    function setAccessLevel($accessLevel) {
        $this->accessLevel = $accessLevel;
    }

        public function getUsername() {
        return $this->username;
    }

    public function getPassword() {
        return $this->password;
    }

    public function getRoles() {
        return $this->roles;
    }

    public function getEmail() {
        return $this->email;
    }

    /**
        * 
        * @return Profile
        */
    public function getProfile() {
        return $this->profile;
    }

    public function setUsername($username) {
        $this->username = $username;
    }

    public function setPassword($password) {
        $this->password = $password;
    }

    public function setRoles($roles) {
        $this->roles = $roles;
    }

    public function setEmail($email) {
        $this->email = $email;
    }

    public function setProfile($profile) {
        $this->profile = $profile;
    }
    
    function getCookieExpire() {
        return $this->cookieExpire;
    }

    function setCookieExpire($cookieExpire) {
        $this->cookieExpire = $cookieExpire;
    }

    function getCookie() {
        return $this->cookie;
    }

    function setCookie($cookie) {
        $this->cookie = $cookie;
    }

    public function jsonSerialize() {
        $vars = get_object_vars($this);

        return $vars;
    }
    
    public function updateLastNewsAccess()
    {
        $this->lastnewsaccess = date("y-m-d G:i:s");
        $this->Update();
    }
    
    /**
     * Aggiorna l'user salvando nel db le modifiche alle variabili <br>
     * Nota 1: non modifica la password e gli id <br>
     * Nota 2: il false indica che non ci sono state modifiche 
     */
    public function Update() {
        $sql = "UPDATE `User` SET `username` = :user , `accessLevel` = :ac, `roles` = :role , `email` = :email , `cookie` = :cookie, `cookie_expire` = :cookesp , lastnewsaccess = :lastnewsaccess WHERE `User`.`id` = :id ;";
        return self::ExecuteQuery($sql, array(":user" => $this->getUsername(), ":ac" => $this->getAccessLevel(), ":role" => serialize($this->getRoles()), ":email" => $this->getEmail(), ":cookie" => $this->getCookie(), ":cookesp" => $this->getCookieExpire(), ":lastnewsaccess" => $this->lastnewsaccess, ":id" => $this->getId()))->rowCount() == 1;
        
    }
    
 
    
    
   
    
    
    //*********************************************************************
    //*******************************************************************
    
    /**
        * Restituisce l'utente che sta visualizzando la pagina
        * @return /User
        */
    public static function getUser() {
        if(!Session::check('user'))  {
            return User::getVisitator();
        }
        else  {
            
            $user = Session::get ('user', 'User');
            if ($user->getAccessLevel() == "") {
                Session::remove('user');
                return User::getVisitator();
            }
            return $user ;
                
        }
            
    }
    
    public static function checkAccessLevel($al)
    {
        $utente = self::getUser();
        return $utente->getAccessLevel() >= $al;
    }
    
    
    
    public static function getVisitator()
    {
        $ar = array(
            "username" => "Visitatore",
            "roles" => serialize(array()),
            "accessLevel" => Role::Unregister,
            "email" => "",
            "id"=> -1,
            "password" => "",
            "profile" => -1
            
        );
        return new User($ar);
    }
    
    
    public static function checkUserValid($user, $pass) {
        $sql = "SELECT username, password FROM User WHERE username = ? ";
        $ris = self::ExecuteQuery($sql, array($user))->fetch();
        return isset($ris['password']) && crypt($pass , $ris['password'] ) == $ris['password'] ;
    }
    
    public static function checkUser($user) {
        $sql = "SELECT COUNT(*) as NUM FROM User WHERE username = ? ";
        $ris = self::ExecuteQuery($sql, array($user ))->fetch();
        return $ris["NUM"]== 1;
    }
    
    /**
     * 
     * @param type $username
     * @return \User
     */
    public static function getUserByUsername($username) {
        $sql = "SELECT * FROM User WHERE username = ? ";
        $ris = self::ExecuteQuery($sql, array($username ));
        return ($ris->rowCount()== 1) ? new User($ris->fetch()) : null;
    }
    
    public static function getUserByLogin($user, $pass) {
        $sql = "SELECT * FROM User WHERE username = ?";
        $ris = self::ExecuteQuery($sql, array($user ))->fetch();
        return (isset($ris['password']) && crypt($pass , $ris['password'] ) == $ris['password']) ? new User($ris) : NULL;
                
    }
    
    /**
     * 
     * @param type $id
     * @return \User or null
     */
    public static function getUserByID($id) {
        $sql = "SELECT * FROM User WHERE id = ? ";
        $ris = self::ExecuteQuery($sql, array( $id ));
        if ( $ris->rowCount() == 0 )
            return null;
        return new User($ris->fetch());
    }
    /**
     * Crea l'Account
     * @param type $user
     * @param type $pass
     * @param type $email
     * @return /User in caso di successo, null, nel caso fallisca la creazione account, -1 se fallisce la creazione del profilo
     */
    public static function createAccount($user, $pass, $email) {
        $idprofile= Profile::createProfile($user, $email); 
        if($idprofile < 0) {
            return $idprofile;
        }
        $sql = "INSERT INTO `User` (`id`, `username`, `password`, accessLevel, `roles`, `email`, `profile`) VALUES (NULL, :user, :pass, :al , :role, :email, :profile );";
        $id =  self::InsertQuery($sql, array(":user" => $user, ":pass" => crypt($pass), ":al" => Role::Unverified, ":role" => serialize(array()) , ":email" => $email, ":profile" => $idprofile ));
        return User::getUserByID($id);
    }
    
    public static function changePassword($id, $newPass)
    {
        $sql = "UPDATE `User` SET `password` = :pass WHERE `id` = :id";
        return self::ExecuteQuery($sql, array(":pass" => crypt($newPass), ":id" => $id))->rowCount() == 1;
         
    }


    public static function hasAccess($levelRequired)
    {
        $user = User::getUser();
        return $user->accessLevel >= $levelRequired;
    }
    
    /**
     * 
     * @param type $accLevel
     * @return \User[]
     */
    public static function getAllUserWithAccessLevel($accLevel, $search, $order = "accessLevel")
    {
        $search = "%".$search."%";
        $sql = "SELECT User.* FROM `User` JOIN Profile ON `profile` = Profile.id WHERE `accessLevel` < :accLevel AND ( `username` Like :sw OR nome LIKE :sw OR Profile.email Like :sw OR User.email Like :sw ) ORDER BY  :order LIMIT 30 ";
        $lista = array();
        $ris = Model::ExecuteQuery($sql,array( ":accLevel" => $accLevel, ":sw" => $search ), array(":order" => $order));
        foreach ($ris as $elem)
        {
            $lista[] = new User($elem);
        }
        return $lista;
    }
    
    public static function deleteAccount($id)
    {
        $sql =  "DELETE FROM `User` WHERE `User`.`id` = ?";
        $user = User::getUserByID($id);
        if(!isset($user))
            return false;
        return (Profile::deleteProfile($user->getProfile()->getId())) ? self::ExecuteQuery($sql, array($id))->rowCount() == 1 : false;
    }

    public static function getAdminList() {
        $sql= "SELECT * FROM User Where accessLevel >= ? ";
        $query= self::ExecuteQuery($sql, array(Role::Administrator));
        $ris= array();
        while($row = $query->fetch()) {
            $ris []= new User($row);
        }
        return $ris;
                
    }
    
    public static function getMustPosters() {
        $sql = "SELECT nome , count(author) as num FROM (User Join Post On User.id=author) JOIN Profile ON Profile.id = User.profile Where accessLevel <= ".User::getUser()->getAccessLevel()." Group by author Order By num DESC ";
        $query= self::ExecuteQuery($sql,array());
        $ris= array();
        while($row = $query->fetch()) {
            $ris[] = $row["nome"];
            $ris[] = $row["num"];
            
        }
        return $ris;
    }

    
    public static function getAttivity()
    {
        return Post::getAttivity(User::getUser()->getId());
    }
}
 

