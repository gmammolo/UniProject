/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;


import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static model.Database.conn;

/**
 *
 * @author Giuseppe
 */
public class Sessione {


    public Integer Id;
    public Messaggio Mess;
    public Utente User;
    public String Codename;
    //public Blob MetaKey;
//    public Map Key;
    public ListaIpotesi Ipotesi;
    public AlberoIpotesi Storico;
    
    public Sessione(int id, int messId, String userName)
    {
        this(id, Messaggio.GetMessaggioById(messId),Utente.GetUtente(userName) , null);
    }
    
    public Sessione(int id, int messId, String userName, Object Albero)
    {
        this(id, Messaggio.GetMessaggioById(messId),Utente.GetUtente(userName) , Albero);
    }
    
    public Sessione(int id, int messId, Utente user)
    {
        this(id, Messaggio.GetMessaggioById(messId),user , null);
    }
    
    public Sessione(int id, int messId, Utente user, Object Albero)
    {
        this(id, Messaggio.GetMessaggioById(messId),user , Albero);
    }
    
    public Sessione(int id, Messaggio mess, Utente user)
    {
        this(id,mess,user,null);
    }
    
    public Sessione(int id, Messaggio mess, Utente user, Object Albero)
    {
        Id=id;
        Mess=mess;
        User=user;
        Ipotesi = new ListaIpotesi();
        Storico = new AlberoIpotesi();
        try {
            if(Albero!=null)
            {
                Storico=(AlberoIpotesi)Database.deserialize((byte[])Albero);
                Ipotesi = Storico.GetLista();
            }
        } catch (IOException ex) {
            Logger.getLogger(Sessione.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Sessione.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(Storico.toString());
        Ipotesi = Storico.GetLista();
    }
    
    public static void CreateTable()
    {
        Database.CreateTable("create table IF NOT EXISTS SESSIONE\n" +
                            "(\n" +
                            "	ID INTEGER not null AUTO_INCREMENT,\n" +
                            "	IDMESS INTEGER,\n" +
                            "	IDUSER VARCHAR(32),\n" +
                            "   CODENAME VARCHAR(20), \n" +
                            "   STORICO BLOB, \n"+ 
                            "   PRIMARY KEY (ID) \n"+
                            ")");       
    }
    
    

    
    /**
     * Aggiunge una Sessione al Db
     * @param ID
     * @param mess
     * @param userName 
     */
    public static void AddSessione(int messId,String userName,String codename, Object storico) throws SQLException
    {
            String sql= "INSERT INTO SESSIONE ( IDMESS , USERNAME , CODENAME , STORICO )  VALUES( ? , ? , ? , ?)";
            PreparedStatement ps=Database.conn.prepareStatement(sql);
            ps.setInt(1, messId);
            ps.setString(2, userName);
            ps.setString(3, codename  );
            ps.setObject(4, storico );
            ps.execute();

        
    }
    /**
     * Carica la sessione corrente nel db
     */
    public void Insert() throws SQLException
    {
        Sessione.AddSessione( Mess.Id, User.Username, Codename, Storico);
    }
    
    
    public static Sessione LoadSessione(String Codename, String User) {
        try{
            
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM SESSIONE WHERE CODENAME = ? AND USERNAME = ?");
            ps.setString(1, Codename);
            ps.setString(2, User);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if(rs.next())
            {
               return new Sessione(rs.getInt("ID"), rs.getInt("IDMESS"), rs.getString("USERNAME"),rs.getObject("STORICO"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return null;
    }
    
    
    public void addKey(char a, char b)
    {
//        Key.put(a, String.valueOf(b).toUpperCase());
        
       if(Ipotesi.AddNewNode(a, b));
            Storico.AddNewNode(a, b);
    }
    
    
    public void Undo()
    {
        Ipotesi.RemoveLastNode();
        try {        
            Storico.Undo();
        } catch (Exception ex) {
            Logger.getLogger(Sessione.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String GetTextIpotesi()
    {
        return this.Ipotesi.GetTextIpotesi(Mess.Cifrato);
    }


    public boolean CheckSession() throws SQLException {
            PreparedStatement pr = conn.prepareStatement("SELECT * FROM SESSIONE WHERE CODENAME =  ?  AND USERNAME = ?" );
            pr.setString(1, Codename);
            pr.setString(2, User.Username);
            pr.execute();
            ResultSet rs=pr.getResultSet();
            if(rs.next())
            {
               return true;
            }
        return false;
    }

    public void Update() throws SQLException {
            PreparedStatement ps = conn.prepareStatement("UPDATE SESSIONE SET STORICO = ? "
                    + "WHERE CODENAME = ? AND USERNAME = ?");
            ps.setObject(1, Storico  );
            ps.setString(2, Codename);
            ps.setString(3, User.Username);
            ps.execute();

    }
    
}
