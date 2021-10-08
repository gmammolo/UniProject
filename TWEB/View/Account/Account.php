<div class="container">
    <header>
        <h2>Welcome to SocialWork!</h2>
    </header>
    <div id="account-form"> 
        <div class="div-menu">
            <ul class="tab-ul">
                <li id="button_login" class="tab-li no-touch selected">Login</li>               
                <li id="button_join" class="tab-li" >Join</li>
            </ul>
        </div>
        <div class="tab-contents" >
            <div id="login">
                <form method="POST" name="login" class="pure-form pure-form-aligned" action="/SocialProject/index.php?Login=true">
                    <fieldset>
                        <div class="pure-control-group">
                            <label>Username</label>
                            <input type="text" name="Username" pattern="[^'\x22]+" placeholder="Username"/> 
                        </div>
                        <div class="pure-control-group">
                            <label>Password</label>
                            <input type="password" name="Password" placeholder="Password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{4,16}" title="Deve contenere almeno un carattere Maiuscolo, uno minuscolo e un numero. (4-16 caratteri)." /> 
                        </div>
                        <div class="pure-control-group">
                            <input type="submit" name="Login" value="Login" onclick="loginAction()"/>
                        </div>
                    </fieldset>
                </form>  
            </div>
            <div id="join">
                <form method="POST" name="join" class="pure-form pure-form-aligned" action="/SocialProject/index.php?Join=true">
                    <fieldset>
                        <div class="pure-control-group">
                            <label for="username">Username</label>
                            <input id="username" type="text" name="username" pattern="[^'\x22]+" placeholder="Username"/> 
                        </div>
                        <div class="pure-control-group">
                            <label for="email">Email</label>
                            <input id="email" placeholder="email@dominio.com" type="text" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" /> 
                        </div>
                        <div class="pure-control-group">
                            <label>Password</label>
                            <input placeholder="Password" type="password" name="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{4,16}" title="Deve contenere almeno un carattere Maiuscolo, uno minuscolo e un numero. (4-16 caratteri)."/> 
                        </div>
                        <div class="pure-control-group">
                            <label>Confirm Password</label>
                            <input placeholder="Password" type="password" name="cpassword" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{4,16}" title="Deve contenere almeno un carattere Maiuscolo, uno minuscolo e un numero. (4-16 caratteri)."/> 
                        </div>
                        <div class="pure-control-group">
                            <input class="pure-button pure-button-primary" type="submit" name="Join" value="Join" onclick="joinAction()"/>
                        </div>    
                    </fieldset>
                </form>
                
            </div>
        </div>
        <script>
//            $("#login").load(__LOGIN_URL__);
            $("#join").hide();
//            $("#join").load(__JOIN_URL__);
            $( "#button_login" ).on( "click", function( event ) { 
                $("#login").show(); 
                $("#join").hide(); 
                $( "#button_login" ).addClass("selected no-touch");
                $( "#button_join" ).removeClass("selected no-touch");
                
            });
            $( "#button_join" ).on( "click", function( event ) { 
                $("#join").show(); 
                $("#login").hide();  
                $( "#button_join" ).addClass("selected no-touch");
                $( "#button_login" ).removeClass("selected no-touch");
            });
        </script>   

    </div>
</div> 