<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
require_once 'Database.php';
/**
 * Description of model
 *
 * @author Giuseppe
 */
abstract class Model {
    //put your code here
    
    
    protected $id;
    
    public function getId() {
        return $this->id;
    }

    public function setId($id) {
        $this->id = $id;
    }

    
    
    public static function init()
    {
        
    }
    
    
    public function __construct() 
    {
        
    }

    
    public static function CreateTable()
    {
        $sql ="CREATE TABLE IF NOT EXISTS  ";

        return Database::getInstance()->execute($sql);
    }
    
    /**
     * 
     * @param type $sql
     * @param array $attr
     * @param array $limit array con valore inf e sup
     * @return PDOStatement
     */
    public static function ExecuteQuery($sql , $attr = array() , $limit = array() )
    {        
        
        foreach ($limit as  $key => $val )
        {
           $sql = str_replace($key,$val ,$sql);
        }
        $ris = Database::getInstance()->getConn()->prepare($sql, $attr);
        $ris->execute($attr);
        return $ris;
    }
    
    /**
     * Effettua una query di inserimento
     * @param type $sql
     * @param type $attr
     * @return int ritorna l'iD nel caso si utilizzi un auto_increment (e -1 in caso di fallimento)
     */
    public static function InsertQuery($sql , $attr = array() )
    {        
        $ris = Database::getInstance()->query($sql, $attr);
        //NOTE: This method may not return a meaningful or consistent result across different PDO drivers,
        // because the underlying database may not even support the notion of auto-increment fields or sequences.
        return($ris->rowCount() > 0) ? Database::getInstance()->lastInsertId() : -1;
    }
    
    
    public abstract function Update();
    
    
}
