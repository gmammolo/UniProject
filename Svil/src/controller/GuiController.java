/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import cifrario.Metodo_Criptaggio;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import model.AlberoIpotesi;
import model.Database;
import model.Dizionario;
import model.Messaggio;
import model.Sessione;
import model.Utente;
import view.ChooseFrame;
import view.ChooseSession;
import view.DizionarioGui;
import view.FrequenzeGui;
import view.LoginForm;
import view.ReadMessage;
import view.ReadMessages;
import view.SendMessage;
import view.SessioneGui;
import view.Storico;

/**
 *
 * @author Giuseppe
 */
public class GuiController {
    
    public static void LoginController(JTextField Username , JPasswordField Password, JLabel StatusMessage, LoginForm Loginform )
    {
        /*
            NOTA: a causa del suo status di progetto a scopo dimostrativo,
            Il field password sarà convertito in una stringa e trattato come
            una normale stringa.
        */
        String pass= "";
        for(char c :  Password.getPassword() )
        {
            pass+=String.valueOf(c);
        }
        
        
        if(Utente.CheckUtente(Username.getText(), pass ))
        {
            StatusMessage.setText("Login Avvenuto con Successo!");
            JFrame frame= new ChooseFrame(Utente.GetUtente(Username.getText(), pass ),Loginform);
            frame.setVisible(true);
            
            
        }
        else
        {
            StatusMessage.setText("Login Fallito!");
        }
    }
    
    public static String NotImplementYet()
    {
        return "OPS, Pare che questo tasto non sia stato implementato in questa versione";
    }
    
    
    
    public static void Logout(JFrame Parent, JFrame Son )
    {
        UndoFrame(Parent, Son);
    }
    
    public static void UndoFrame(JFrame Parent, JFrame Son )
    {
        Parent.setVisible(true);
        Parent.pack();
        Son.dispose();
    }
    
    public static void RispondiMessaggio(Utente user, JFrame This, String Destinatario)
    {
        This.setVisible(false);
        JFrame sendM = new SendMessage(user,Destinatario,(ChooseFrame)This);
        sendM.setVisible(true);
    }
    
    public static void InoltraMessaggio(Utente user, JFrame This, String Testo)
    {
        This.setVisible(false);
        JFrame sendM = new SendMessage(user,(ChooseFrame)This,Testo);
        sendM.setVisible(true);
    }
    
    public static void NewMessage(Utente user, JFrame This)
    {
        This.setVisible(false);
        JFrame sendM = new SendMessage(user,(ChooseFrame)This);
        sendM.setVisible(true);
    }
    
    public static void GoReadMessage(Utente user, JFrame This)
    {
        This.setVisible(false);
        JFrame readM= new ReadMessages(user,(ChooseFrame)This);
        readM.setVisible(true);
    }
    
    public static void GoReadMessage(Utente User,Messaggio Mess,ReadMessages This, ChooseFrame Parent)
    {
        SetReadMessage(Mess);
        This.setVisible(false);
        JFrame readM= new ReadMessage(User,Mess,Parent);
        readM.setVisible(true);
        
    }
    
