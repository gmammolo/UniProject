<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

class Menu
{
    protected $icon;
    protected $name;
    protected $html;
    protected $accessLevel;
    
    public function __construct($name, $html,$icon , $accessLevel ) {
        $this->icon = $icon;
        $this->name = $name;
        $this->html =  $html;
        $this->accessLevel = $accessLevel;
        
    }
    
    function getIcon() {
        return '<span class="icon"><img src="'.$this->icon.'" alt = " "/></span>';
    }

    function getName() {
        return $this->name;
    }

    function getHtml() {
        return $this->html;
    }

    function setIcon($icon) {
        $this->icon = $icon;
    }

    function setName($name) {
        $this->name = $name;
    }

    function setHtml($html) {
        $this->html = $html;
    }

    function getAccessLevel() {
        return $this->accessLevel;
    }

    function setAccessLevel($accessLevel) {
        $this->accessLevel = $accessLevel;
    }


}



/**
 * Description of GestoreTemplate
 *
 * @author giuseppe
 */
class MenageTemplate {
   
    /**
     * Menu del template
     * @var array
     */
    protected static $menu = array();
    
    /**
     * array contenente tutti gli include del css
     * @var array 
     */
    protected static $css = array();
    
    
    /**
     * array contente il contenuto da stampare nel content <br>
     * Non permette di gestire direttamente la posizione dei vari content inseriti, <br> 
     * pertanto in caso di impostazioni personalizzate si usa un file php intermediario <br>
     * @var array
     */
    protected static $contents =array();
    
    /**
     * Array con al suo interno i riferimenti al percorso dei wiget <br>
     * @var array
     */
    protected static $widget=array();
    
    /**
     * variabile contenente i vari javascript del sito;
     * @var array 
     */
    protected static  $scripts =array();
    
    /**
     * Aggiunge una regola del css
     * @param string $url
     */
    public static function addCss($url)
    {
            self::$css[] = $url;
    }
    
    /**
     * Aggiunge una regola del Javascript
     * @param string $url
     */
    public static function addJavascript($url)
    {
        self::$scripts[] = $url;
    }
    
    /**
     * Aggiunge un content al sito
     * @param string $url
     */
    public static function addContent($url)
    {
        self::$contents[] = $url;
        
    }
    
    /**
     * Aggiunge un widget
     * @param string $url
     */
    public static function addWidget($url)
    {
        self::$widget[] = $url;
        
    }
    
    /**
     * Nome del tab del menu principale <br>
     * se l'url non è presente si organizza per l'inserimento di un array. <br>
     * @param string $nome
     * @param string $url optional
     */
    public static function addTabMenu($nome, $url = NULL,$icon = null, $accessLevel = Role::Unregister , $menuName = NULL)
    {
            if(!is_null($menuName))
            {
                if(!isset(self::$menu[$menuName]) || !is_array(self::$menu[$menuName]))
                    self::$menu[$menuName] = array();
                array_push( self::$menu[$menuName], new Menu($nome,$url,$icon , $accessLevel ));
            }
            else if(is_null($url))
            {
                self::$menu[$nome] = array($icon);
            }
            else
            {
                self::$menu[$nome] = new Menu($nome,$url,$icon,$accessLevel );
            }
                

    }
    
    
    /**
     * Ritorna l'array con le regole del css
     * @return array
     */
    public static function getCss()
    {
        return self::$css;
    }
    
    /**
     * Ritorna l'array con l'url dei file javascript
     * @return array
     */
    public static function getJavascript()
    {
        return self::$scripts;
    }
    
    /**
     * Ritorna l'array dei content
     * @return array
     */
    public static function getContents()
    {
        return self::$contents;
    }
    
     /**
     * Ritorna l'array dei widget
     * @return array
     */
    public static function getWidget()
    {
        return self::$widget;
    }
    
    /**
     * return array 
     * @return array
     */
    public static function getMenu()
    {
        return self::$menu;
    }
    
    
    /**
     * Eichiama la funzione js per settare la grandezza della pagina
     */
    public static function resize()
    {
        echo "<script>resize();</script>";
    }
    
    
}
