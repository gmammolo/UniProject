<?php


class DatabaseException extends Exception
{
    public function __construct($message)
    {
      parent::__construct($message);
    }
}

class Database
{
    // configuration
    protected static    $dbtype     = "sqlite";
    protected static    $dbhost     = "localhost";
    protected static    $dbname     = "dbgiuseppemammolo";
    protected static    $dbuser     = "giuseppemammolo";
    protected static    $dbpass     = "giuseppemammolo";

    /**
     *
     * @var \PDO
     */
    protected $conn; 
    
    protected static $instance = null;
    
    protected function __construct() {
        $this->conn = new PDO("mysql:host=".static::$dbhost.";dbname=".static::$dbname,static::$dbuser,static::$dbpass);
    }
    function getConn() {
        return $this->conn;
    }

        /**
     * Esegue una query senza ritorno
     * @param type $sql
     * @param type $attr
     * @return boolean
     */
    public  function execute($sql, $attr=array())
    {
        $q = $this->conn->prepare($sql);
	$q->execute($attr);
        if($q->errorCode() == 0) {
            return TRUE;
        } 
        else {
            return $q->errorInfo();
        }
    }
    
    /**
     * Esegue una query in cui ci si aspetta dei valori di ritorno
     * @param type $sql
     * @param type $attr
     * @return PDOStatement
     */
    public function query($sql, $attr=array())
    {
        $q = $this->conn->prepare($sql);
	$q->execute($attr);
        return $q;
    }
    
    public function lastInsertId()
    {
        return $this->conn->lastInsertId();
    }



    /**
     * 
     * @return Database
     */
    public static function getInstance()
    {
      if(static::$instance == null)
      {   
         $c = __CLASS__;
         static::$instance = new $c;
      }
      return static::$instance;
    }
    
}