    protected static void SetReadMessage(Messaggio mess)
    {
            Messaggio.ReadMessage(mess);
            
    }
    
    
    public static void SendMessage(JTextField Destinatario, JLabel Status, JComboBox MetodoCriptaggio, JTextArea Testo, JComboBox Linguaggio, Utente Sender, ChooseFrame Parent, SendMessage This)
    {
        String Dest = ( Utente.CheckUtente(Destinatario.getText()) ) ? Destinatario.getText() : "";
        if(Dest.equals(""))
        {
            Status.setText("Attenzione: Destinatario non accettabile");
            return;
        }

        Metodo_Criptaggio method= Metodo_Criptaggio.NESSUNO;
        switch ((String)MetodoCriptaggio.getSelectedItem())
        {
            case "Sostituzione":
                method= Metodo_Criptaggio.SOSTITUZIONE;
                break;
            case "Cifrario di Cesare":
                method= Metodo_Criptaggio.CIFRARIO_DI_CESARE;
                break;
            default:
                method= Metodo_Criptaggio.NESSUNO;
                break;
        }
        new Messaggio(Testo.getText() ,  method.name(), (String)Linguaggio.getSelectedItem(), Sender.Username, Dest).Insert();
        Parent.sendMessage("Messaggio inviato con successo");
        Status.setText("Messaggio Inviato con successo");
        //chiudere
        Parent.setVisible(true);
        Parent.pack();
        This.dispose();
        
    }
    
    
    public static void NewSessionRandom(ChooseSession Parent)
    {
        Messaggio m;
        try {
            m= Messaggio.GetRandomMessageExluseUser(Parent.User.Username);
        } catch (Exception ex) {
            Logger.getLogger(GuiController.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        JFrame fr=new SessioneGui(new Sessione(0,m,Parent.User));
        Parent.dispose();
        fr.setVisible(true);
    }

    public static void LoadSession(ChooseSession aThis, String Codename) {
        Sessione sess=Sessione.LoadSessione(Codename,aThis.User.Username);
        if(sess==null)
        {
            JOptionPane.showMessageDialog(null,"Attenzione, Non è stata trovata nessuna Sessione con quel nome");
            return;
        }
        JFrame fr=new SessioneGui(sess);
        aThis.dispose();
        fr.setVisible(true);
//        JOptionPane.showMessageDialog(null,sess.Storico.toString());
    }

    public static void GoDecriptSession(Utente user, ChooseFrame aThis) {
        aThis.dispose();
        JFrame fr= new ChooseSession(user);
        fr.setVisible(true);
    }

    public static void AddKeyComponent(SessioneGui aThis,String varA , String varB) {
        char a,b;
        if(varA.length()==1 )
            a=varA.charAt(0);
        else return;
        if(varB.length()==1)
            b=varB.charAt(0);
        else return;
        //aThis.Session.Storico.AddNewNode(a, b);
        GestoreIpotesi.SostituisciLettera(aThis.Session,a,b);
        aThis.UpdateSession(aThis.Session.Ipotesi);
    }

    public static void SaveSession(SessioneGui aThis, String text) {
        try {
            aThis.Session.Codename=text;
            if(aThis.Session.CheckSession())
                aThis.Session.Update();
            else
                aThis.Session.Insert();
            
            JOptionPane.showMessageDialog(null,"Salvataggio effettuato con successo");
        } catch (SQLException ex) {
            Logger.getLogger(GuiController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Salvataggio Fallito");
        }
        
    }

    public static void OpenDizionario(SessioneGui aThis, JTextPane Cifrato) {
       
        String s="";
        if (Cifrato.getSelectedText() != null){ 
            s = Cifrato.getSelectedText();
        }
        aThis.setVisible(false);
        JFrame fr=new DizionarioGui(aThis,s);
        fr.setVisible(true);
    }

    public static ArrayList<String> SearchParola(String text) {
       return SupportSystem.chiediParola(text);
    }

    public static void AccettaVocabolo(DizionarioGui aThis, String old, String find) {
       SessioneGui Parent= aThis.Parent;
       for(int i=0;i<old.length();i++)
       {
           char a=old.charAt(i);
           char b=find.charAt(i);
           if(a >= 'a' && a <= 'z')
           {
               Parent.Session.addKey(a, b);
           }
       }
       Parent.UpdateSession(Parent.Session.Ipotesi);
       Parent.setVisible(true);
       aThis.dispose();
       
    }

    public static void ShowStorico(SessioneGui parent) {
//        parent.setVisible(false);
        JFrame fr = new Storico(parent);
        fr.setVisible(true);
        
    }

    public static void CheckFreq(String testo) {
        JFrame fr= new FrequenzeGui(testo);
        fr.setVisible(true);
    }
    

}
