<?php

abstract class BasicEnum {
    const __class = __CLASS__;
    const __default =  NULL;

    private static function getConstants() {
          /* NOTA IMPLEMENTATIVA: su una versione di PHP successiva a 5.4 la riga qui sotto è in realtà
           * $reflect = new ReflectionClass ( static::class );
           * Il vantaggio in quella implementazione è la possibilità di non utilizzare la 
           * const __class = __CLASS__; 
           * che in questa versione viene invece obbligatoriamente scritta in tutti gli enum che implementano
           * BasicEnum. Inoltre in quella versione qui in questo metodo non è richiesto il secondo 
           * array_shift($constats); (in quanto la costancle __class non esiste e non va tolta dalla lista)
           */
          $reflect = new ReflectionClass ( static::__class);
    	  $list_constants = $reflect->getConstants();
           
          $constats = array_keys($list_constants);
          array_shift($constats);
          array_shift($constats);
          return $constats;
    }
    
    
    public static function getConstant($index)
    { 
        if(static::isValidValue($index))
        {
            
           return static::getConstants()[$index];
        }
        return "";  
    }
    
    
    
    
    public static function isValidName($name) {
        return in_array($name, static::getConstants());
    }

    public static function isValidValue($value) {
        return $value >= 0 && $value < count(static::getConstants());
    }
}

class Role extends BasicEnum 
{

    const __class = __CLASS__;
    const __default =  self::Unregister;
    
    const Unregister = 0;
    const Unverified = 1;
    const Register = 2;
    const Moderator = 3;
    const Administrator = 4;
    const Founder = 5;
}

class Privacy extends BasicEnum 
{
    const __class = __CLASS__;
    const __default =  self::amici;
    
    const privato = 0;
    const amici = 1;
    const amiciplus = 2;
    const globale = 3;
}

class NotifyType extends BasicEnum
{
    const __class = __CLASS__;
    const __default = self::generic;
    
    const generic = "generic";
    const requestFriendship = "rFriend";
    const acceptedFriendship = "aFriend";
    const newPost = "newPost";
    const newComment = "newComment"; 
    const newLikeItPost = "LikePost";
    const newLikeItComment = "LikeCommen";
    const accessRequest = "accRequest";
}