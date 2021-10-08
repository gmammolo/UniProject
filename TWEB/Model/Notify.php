<?php

/**
 * Description of Notify
 *
 * @author Giuseppe
 */
class Notify extends Model {
    
    
    protected $receiving; 
    protected $type;
    protected $reference;
    protected $cause;
    protected $date;
    
    public function __construct ($a) {
        $this->id= $a["id"];
        if($a["receiving"] >=0)
            $this->receiving= User::getUserByID($a["receiving"]);
        $this->cause= User::getUserByID($a["cause"]);
        $this->type= $a["type"];
        $this->date = $a["date"];
        switch($this->type) {
            case NotifyType::acceptedFriendship : case NotifyType::requestFriendship: case NotifyType::accessRequest:
                $this->reference= User::getUserByID($a["reference_id"]);
                break;
            case NotifyType::newComment: case NotifyType::newLikeItComment:
                $this->reference = Comment::getCommentByID($a["reference_id"]);
                break;
            case NotifyType::newPost: case NotifyType::newLikeItPost:
                $this->reference = Post::getPostByID($a["reference_id"]);
                break;
            case NotifyType::generic: default :
                $this->reference= $a["reference_id"];
                break;                       
       }
    }
    
    public function Update() {
        
    }

    
    public static function addNotify($receiving, $cause,  $type, $reference) {
        $sql = "INSERT INTO `Notify` (`type`, `receiving`, `cause`, `reference`, `date`) VALUES (:type , :receiving , :cause , :reference , :date );";
        $ris= self::InsertQuery($sql, array(":type" => $type, ":receiving" => $receiving, ":cause" => $cause, ":reference" => $reference, ":date" => date("y-m-d G:i:s")));
        return $ris>=0;
        
        
    }
    
    
     public static function removeAdminNotify($cause) {
       $type = "accRequest";
       $sql = "DELETE FROM `Notify` WHERE type = ? AND cause  = ?";
       $ris= self::ExecuteQuery($sql, array($type,$cause ));
       return $ris->rowCount() > 0;
       
     }
    
    public static function getNumNewUser() {
        $sql = "SELECT count(*) as num FROM `Notify` WHERE `type` = ? ";
        $q = self::ExecuteQuery($sql, array(NotifyType::accessRequest));
        return ($q->rowCount() > 0 ) ? $q->fetch()["num"] : 0 ;
    }
    
    public static function removeRequestFriendshipNotify($cause, $ricevente) {
        $sql =  "DELETE FROM `Notify` WHERE receiving = ? AND cause = ? AND type = ? ";
        $q = self::ExecuteQuery($sql, array($ricevente, $cause, NotifyType::requestFriendship));
    }
    
    public static function getNumFriendRequest($id) {
       $sql = "SELECT count(*) as num FROM `Notify` WHERE  `type` = ? AND receiving = ? ";
        $q = self::ExecuteQuery($sql, array(NotifyType::requestFriendship, $id));
        return ($q->rowCount() > 0 ) ? $q->fetch()["num"] : 0 ;
    }
    
public static function getNumNewPost($id){
        $sqlFrienList = "SELECT requested FROM `Relationship` WHERE `applicant` = :id AND `accepted`= TRUE AND `ablocked`=FALSE AND `rblocked`= FALSE UNION SELECT applicant FROM `Relationship` WHERE `requested` = :id AND `accepted`= TRUE AND `ablocked`=FALSE AND `rblocked`= FALSE\n";
        $sql = "SELECT DISTINCT COUNT(*) as num FROM User JOIN ( Post JOIN Notify ON reference = Post.id) ON User.id = :id  Where type = :type AND privacy >= 1 AND  author in ( $sqlFrienList ) AND lastnewsaccess < Notify.date ";
        $q = self::ExecuteQuery($sql, array(":id" => $id, ":type" => NotifyType::newPost)  );
        return ($q->rowCount() > 0 ) ? $q->fetch()["num"] : 0 ;
        
    }
    
    public static function getNumNewComment($id){
        $sqlFrienList = "SELECT requested FROM `Relationship` WHERE `applicant` = :id AND `accepted`= TRUE AND `ablocked`=FALSE AND `rblocked`= FALSE UNION SELECT applicant FROM `Relationship` WHERE `requested` = :id AND `accepted`= TRUE AND `ablocked`=FALSE AND `rblocked`= FALSE\n";
        $sql = "SELECT DISTINCT COUNT(*) as num FROM User JOIN (Comment JOIN Notify ON reference = Comment.id) ON User.id = :id  Where type = :type AND author in ( $sqlFrienList ) AND lastnewsaccess < Notify.date ";
        $q = self::ExecuteQuery($sql, array(":id" => $id, ":type" => NotifyType::newComment) );
        return ($q->rowCount() > 0 ) ? $q->fetch()["num"] : 0 ;   
    }
    
    public static function getNumNewLikeForUser($id) {
        $sql = "SELECT count(*) as num FROM `Notify` JOIN User ON receiving = User.id WHERE ( `type` = :type1 OR `type` = :type2 )  AND `receiving` = :id AND lastnewsaccess < date";
        $query = self::ExecuteQuery($sql,array(":id" => $id,  ":type1" => NotifyType::newLikeItPost ,  ":type2" => NotifyType::newLikeItComment ));
        return ($query->rowCount() > 0 ) ? $query->fetch()["num"] : 0 ;
    }
}
