/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package message;

import model.Database;
import model.Utente;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import javax.swing.JLabel;

/**
 *
 * @author Giuseppe
 */
public class TimeMessage extends TimerTask  {

    Utente User;
    JLabel Mess;

    public TimeMessage(Utente User,JLabel mess)
    {
       this.User=User;
       this.Mess=mess;
    }

    //public TimeMessage(String Username) {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   // }
    
    public void run(){

        checkMessage();
        //System.out.println("Thread: " + Name + " - " + getData());

    }
    
//    private String getData(){
//        return "cagnana";
//    }
    
    private void checkMessage()
    {
        Mess.setText("Ci sono "+User.CheckMessage()+" ancora non letti");
            
        
    }
}


