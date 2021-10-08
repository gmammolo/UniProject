<?php
/**
 * Description of Showcase
 *
 * @author Giuseppe
 */
class Showcase extends Model {
    //put your code here
    
    /**
     *
     * @var \User 
     */
    protected $author;
    /**
     *
     * @var \User
     */
    protected $user;
    /**
     *
     * @var \Post
     */
    protected $post;
    
    /**
     * 
     * @param array $a
     */
    public function __construct($a) {
        $this->id = $a['id'];
        $this->post = Post::getPostByID($a['id_post']);
        $this->author = $this->post->getAuthor();
        $this->user = User::getUserByID($a['id_user']);
    }
    
    function getAuthor() {
        return $this->author;
    }

    function getUser() {
        return $this->user;
    }

    function getPost() {
        return $this->post;
    }

    function setAuthor(\User $author) {
        $this->author = $author;
    }

    function setUser(\User $user) {
        $this->user = $user;
    }

    function setPost(\Post $post) {
        $this->post = $post;
    }

        
    public function Update() {
    
        
    }
    
    
    
    public static function insertPost($id_user, $id_post) {
       $sql = "INSERT INTO `Showcase` (`id`, `id_user`, `id_post`)  VALUES (NULL, :u , :p );";
       $ris = self::InsertQuery($sql, array(":u" => $id_user , ":p" => $id_post ));
//       if($ris>=0)
//         $not =  Notify::addNotify($id_user, User::getUser()->getId(), NotifyType::newPost, $ris);
       return ($ris>=0);
    }

    /**
     * 
     * @param type $userid
     * @param type $inflimit
     * @param type $suplimit
     * @return \Showcase[]
     */
    public static function getShowcasePost($userid, $inflimit, $suplimit) {
        $sql = "SELECT Post.* FROM `Showcase` JOIN Post ON Post.id = id_post WHERE `id_user` = :id ORDER BY id DESC LIMIT :inf, :sup  ;";
        $ris = self::ExecuteQuery($sql, array(":id" => $userid ), array(":inf" => $inflimit, ":sup" => $suplimit ));
        $showcase= array();
        while ($row = $ris->fetch()) {
            $showcase[] = new Post($row);
        }
        return $showcase;
    }
    
    public static function delete($id_user, $id_post) {
        $sql ="DELETE FROM `Showcase` WHERE `id_user` = :idu AND id_post = :idp";
        return self::ExecuteQuery($sql, array(":idu" => $id_user, ":idp" => $id_post))->rowCount()==1; 
    }

}
