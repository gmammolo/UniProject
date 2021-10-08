<?php

/**
 * Maschera della classe Relationship: Serve per gestire velocemente le relazioni, in  quanto 
 * non è sempre prestabilito se l'utente collegato è l'applicant o il requested
 * NOTE: USARE SOLO SE LA RELAZIONE HA A CHE FARE CON  L'UTENTE
 * 
 */
class Friendship {
    /*
     *   FUNZIONI STATICHE:
     *       $user = "a";
     *       $fun = "Relationship::".$user."Pippo";
     *       call_user_func($fun);
     * 
     *  FUNZIONI NON STATICHE
     *       $rel = new Relationship();
     *       $user = "r"."Pippo";
     *       $fun = $rel->$user();
     * 
     */

    /**
     *
     * @var \User 
     */
    private $user;

    /**
        *
        * @var \User
        */
    private $friend;

    /**
        *
        * @var Relationship
        */
    private $relationship;

    /**
     * 
     * @param \Relationship $relationship
     */
    public function __construct($relationship) {
        $this->relationship = $relationship;
        if ($relationship->getApplicant()->getId() == User::getUser()->getId()) {
            $this->user = "A";
            $this->friend = "R";
        } else {
            $this->user = "R";
            $this->friend = "A";
        }
    }

    /**
     * 
     * @return \User
     */
    public function getUser() {
        return ( $this->user == "A") ? $this->relationship->getApplicant() : $this->relationship->getRequested();
    }

    /**
     * 
     * @return \User
     */
    public function getFriend() {
        return ( $this->friend == "A") ? $this->relationship->getApplicant() : $this->relationship->getRequested();
    }

    
    public function Blocks()
    {
        $method= "d". $this->user."BlockedAction";
        $this->relationship->$method();
                
    }
    
    public function isBlocked()
    {
        $method= "get".$this->user."BlockedAction";
        return $this->relationship->$method();
    }

    /**
     * 
     * @param type $user
     * @return \Friendship
     */
    public static function getRandomFriendship($user)  {
        $relation = Relationship::getRandomFriend($user->getID());
        return self::checkRelationIsFriendShip($relation) ?  new Friendship($relation) : NULL  ;
    }
    
    
    public static function getFriendsList($user, $inf, $sup) {
        $relationes = Relationship::getFriendsList($user->getID(), $inf, $sup);
        $ris=array();
        foreach($relationes as $relation) {
            $ris[]=  new Friendship($relation) ;
        }
            
        return $ris;
    }
    
    /**
     * Restituisce una relazione di amicizia (quindi solo se è già accettato e non bloccato)
     * @param type $id1
     * @param type $id2
     * @return /Friendship
     */
    public static function getFriendship($id1, $id2) {
        $relation = Relationship::getRelationship($id1, $id2);
        return self::checkRelationIsFriendShip($relation) ? new Friendship($relation) : null;
    }
    
    public static function checkRelationIsFriendShip( $relation) {
        return (isset($relation) && $relation->getAccepted() && !$relation->getAblocked() && !$relation->getRblocked());
    }  
    
    public static function IsFriend(\User $friend) {
        if(!isset($friend))
            return false;
        $friendship = self::getFriendship(User::getUser()->getId(), $friend->getId());
        return isset($friendship);
    }
    
    public static function getFriendsListWithSearch($user, $search, $order = "User.id", $residenza = "") {
        return Relationship::getFriendsListWithSearch($user->getId(),$search, $order, $residenza);
    }
    
}
