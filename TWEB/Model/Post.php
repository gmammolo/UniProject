<?php

/**
 * Description of Post
 *
 * @author Giuseppe
 */
class Post extends Model {

    /**
     *
     * @var \User
     */
    protected $author;
    protected $text;
    protected $image;
    protected $hashtag;
    protected $date;
    protected $locate;
    protected $likeit;
    /**
     *
     * @var \Privacy  
     */
    protected $privacy;

    public function __construct($ar = array()) {
        $this->id = $ar['id'];
        $this->author = User::getUserByID($ar['author']);
        $this->text = $ar['text'];
        $this->image = $ar['image'];
        $this->hashtag = unserialize($ar['hashtag']);
        $this->date = $ar['date'];
        $this->locate = $ar['locate'];
        $this->privacy = $ar['privacy'];
        $this->likeit = $ar["likeit"];
    }

    function getAuthor() {
        return $this->author;
    }

    function getText() {
        return $this->text;
    }

    function getImage() {
        return $this->image;
    }

    function getHashtag() {
        return $this->hashtag;
    }

    function getDate() {
        return $this->date;
    }

    function getLocate() {
        return $this->locate;
    }

    function getPrivacy() {
        return $this->privacy;
    }

    function setAuthor(\User $author) {
        $this->author = $author;
    }

    function setText($text) {
        $this->text = $text;
    }

    function setImage($image) {
        $this->image = $image;
    }

    function setHashtag($hashtag) {
        $this->hashtag = $hashtag;
    }

    function setDate($date) {
        $this->date = $date;
    }

    function setLocate($locate) {
        $this->locate = $locate;
    }

    function setPrivacy($privacy) {
        $this->privacy = $privacy;
    }
    function getLikeit() {
        return $this->likeit;
    }

    function setLikeit($likeit) {
        $this->likeit = $likeit;
    }
    
    function addLike() {
        $this->likeit++;
        Notify::addNotify($this->author->getId(), -1, NotifyType::newLikeItComment, $this->id);
        return $this->Update();  
    }

    public function Update() {
        $sql = "UPDATE `Post` SET `text` = :text, `image` = :image, `hashtag` = :hashtag , `date` = :date , `locate` = :loc, `privacy` = :priv , `likeit` = :lk  WHERE `id` = :id ;";
        return self::ExecuteQuery($sql,array( ":text" => $this->getText(), ":image" => $this->getImage(), ":hashtag" => serialize($this->getHashtag() ), ":date" => $this->getDate(), ":loc" => $this->getLocate(), ":priv" => $this->getPrivacy(), ":lk" => $this->getLikeit(),   ":id" => $this->getId() ))->rowCount() == 1;
    }
    
    
    
    //###################################
    
    /**
     * 
     * @param type $author
     * @param type $text
     * @param type $image
     * @param type $locate
     * @param type $hashtag
     * @param type $privacy
     * @return \Post
     */
    public static function createNewPost($author, $text, $image, $locate , $hashtag, $privacy ) {
        $sql = "INSERT INTO `Post` (`id`, `author`, `text`, `image`, `hashtag`, `date`, `locate`, `privacy`) VALUES (NULL, :author, :text, :image, :hashtag, :date, :loco, :privacy);";       
        $id = self::InsertQuery($sql, array(":author" => $author , ":text" => $text , ":image" => $image , ":hashtag" => serialize($hashtag), ":date" => date("y-m-d G:i:s"), ":loco" => $locate, ":privacy" => $privacy));
        if($id>0) Notify::addNotify (-1, $author, NotifyType::newPost, $id);
        return ($id > 0) ? Post::getPostByID($id) : null;
    }

    
    public static function getPostByID($id) {
       $sql = 'SELECT * FROM `Post` Where id = ? ';
       $ris = self::ExecuteQuery($sql, array($id));
        if ( $ris->rowCount() != 1 )
            return null;
        return new Post($ris->fetch());
    }
    
    public static function delete($id) {
        $sql ="DELETE FROM `Post` WHERE `Post`.`id` = ?";
        return self::ExecuteQuery($sql, array($id))->rowCount()==1; 
    }
    
