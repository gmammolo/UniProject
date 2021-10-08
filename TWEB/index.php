<?php
ob_start();
error_reporting(E_ALL); 
ini_set('display_errors', 1);

session_start ();

define("_ROOT_",dirname(__FILE__)."/");

require_once "Config/Config.php";

require_once _DIR_CONTROLLER_ . 'MasterController.php';



