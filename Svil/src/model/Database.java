/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author giuseppe
 */
public class Database {
    

    public static Connection conn;
    protected static Statement s ;
    
    
    
    /**
     * Metodo che inizializza la connessione al Db
     */
    public static void Initializate()
    {
        try {
            conn= InitializateMysql();
        } catch (Exception ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Connection InitializateMysql() throws Exception
    {
//            String driver = "org.gjt.mm.mysql.Driver";
            String driver = "com.mysql.jdbc.Driver";
            
            String url = "jdbc:mysql://localhost/sviluppo";
            String username = "root";
            String password = "";

            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, username, password);
            return conn;
        
    }
    
    

    public static Connection InitializateDerby() throws Exception
    {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        return DriverManager.getConnection("jdbc:derby://localhost/Svil;create=true;user=APP;pass=APP");
    }
    
    /**
     * La connessione rimane aperta in modo statico finchè non verrà chiusa
     * tramite questo metodo
     */
    public static void Close()
    {
         try {
            s.close();
            conn.close();
        } catch (Exception e) {
            System.err.println("Error During Close");
            System.exit(0);
        }
    }

    
    /**
     * Metodo Privato per l'inserimento delle tabelle. Genera la stringa delle colonne
     * per creare una tabella nel db
     * @param column array di stringhe contenete il nome delle colonne da inserire
     * @return 
     */
    private static String GetColumnToString(String[] column)
    {
        if(column == null || column.length == 0)
            return "";
        String ris="("+column[0];
        for(int i=1; i< column.length;i++)
        {
            ris+=(", ");
            ris+=column[i];
        }
        ris+=") " ;
        return ris;
        
    }
    
    /**
     * Metodo che crea una nuova tabella nel DB
     * @param sql Stringa della query per creare la tabella
     * @return true in caso di successo, false in caso negativo 
     */
    public static boolean CreateTable(String sql)
    {
        try{
            Statement s = conn.createStatement();
            s.execute(sql);
  
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        
        return true;
    }
   
  /**
   * @return ArrayList&lt;Utente&gt; Ritorna la lista di tutti gli utenti che esistono
   * nel db.
   */  
  public static ArrayList<Utente> GetAllUtenti()
  {
    ResultSet rs=  GetAllTuple("Utente");
    ArrayList<Utente> list= new  ArrayList<Utente>();
    try{
        while (rs.next()) {
              //System.out.println("Derby says: "+rs.getString("Username")+" - "+rs.getString("Password"));
            list.add(new Utente(rs.getString("Username"),rs.getString("Password")));
        
        }
    } catch (SQLException ex) {
            ex.printStackTrace();
    }
    
    return list;
           
  }
    

  /**
   * Metodo Privato: Ritorna tutte le tuple di una determinata tabella
   * @param Tables tabella in cui cercare le tuple
   * @return ResultSet con il risultato o null in caso di fallimento
   */
  private static ResultSet GetAllTuple(String Tables)
  {
      try{
          s.execute("SELECT * FROM "+Tables);
          ResultSet rs = s.getResultSet();
          //while (rs.next()) {
         //     System.out.println("Derby says: "+rs.getString("Username")+" - "+rs.getString("Password"));
         // }
        return rs;
      } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
      }
  }
  
    
   
  
    /**
     * Metodo utilizzato per sopperire alla mancanza dell' autoincrement.
     * @param Table Nome della tabella
     * @param column Nome della colonna di Integer
     * @return Valore più grande della colonna
     */
    public static int GetLastValue(String Table, String column)
    {
        try{
          //System.out.println("SELECT MAX("+column+") as NUM FROM "+Table+ " GROUP BY 1"); //non so perchè vuole 1 anzichè column  
          s.execute("SELECT MAX("+column+") as NUM FROM "+Table+ " GROUP BY 1");
          ResultSet rs = s.getResultSet();
          if(rs.next())
          {
               //int num=Integer.parseInt(rs.getString("NUM"))+1;
              int num=rs.getInt("NUM")+1;
               return num;
          }
          else
              return 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }
    

    
//    /**
//     * Restituisce la lista dei messaggi 
//     * @param reader Username dell' utente richiedente
//     * @param limit numero massimo di messaggi da leggere
//     * @return ArrayList&lt;Messaggio&gt; Lista messaggi
//     */
//    public static ArrayList<Messaggio> GetMessageList(Utente reader, int limit)
//    {
//        ArrayList<Messaggio> list= new  ArrayList<Messaggio>();
//        try{
//            //System.out.println("SELECT MAX("+column+") as NUM FROM "+Table+ " GROUP BY 1"); //non so perchè vuole 1 anzichè column  
//            s.execute("SELECT * FROM MESSAGGIO \n" +
//                      "WHERE  RECEIVER = '"+reader.Username+"' \n" +
//                      "ORDER BY ISREAD ASC,ID\n" +
//                      "FETCH FIRST "+limit+" ROWS ONLY");
//            ResultSet rs = s.getResultSet();
//
//            while(rs.next())
//            {
//                list.add(new Messaggio(rs.getInt("ID"), rs.getString("TESTO") , rs.getString("CIFRATO") , rs.getString("METODO_CRIPTAGGIO") ,rs.getString("METAKEY")  ,rs.getString("LINGUA") , rs.getString("SENDER") , rs.getString("RECEIVER") , rs.getBoolean("ISREAD")));
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return list;
//    }
    
    /**
     * Valida il testo inserito, ovvero rimuove i caratteri non accettabili
     * @param value testo potenzialmente non sicuro
     * @return testo sicuro
     */
    public static String Validate(String value)
    {
        return value;
    }


    /**
     * Trasforma un Oggetto che implementa un Serializable in un array di byte
     * @param obj L'oggetto deve esserere Serializabile
     * @return byte[] 
     * @throws IOException 
     */
    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream o = new ObjectOutputStream(b);
        o.writeObject(obj);
        return b.toByteArray();
    }

    /**
     * Riconverte un'array di byte in un oggetto Serializzabile
     * @param bytes byte[] 
     * @return
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream b = new ByteArrayInputStream(bytes);
        ObjectInputStream o = new ObjectInputStream(b);
        return o.readObject();
    }
}
    