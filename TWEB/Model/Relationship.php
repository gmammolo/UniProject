<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
require_once 'Model.php';

/**
 * Description of Friendship
 *
 * @author giuseppe
 */
class Relationship extends Model {

    //put your code here


    /**
     *
     * @var \User
     */
    protected $applicant;
    /**
     *
     * @var \User
     */
    protected $requested;
    protected $ablocked;
    protected $rblocked;

    /**
     *  Indica se requested ha accettato la richiesta.
     *  E' sufficiente farlo solo in un verso in quanto una volta che applicant 
     * fa la richiesta ha accettato  implicitamente una risposta positiva
     * @var boolean
     */
    protected $accepted;
    protected $requestDate;
    protected $acceptedDate;
    protected $ablockedDate;
    protected $rblockedDate;

    public function __construct($ar = array()) {
        $this->id = $ar['id'];
        $this->applicant = User::GetUserByID($ar["applicant"]);
        $this->requested = User::GetUserByID($ar["requested"]);
        $this->ablocked = $ar['ablocked'];
        $this->rblocked = $ar['rblocked'];
        $this->accepted = $ar['accepted'];
        $this->requestDate= $ar['requestDate'];
        $this->acceptedDate = $ar['acceptedDate'];
        $this->ablockedDate = $ar['ablockedDate'];
        $this->rblockedDate = $ar['rblockedDate'];
    }

    /**
     * 
     * @return User
     */
    function getApplicant() {
        return $this->applicant;
    }

    /**
     * 
     * @return User
     */
    function getRequested() {
        return $this->requested;
    }

    function getAblocked() {
        return $this->ablocked;
    }

    function getRblocked() {
        return $this->rblocked;
    }

    function getAccepted() {
        return $this->accepted;
    }

    function getRequestDate() {
        return $this->requestDate;
    }

    function getAcceptedDate() {
        return $this->acceptedDate;
    }

    function getAblockedDate() {
        return $this->ablockedDate;
    }

    function getRblockedDate() {
        return $this->rblockedDate;
    }

    function setAccepted($accepted) {
        $this->accepted = $accepted;
    }

    function setRequestDate($requestDate) {
        $this->requestDate = $requestDate;
    }

    function setAcceptedDate($acceptedDate) {
        $this->acceptedDate = $acceptedDate;
    }

    function setAblockedDate($ablockedDate) {
        $this->ablockedDate = $ablockedDate;
    }

    function setRblockedDate($rblockedDate) {
        $this->rblockedDate = $rblockedDate;
    }

    /**
     * Blocca e sblocca il Requested
     */
    public function dABlockedAction() {
        $this->blocked = $this->blocked xor 1;
        Update();
    }

    /**
     * Blocca e sblocca l'applicant
     */
    public function dRBlockedAction() {
        $this->blocked = $this->blocked xor 1;
        Update();
    }
    
    public function acceptFriendship() {
        $this->setAccepted(TRUE);
        $this->setAcceptedDate(date("y-m-d G:i:s"));
        $this->Update();
    }
    

    public function Update() {
        $sql = "UPDATE `Relationship` SET `ablocked` = :ablocked, `rblocked` = :rblocked, `accepted` = :accepted, `acceptedDate` = :acceptedDate, `requestDate` = :requestDate, `ablockedDate` = :ablockedDate , `rblockedDate` = :rblockedDate WHERE `Relationship`.`id` = :id";
        $ris= self::ExecuteQuery($sql, array( ":ablocked" => $this->getAblocked() ,":rblocked" => $this->getRblocked(), ":accepted" => $this->getAccepted(),  ":acceptedDate" => $this->getAcceptedDate() , ":requestDate" => $this->getRequestDate(), ":ablockedDate" => $this->getAblockedDate(), ":rblockedDate" => $this->getRblockedDate(), ":id" => $this->getId()  ));
        return ($ris->rowCount() == 1);
    }

    
    
    public static function addFriendRequest($idr, $ida) {
        $sql = "INSERT INTO `Relationship` (`applicant`, `requested`, `requestDate`) VALUES ( :applicant , :requested, :date );";
        $ris = self::ExecuteQuery($sql, array(":applicant" => $idr, ":requested"=>$ida, ":date" => date("y-m-d G:i:s") ));
        return ($ris->rowCount() > 0);
    }
    
    public static function getRandomNotRelated($userID)
    {
        $sql = "SELECT * FROM `User`\n"
                . "WHERE id <> :userID AND accessLevel > ".Role::Unverified."  AND accessLevel < ".Role::Administrator."  "
                . "AND id NOT IN (\n"
                . " SELECT applicant From Relationship Where requested = :userID \n"
                . " UNION\n"
                . " SELECT requested From Relationship Where  applicant = :userID \n"
                . " ) ORDER BY RAND() LIMIT 30 ";
        $u = Model::ExecuteQuery($sql, array(":userID" => $userID));
        $ris = array();
        while($row =  $u->fetch()) {
            $ris []= new User($row);
        }
        return $ris;    
            
        
    }
    
   
    
    
    public static function getRandomRelationship($userID)
    {
        $sql = "SELECT * FROM `User`\n"
                . "WHERE id <> :userID AND accessLevel > 1 AND accessLevel <= 3 AND id IN (\n"
                . " SELECT applicant From Relationship Where 1\n"
                . " UNION\n"
                . " SELECT requested From Relationship Where 1\n"
                . " ) ORDER BY RAND() LIMIT 0, 30 ";
        $u = Model::ExecuteQuery($sql, array(":userID" => $userID));
        return ($u->rowCount() > 0) ? new Relationship($u->fetch()) : null;
    }
    