    public static function getPostByHashTag($hashtag) {
        $hashtag = "%\"$hashtag\"%";
        $sql = "SELECT Distinct Post.* FROM `Post`\n"
            . "WHERE hashtag like :ht AND privacy = 3\n"
            . "UNION\n"
            . "SELECT Distinct Post.* FROM `Relationship` JOIN (Post JOIN Showcase ON id_post = Post.id ) ON id_user = requested WHERE `applicant` = :id AND accepted = TRUE AND ablocked = FALSE AND hashtag like :ht AND privacy >= 1\n"
            . "UNION \n"
            . "SELECT Distinct Post.* FROM `Relationship` JOIN (Post JOIN Showcase ON id_post = Post.id ) ON id_user = applicant WHERE `requested` = :id AND accepted = TRUE AND rblocked = FALSE AND hashtag like :ht AND privacy >= 1\n"
            . "UNION\n"
            . "SELECT Distinct Post.* FROM `Post` JOIN Showcase ON id_post = Post.id \n"
            . "WHERE hashtag like :ht ";
        $ris = self::ExecuteQuery($sql, array(":id" => User::getUser()->getId(),":ht" => $hashtag ) );
        $list= array();
        while($row = $ris->fetch()) {
            $list[] = new Post($row);
        }
        return $list;
    }
    
    
    /**
     * Restituisce la bacheca con i post degli amici e quelli personali
     * @param type $infl
     * @param type $supl
     * @return \Showcase
     */
    public static function getNewPost($infl, $supl) {
        $sqlFrienList = "SELECT requested FROM `Relationship` WHERE `applicant` = :id AND `accepted`= TRUE AND `ablocked`=FALSE AND `rblocked`= FALSE UNION SELECT applicant FROM `Relationship` WHERE `requested` = :id AND `accepted`= TRUE AND `ablocked`=FALSE AND `rblocked`= FALSE\n";
        $sql = "SELECT DISTINCT * FROM Post Where privacy >= 1 AND  author in ( $sqlFrienList ) OR  author = :id ORDER BY date DESC LIMIT :inf, :sup";
        $ris = self::ExecuteQuery($sql, array(":id" => User::getUser()->getId()) , array(":inf" => $infl, ":sup" => $supl) );
        $list= array();
        while($row = $ris->fetch()) {
            $list[] = new Post($row);
        }
        return $list;
    }
    
    public static function getHashtagPost($hashtag, $infl, $supl) {
        $hashtag = "%$hashtag%";
        $sqlFrienList = "SELECT requested FROM `Relationship` WHERE `applicant` = :id AND `accepted`= TRUE AND `ablocked`=FALSE AND `rblocked`= FALSE UNION SELECT applicant FROM `Relationship` WHERE `requested` = :id AND `accepted`= TRUE AND `ablocked`=FALSE AND `rblocked`= FALSE";
        $sql = "SELECT DISTINCT * FROM Post Where  ( ( privacy >= 1 AND  author in ( $sqlFrienList ) ) OR  author = :id OR  privacy > 2  ) AND hashtag LIKE :hash "
                . "ORDER BY date DESC LIMIT :inf, :sup";
        $ris = self::ExecuteQuery($sql, array(":id" => User::getUser()->getId(), ":hash" => $hashtag) , array(":inf" => $infl, ":sup" => $supl) );
        $list= array();
        while($row = $ris->fetch()) {
            $list[] = new Post($row);
        }
        return $list;
    }
    
    
    public static function getPostListBySearch($search, $order = "Post.id" ) {
        $search = "%$search%";
        $sqlFrienList = "SELECT requested FROM `Relationship` WHERE `applicant` = :id AND `accepted`= TRUE AND `ablocked`=FALSE AND `rblocked`= FALSE UNION SELECT applicant FROM `Relationship` WHERE `requested` = :id AND `accepted`= TRUE AND `ablocked`=FALSE AND `rblocked`= FALSE";
        $sql = "SELECT * FROM Post WHERE ( privacy = ".Privacy::globale." OR ( privacy = ".Privacy::amici." AND author in ($sqlFrienList) ) OR ( author = :id ) ) AND text LIKE :search  ORDER BY :order ";
        $ris = self::ExecuteQuery($sql, array(":id" => User::getUser()->getId(), ":search" => $search), array(":order" => $order));
        $list= array();
        while($row = $ris->fetch()) {
            $list[] = new Post($row);
        }
        return $list;
        
    }
    
    public static function getAttivity($author) {
       $sql = "SELECT DISTINCT date FROM Post Where author = :id";
        $ris = self::ExecuteQuery($sql, array(":id" => $author) );
        $list= array();
        while($row = $ris->fetch()) {
            $list[] = date("Y-m-d",  strtotime($row["date"]));
        }
        return $list;
    }
}
