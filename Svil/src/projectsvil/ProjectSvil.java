/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projectsvil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import model.AlberoIpotesi;
import model.Database;
import model.Dizionario;
import model.ListaIpotesi;
import model.Messaggio;
import model.Sessione;
import model.Utente;
import view.LoginForm;

/**
 *
 * @author giuseppe
 */
public class ProjectSvil {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        // TODO code application logic here
        
        //MAIN PROJECT
        
        
        
        Database.Initializate();
        //createTable();
        //System.exit(0); 
        
        
        
//   Metodi da scommentare solo alla configurazione (Primo avvio)
//        createTable();
//        createUser();

        JFrame login=new LoginForm();
        login.setVisible(true);
        
        //Utente.AddUtente("Pluto", "pass");
        
        
       
        
        //System.exit(0); 
        
    }
    
    
    public static void createTable()
    {
        Messaggio.CreateTable();
        Utente.CreateTable();
        Sessione.CreateTable();
        Dizionario.CreateTable();
        
        //Carica Dizionario
        try {
            Dizionario.GenerateFromTxt("C:\\Users\\Giuseppe\\Documents\\NetBeansProjects\\ProjectSvil\\DbTable\\italiano.txt");
        } catch (IOException ex) {
            Logger.getLogger(ProjectSvil.class.getName()).log(Level.SEVERE, null, ex);
        }
       System.out.println("Finito!");
       System.exit(0);
    }
    
    
    public static void createUser()
    {
        Utente.AddUtente("Sender", "Pass");
        Utente.AddUtente("Receiver", "Pass");
        Utente.AddUtente("Spy", "Pass");
    }
    
    public static void createMessage()
    {
//        INSERT INTO MESSAGGIO (TESTO, CIFRATO, METODO_CRIPTAGGIO, LINGUA, SENDER, RECEIVER, ISREAD, METAKEY)   VALUES ('primo messaggio da mandare come prova','vaqsu smbbgooqu lg sgtlgam iusm vaueg','CIFRARIO_DI_CESARE','Italiano','Sender','Receiver',false,'6')
//        INSERT INTO MESSAGGIO (TESTO, CIFRATO, METODO_CRIPTAGGIO, LINGUA, SENDER, RECEIVER, ISREAD, METAKEY)   VALUES ('Guarda qui che bello, il seocndo messaggio come arriva','msgmmg gsi mfc tcnnm, in tcmmfmm icttgmmim mmic gmmimg','SOSTITUZIONE','Italiano','Sender','Receiver',false,'zavmusstrmqgpgomnfmilniihfgmftecdmcmbtag')

    }
    
}