    /**
     * Restituisce le richieste in pendente all' utente
     * @param \int $userID
     * @param \int $inf
     * @param \int $sup
     * @return \Relationship
     */
    public static function getFriendshipRequest($userID, $inf, $sup) {
        $sql = "SELECT * FROM `Relationship` WHERE  `requested` = :idr AND `accepted`= FALSE AND `ablocked`=FALSE AND `rblocked`= FALSE ORDER BY requestDate DESC LIMIT :inf , :sup";
        $ris = self::ExecuteQuery($sql, array(":idr" => $userID), array(":inf"=>$inf, ":sup"=>$sup));
        $acp = array();
        while($row=$ris->fetch()) {
            $acp[] = new Relationship($row);
        }
        return $acp;
    }
    
    
    public static function getFriendsList($userID, $inf, $sup) {
        $sql = "SELECT * FROM `Relationship` WHERE ( `applicant` = :id OR `requested` = :id ) AND `accepted`= TRUE AND `ablocked`=FALSE AND `rblocked`= FALSE ORDER BY id LIMIT :inf , :sup";
        $ris = self::ExecuteQuery($sql, array(":id"=>$userID), array(":inf"=>$inf, ":sup"=>$sup));
        $acp = array();
        while($row=$ris->fetch()) {
            $acp[] = new Relationship($row);
        }
        return $acp;
    }
    
    public static function getRandomFriend($userID) {
        $sql = "SELECT * FROM `Relationship` WHERE `applicant` = :id OR `requested` = :id AND `accepted`= TRUE AND `ablocked`=FALSE AND `rblocked`= FALSE ORDER BY RAND() LIMIT 1";
        $ris = self::ExecuteQuery($sql, array(":id"=>$userID));
        return ($ris->rowCount()==1) ? new Relationship($ris->fetch()) : NULL; 
    }
    
    /**
     * 
     * @param type $id1
     * @param type $id2
     * @return \Relationship
     */
    public static function getRelationship( $id1, $id2) {
        $sql = "SELECT * FROM `Relationship` WHERE ( `applicant` = :id1 OR applicant = :id2 ) AND ( `requested` = :id1 OR requested = :id2) ;";
        $ris= self::ExecuteQuery($sql, array(":id1"=>$id1 , ":id2"=>$id2 ));
        return ($ris->rowCount()==1) ? new Relationship($ris->fetch()) : NULL; 
    }
    
    /**
     * 
     * @param type $iduser
     * @param string $search
     * @return \User
     */
    public static function getFriendsListWithSearch($iduser, $search, $order = "User.id", $residenza= ""){
        $search = "%".$search."%";
        $sqlFrienList = "SELECT requested FROM `Relationship` WHERE `applicant` = :id AND `accepted`= TRUE AND `ablocked`=FALSE AND `rblocked`= FALSE UNION SELECT applicant FROM `Relationship` WHERE `requested` = :id AND `accepted`= TRUE AND `ablocked`=FALSE AND `rblocked`= FALSE";
        $sql = "SELECT  DISTINCT  User.* FROM User JOIN Profile ON Profile.id = User.profile WHERE User.id IN ( $sqlFrienList )  AND ( username LIKE :search OR nome LIKE :search ) $residenza ORDER BY :order ";
        $ris = self::ExecuteQuery($sql, array(":id" => $iduser, ":search" => $search),array(":order" => $order));
        $acp = array();
        while($row=$ris->fetch()) {
            $acp[] = new User($row);
        }
        return $acp;
        
    }
    
    public static function getNotFriendListWithSearch($iduser, $search) {
        $search = "%".$search."%";
        $sql = "SELECT User.* FROM User JOIN Profile ON User.profile = Profile.id WHERE User.id <> :id AND accessLevel >= 2 AND accessLevel < :accLevel \n"
    . "AND ( `username` Like :sw OR nome LIKE :sw OR Profile.email Like :sw OR User.email Like :sw )\n"
    . "AND User.id NOT IN (SELECT requested FROM `Relationship` WHERE `applicant` = :id AND accepted = TRUE AND ablocked = FALSE\n"
    . "UNION \n"
    . " SELECT applicant FROM `Relationship` WHERE `requested` = :id AND accepted = TRUE AND rblocked = FALSE )";
        
        $ris = self::ExecuteQuery($sql, array(":id" => $iduser, ":sw" => $search, ":accLevel" => User::getUser()->getAccessLevel()));
        $acp = array();
        while($row=$ris->fetch()) {
            $acp[] = new User($row);
        }
        return $acp;
        
    }
    
    
}



