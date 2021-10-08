<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of Utility
 *
 * @author Giuseppe
 */
class Utility {

    public static function RedMessage($param0) {
        $redmessage = &Session::get('redMessage', 'array');
        $redmessage[]=$param0;
        
    }

    public static function GreenMessage($param0) {
        $greenMessage = &Session::get('greenMessage', 'array');
        $greenMessage[]=$param0;
    }

    public static function YellowMessage($param0) {
        $yellowMessage = &Session::get('yellowMessage', 'array');
        $yellowMessage[]=$param0;
    }
    //put your code here
}
