<?php

/**
 * Description of Comment
 *
 * @author Giuseppe
 */
class Comment extends Model {
    
    /**
     *
     * @var \Post
     */
    protected $post;
    protected $text;
    /**
     *
     * @var \User 
     */
    protected $author;
    protected $likeit;
    protected $date;
    
    public function __construct($a) {
        $this->id=$a["id"];
        $this->text=$a["text"];
        $this->author=User::getUserByID($a["author"]);
        $this->likeit=$a["likeit"];
        $this->date=$a["date"];
        $this->post=Post::getPostByID( $a["idpost"] );
    }
    
    function addLike() {
        $this->likeit++;
        Notify::addNotify($this->author->getId(), -1, NotifyType::newLikeItComment, $this->id);
        return $this->Update();  
    }

    
    
    public function Update() {
        $sql = "UPDATE `Comment` SET `text` = :text, `likeit` = :lk, `date` = :date WHERE `Comment`.`id` = :id ;";
        return self::ExecuteQuery($sql, array(":text" => $this->getText(), ":lk" => $this->getLikeit(), ":date" => $this->getDate(), ":id" => $this->getId()  ) )->rowCount() > 0;
    }
    
    function getPost() {
        return $this->post;
    }

    function getText() {
        return $this->text;
    }

    function getAuthor() {
        return $this->author;
    }

    function getLikeit() {
        return $this->likeit;
    }

    function getDate() {
        return $this->date;
    }

    function setText($text) {
        $this->text = $text;
    }


    function setLikeit($likeit) {
        $this->likeit = $likeit;
    }

    function setDate($date) {
        $this->date = $date;
    }

    
    public static function getCommentByID($id) {
        $sql = "SELECT * FROM Comment Where id = :id ";
        $ris = self::ExecuteQuery($sql, array(":id" => $id) );
        return ($ris->rowCount() > 0) ? new Comment($ris->fetch()) : null;
    }
    
    
    /**
     * 
     * @param type $postId
     * @return \Comment[]
     */
    public static function  getCommentsByPostID($postId) {
        $sql = "SELECT * FROM Comment Where idpost = :id ";
        $ris = self::ExecuteQuery($sql, array(":id" => $postId) );
        $list= array();
        while($row = $ris->fetch()) {
            $list[] = new Comment($row);
        }
        return $list;
    }

    
    public static function addComment($idpost, $idauthor, $text) {
        
        $sql = "INSERT INTO `Comment` ( `idpost`, `text`, `author`, `date`) VALUES (:idp , :text,  :author, :date);";
        $ris =  self::InsertQuery($sql, array(":idp" => $idpost, ":author" => $idauthor, ":text" => $text, ":date" => date("y-m-d G:i:s")));
        if($ris >= 0 ) Notify::addNotify (-1, $idauthor, NotifyType::newComment, $ris);
        return $ris;
            
    }
    
    public static function delete($id) {
        $sql ="DELETE FROM `Comment` WHERE `id` = ?";
        return self::ExecuteQuery($sql, array($id))->rowCount()==1; 
    }
    
}
