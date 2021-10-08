<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

require_once 'Model.php';

/**
 * Description of Profile
 *
 * @author giuseppe
 */
class Profile extends Model implements \JsonSerializable{
    //put your code here
    protected $id;
    protected $nome;
    protected $generalita;
    protected $avatar;
    protected $email;
    protected $residenza;
    protected $data;
    
    public function getNome() {
        return $this->nome;
    }

    public function getEmail() {
        return $this->email;
    }

    public function getResidenza() {
        return $this->residenza;
    }

    public function getData() {
        return $this->data;
    }

    public function setNome($nome) {
        $this->nome = $nome;
    }

    public function setEmail($email) {
        $this->email = $email;
    }

    public function setResidenza($residenza) {
        $this->residenza = $residenza;
    }

    public function setData($data) {
        $this->data = $data;
    }
    public function getAvatar() {
        return $this->avatar;
    }

    public function setAvatar($avatar) {
        $this->avatar = $avatar;
    }
    function getId() {
        return $this->id;
    }

    function getGeneralita() {
        return $this->generalita;
    }

    function setId($id) {
        $this->id = $id;
    }

    function setGeneralita($generalita) {
        $this->generalita = $generalita;
    }

        public function __construct($arr = array() ) {
        $this->id = $arr['id'];
        $this->avatar =  $arr['avatar'];
        $this->generalita = $arr['generalita'];
        $this->nome = $arr['nome'];
        $this->data = $arr['data'];
        $this->email = $arr['email'];
        $this->residenza = $arr['residenza'];
        
    }
    
    /**
     * 
     * @param type $id
     * @return \Profile
     */
    public static function getProfileByID($id)
    {
        if($id == -1)
            return self::getVisitator ();
        
        $ar = self::ExecuteQuery("SELECT * FROM Profile WHERE id=?;", array($id))->fetch();
        return new Profile($ar);
        
    }

    public function jsonSerialize() {
        $vars = get_object_vars($this);

        return $vars;
    }
    
    
    public function Update() {
        $sql = "UPDATE `Profile` SET `nome` = :nome, `generalita` = :gen, `residenza` = :res, `data` = :data, `email` = :email, avatar = :avatar WHERE `Profile`.`id` = :id;";
        $ris = Model::ExecuteQuery($sql,array(":nome" => $this->getNome(), ":gen" => $this->getGeneralita() , ":res" => $this->getResidenza(), ":data" => $this->getData(), ":email" => $this->getEmail(), ":avatar"=> $this->getAvatar() , ":id" => $this->getId() ));
        
    }
    
    /**
     * 
     * @return \Profile
     */
    public static function getVisitator()
    {
        $ar = array(
            "avatar" => "Template/images/avatar.jpg",
            "nome" => "Visitatore",
            "data" => "00/00/0000",
            "generalita" => "nessuno",
            "email" => "",
            "residenza" => "",
            "id" => -1
        );
        return new Profile($ar);
    }
    
    /**
     * 
     * @param type $nome
     * @param type $email
     * @return int
     */
    public static function createProfile($nome, $email)
    {
        $avatar = "Template/images/avatar.jpg";
        $sql = "INSERT INTO `Profile` (`id`, `nome`, `avatar`, `residenza`, `email`) VALUES (NULL, ?, ?, '', ?)";
        $id = self::InsertQuery($sql, array($nome, $avatar, $email));
        $prof = Profile::getProfileByID($id);
        return (isset($prof)) ? $id : -1 ;
    }

    public static function deleteProfile($id)
    {
        $sql = "DELETE FROM `Profile` WHERE `Profile`.`id` = ?";
        return self::ExecuteQuery($sql,array($id))->rowCount() == 1;
    }
    
}
