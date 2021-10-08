<?php
// TODO: come reperire percorso completo:
//$actual_link = "http://$_SERVER[HTTP_HOST]$_SERVER[REQUEST_URI]";

//define("_HOME_URL_" , "http://localhost:8200/studenti/st116798/Public/SocialProject/");

define("_HOME_URL_" , "http://$_SERVER[HTTP_HOST]/studenti/st116798/Public/SocialProject/");
define("_INDEX_URL_" , "/studenti/st116798/Public/SocialProject/index.php");
define("_BASE_URL_" , "/studenti/st116798/Public/SocialProject/");
define("_DIR_TEMPLATE_", _ROOT_."Template/");
define("_DIR_MODEL_", _ROOT_."Model/");
define("_DIR_VIEW_", _ROOT_."View/");
define("_DIR_CONTROLLER_", _ROOT_."Controller/");
define("_DIR_CONFIG_", _ROOT_."Config/");
define("__DIR_UPLOAD__", _ROOT_."Public/");
define("__PUBLIC_URL__", _HOME_URL_ . "Public/");


require_once _DIR_MODEL_. 'Utility.php';
require_once _DIR_MODEL_. 'Database.php';
require_once _DIR_MODEL_. 'Session.php';
require_once _DIR_MODEL_ . 'Enum.php';

require_once _DIR_MODEL_.'User.php';
require_once _DIR_MODEL_.'Profile.php';
require_once _DIR_MODEL_.'Relationship.php';
require_once _DIR_MODEL_.'Friendship.php';
require_once _DIR_MODEL_.'Post.php';
require_once _DIR_MODEL_.'Comment.php';
require_once _DIR_MODEL_.'Showcase.php';
require_once _DIR_MODEL_.'Notify.php';

require_once _DIR_CONTROLLER_ .  'PageController.php';
require_once _DIR_TEMPLATE_.'MenageTemplate.php';
MenageTemplate::addJavascript("Config/Config.js");

?>