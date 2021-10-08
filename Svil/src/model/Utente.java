/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import static model.Database.conn;

/**
 *
 * @author giuseppe
 */
public class Utente {
 
    public String Username;
    public String Password;
    
    public Utente()
    {
        this("","");
    }
    
    public Utente(String Username,String Password)
    {
        this.Username=Username;
        this.Password=Password;
    }
    
    /**
     * Crea la tabella UTENTE
     */
    public static void CreateTable()
    {
        Database.CreateTable("create table IF NOT EXISTS UTENTE"+
                                "("+
                                    "USERNAME VARCHAR(32) not null,"+
                                    "PASSWORD VARCHAR(32),"+
                                    "PRIMARY KEY(USERNAME)"+
                                ")");      
    }
    
    /**
     * Aggiunge un utente al Db
     * @param User
     * @param Pass_Chiaro 
     */
    public static void AddUtente(String User, String Pass_Chiaro)
    {
        try {
            PreparedStatement pr=conn.prepareStatement("INSERT INTO UTENTE (`USERNAME` , `PASSWORD` ) VALUES (? , ?)");
            pr.setString(1, User);
            pr.setString(2, Pass_Chiaro);
            pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Utente.class.getName()).log(Level.SEVERE, null, ex);
        }
                

    }
    
     /**
     * Carica la sessione corrente nel db
     */
    public void Insert()
    {
        Utente.AddUtente(Username, Password);
    }
                
    
    /**
     * Verifica la presenza di messaggi dell' utente
     * @return numero dei messaggi
     */
    public int CheckMessage()
    {
        try {
            PreparedStatement pr=Database.conn.prepareStatement("SELECT FLAG FROM MESSAGGIO WHERE RECEIVER = ? ");
            pr.setString(1, Username);
            pr.execute();
            ResultSet rs = pr.getResultSet();
            int ris=0;
            while (rs.next()) {
                //Se FLAG AND 12 XOR 12 != 0 vuol dire che il messaggio è stato già letto (2^4) oppure è stato rigiutato
                if(( rs.getInt("FLAG") & 13 ) == 0 )
                {
                    ris++;
                }
                
            }
            return ris;
        } catch (SQLException ex) {
            Logger.getLogger(Utente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    /**
     * Restituisce la Lista di tutti gli utenti
     * @return 
     */
    public static  ArrayList<Utente> GetAllUtenti()
    {
         ArrayList<Utente> list=Database.GetAllUtenti();
         return list;
        
    }
    
    /**
     * @deprecated
     * @param User
     * @param Pass_Chiaro
     * @return 
     */
    public boolean UserLogin(String User, String Pass_Chiaro)
    {
            return false;
    }
    
    
        /**
     * Verifica l'esistenza di un utente nel DB
     * @param User
     * @return true se esiste, false altrimenti
     */
    public static boolean CheckUtente(String User)
    {
        return (GetUtente(User) == null) ? false : true;
    }
    
    /**
     * Verifica l'esistenza di un utente nel DB
     * @param User
     * @param Pass_Chiaro
     * @return true se esiste, false altrimenti
     */
    public static boolean CheckUtente(String User, String Pass_Chiaro)
    {
        return (GetUtente(User, Pass_Chiaro) == null) ? false : true;
    }
    
     
    /**
     * Restituisce un utente conoscendo solo il suo nome:
     * Attenzione: non usarlo per il login, ma solo per avere informazioni secondarie
     * dell' utente. (in questo caso il db è povero di info e non avendo limiti di sicurezza
     * si può tranquillamente prendere informazioni più delicate come la password)
     * @param User
     * @return 
     */
    public static Utente GetUtente(String User)
    {
        return GetUtente(User, "");
    }
    
    
    /**
     * Restituisce l'utente con i dati insetiti
     * @param User
     * @param Pass_Chiaro facoltativa: pass dell' utente
     * @return 
     */
    public static Utente GetUtente(String User, String Pass_Chiaro)
    {
        try{
            String pass= (Pass_Chiaro.equals("") ) ? ""  : "AND PASSWORD = ? " ;
            PreparedStatement pr=Database.conn.prepareStatement("SELECT * FROM UTENTE WHERE USERNAME=? "+ pass );
            pr.setString(1, User);
            if(!pass.equals(""))
                pr.setString(2, Pass_Chiaro);
            pr.execute();
            ResultSet rs = pr.getResultSet();
            if(!rs.next())
                return null;
            else
              return new Utente(rs.getString("Username"), rs.getString("Password"));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }


}
