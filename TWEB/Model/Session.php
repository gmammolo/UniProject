<?php

//require_once 'User.php';
//require_once 'Admin.php';

/**
 * Classe Per la Gestione della Sessione.
 *
 * @author Giuseppe
 */
class Session {
    
    private static $deserialized;
    
    /**
     * Variabile che indica se è abilitato lo start automatico della sessione o meno
     * @var boolean
     */
    private static $autostartsession=TRUE;
    
    private static function _deseriaize($name, $type) {
        if(isset($_SESSION[$name]))
           return unserialize ($_SESSION[$name]);
        else if(class_exists($type))
            return new $type(); 
        else if($type==='array')
            return array();
        else
            return '';
    }
    
    
    /**
     * Restituisce la variabile salvata nel session con il nome passato come parametro.
     * Se non esiste la variabile la crea con il type passato
     * @param string $name Nome della variabile da trovare
     * @param string $type Tipo della variabile
     * @return type L'ogetto richiesto  
     * @throws InvalidArgumentException
     */
    public static function &get($name, $type) {
        if(self::$autostartsession && session_status() == PHP_SESSION_NONE)
            session_start ();
        if(isset(self::$deserialized[$name]))
            $return = &self::$deserialized[$name];
        else
        {
            //& tolta perchè generava un errore: controllare
            $return = self::_deseriaize ($name, $type);
            self::$deserialized[$name] = &$return;
        }
        if(class_exists($type) & !is_a($return, $type))
        {
            print_r($type);
            throw new InvalidArgumentException("Oggetto $name non è di classe $type ");
        }
        return $return;    
    }
    
    public static function set($name, $obj)
    {
        self::$deserialized[$name]=$obj;
    }
    
    public static function toString()
    {
        var_dump(self::$deserialized);
    }


    /**
     * Controlla se la variabile $name è salvata
     * @param string $name
     * @return bool TRUE se esiste, FALSE altrimenti
     */
    public static function check($name)
    {
        return isset($_SESSION[$name]) || isset(self::$deserialized[$name]);
    }
    
    /**
     * Rimuove una variabile dallla sessione
     * @param string $name
     */
    public static function remove($name)
    {
        if(isset($_SESSION[$name]))
            unset($_SESSION[$name]);
        if(isset(self::$deserialized[$name]))
            unset(self::$deserialized[$name]);
            
    }

    public static function destroy()
    {
        if(isset(self::$deserialized )) 
            foreach (self::$deserialized as $key => $obj )
            {
                unset(self::$deserialized[$key]);
            }
        if(isset($_SESSION ))     
            foreach ($_SESSION as $key => $obj )
            {
                unset($_SESSION[$key]);
            }
    }
    
    public static function shutdown()
    {
        if(isset(self::$deserialized))
        {
            foreach (self::$deserialized as $key => $obj )
            {
                $_SESSION[$key]=  serialize($obj);
            }
        }
    }
    
    /*
     * public static function register_shutdown_function($callback)
     *   {
     *       //accoda $callback a $shutfunc
     *       self::$shutfunc []= $callback;
     *   }
     */
    
}

register_shutdown_function(array('Session','shutdown'));
