/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import cifrario.CifrarioCesare;
import cifrario.CifrarioSostituzione;
import cifrario.CryptSystem;
import cifrario.Metodo_Criptaggio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.ir.Flags;
import static model.Database.conn;

/**
 *
 * @author giuseppe
 */



public class Messaggio {


    
    public Integer Id;
    public String Testo;
    public String Cifrato;
    public Metodo_Criptaggio CryptMethod;
    public String Lingua;
    public String Sender;
    public String Receiver;
//    public boolean IsRead;
    public MessageFlag Flag;
    public String metakey;
    
    public CifraturaOptions Informazioni_Cifratura;
    
//    public Messaggio(String testo)
//    {
//        this(-1,testo,"",Metodo_Criptaggio.NESSUNO.name(),"","","",null);
//    }
//    
//    public Messaggio(String testo, String cifrato, String metodo_criptaggio, String lingua, String sender, String receiver)
//    {
//        this(-1,testo,cifrato,metodo_criptaggio,lingua,sender,receiver, null);
//    }
//    
//    public Messaggio(Integer id, String testo, String cifrato, String metodo_criptaggio, String lingua,String sender, String receiver, Integer flag)
//    {
//        this(id,testo,cifrato,metodo_criptaggio,"",lingua,sender,receiver, null);
//    }
//   
    public Messaggio( String testo, String metodo_criptaggio, String lingua,String sender, String receiver)
    {
         this(-1,testo,"",metodo_criptaggio,"",lingua,sender,receiver, 0);
    }
    
    public Messaggio( String testo, String cifrato, String metodo_criptaggio, String metakey, String lingua,String sender, String receiver, Integer flag)
    {
         this(-1,testo,cifrato,metodo_criptaggio,"",lingua,sender,receiver, null);
    }
    
    public Messaggio(Integer id, String testo, String cifrato, String metodo_criptaggio, String metakey, String lingua,String sender, String receiver, Integer flag)
    {
        Id=id;
        Testo=testo;
        Cifrato=cifrato;
        CryptMethod= Metodo_Criptaggio.valueOf(metodo_criptaggio);
        this.metakey=metakey;
        Lingua=lingua;
        Sender=sender;
        Receiver=receiver;
        if(flag == null ) 
            Flag= new MessageFlag();
        else
            Flag = new MessageFlag(flag);
        Informazioni_Cifratura=new CifraturaOptions();
        if("".equals(Cifrato))
            Cifra();
        
    }
    
    
    /**
     * Aggiunge un nuovo messaggio al DB ottenuto dalle seguenti info
     * @param Testo
     * @param Cifrato
     * @param CryptMethod
     * @param Lingua
     * @param Sender
     * @param Receiver
     * @param IsRead
     * @param metakey 
     */
    public static void AddMessaggio(String Testo,String Cifrato, String CryptMethod, String Lingua, String Sender, String Receiver, MessageFlag Flag, String metakey)
    {
        try {
            PreparedStatement pr=conn.prepareStatement("INSERT INTO MESSAGGIO (TESTO,CIFRATO,METODO_CRIPTAGGIO,LINGUA,SENDER,RECEIVER,FLAG,METAKEY) VALUES (? , ? , ? , ? , ? , ? , ? , ?)");
            pr.setString(1, Testo);
            pr.setString(2, Cifrato);
            pr.setString(3, CryptMethod);
            pr.setString(4, Lingua);
            pr.setString(5, Sender);
            pr.setString(6, Receiver);
            pr.setInt(7, Flag.getInt() );
            pr.setString(8, metakey);
            
            pr.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Messaggio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
   
    }
   
    /**
     * Metodo di inserimento nel DB
     */
    public void Insert()
    {
        Messaggio.AddMessaggio(Testo, Cifrato, CryptMethod.name(), Lingua, Sender, Receiver, Flag, metakey);

    }
    
    /**
     * Metodo che cifra il messaggio con il metodo scelto.
     * I risultati verranno salvati nelle variabili interne.
     */
    public void Cifra()
    {
        if(Informazioni_Cifratura.InfoMethod == null )
        {
            metakey="";
            Cifrato="";
            return;
        }
       metakey =  Informazioni_Cifratura.InfoMethod.GenerateKey();
       Cifrato = Informazioni_Cifratura.InfoMethod.Crypt(Testo.toLowerCase());
    }
 
    /**
     * Metodo che restituisce l'id dell' ultimo messaggio nel DB
     * @return l'ID dell' ultimo messaggio
     */
     public static int GetLastID()
     {
        return Database.GetLastValue("MESSAGGIO", "ID");
     }
    
     public static ArrayList<ProxyMessage> GetRequestMessage(Utente reader, int limit)
     {
         ArrayList<Messaggio> temp = GetMessageList(reader,limit);
         ArrayList<ProxyMessage> list = new ArrayList<ProxyMessage>();
         for(Messaggio mess : temp)
         {
             if(mess.IsRequested())
                 list.add((ProxyMessage.GetProxy(mess)));
         }
         
         return list;
     }
     
     
             
             
     public static ArrayList<ProxyMessage> GetProposte(Utente reader, int limit)
     {
         ArrayList<Messaggio> temp = GetMessageList(reader,limit);
         ArrayList<ProxyMessage> list = new ArrayList<ProxyMessage>();
         for(Messaggio mess : temp)
         {
             if(mess.IsAccepted())
                 list.add(ProxyMessage.GetProxy(mess));
         }
         
         return list;
     }
     
     /**
      * 
      * @param reader Utente che legge
      * @param limit limite massimo di messaggi da ricevere
      * @return Lista del messaggio
      */
     public static ArrayList<Messaggio> GetMessageList(Utente reader, int limit)
     {

        ArrayList<Messaggio> list= new  ArrayList<Messaggio>();
        
        try {    
            PreparedStatement pr=conn.prepareStatement("SELECT * FROM MESSAGGIO \n" +
                    "WHERE  RECEIVER = ? \n" +
                    "ORDER BY FLAG ASC,ID\n" +
                    "LIMIT ?");
            pr.setString(1, reader.Username);
            pr.setInt(2, limit);
            pr.execute();
            ResultSet rs = pr.getResultSet();
            while(rs.next())
            {
                list.add(new Messaggio(rs.getInt("ID"), rs.getString("TESTO") , rs.getString("CIFRATO") , rs.getString("METODO_CRIPTAGGIO") ,rs.getString("METAKEY")  ,rs.getString("LINGUA") , rs.getString("SENDER") , rs.getString("RECEIVER") , rs.getInt("FLAG")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Messaggio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
     }
     
     /**
      * Setta un messaggio come già letto
      * @param MessageID Id del messaggio da settare
      */
    public static void ReadMessage(Messaggio mess) {
        try {
            mess.SetRead();
            PreparedStatement pr=conn.prepareStatement("UPDATE MESSAGGIO SET FLAG = ? WHERE ID = ? ");
            pr.setInt(1, mess.Flag.getInt() );
            pr.setInt(2, mess.Id);
            pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Messaggio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }
    
    public void UpdateFlag()
    {
         try {
            PreparedStatement pr=conn.prepareStatement("UPDATE messaggio SET `FLAG` = ? WHERE `ID` = ?;");
            pr.setInt(1, Flag.getInt());
            pr.setInt(2, Id);
            pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Messaggio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * 
     * @param id
     * @return Messaggio restituisce il messaggio in base al suo ID
     */
    public static Messaggio GetMessaggioById(int id)
    {
        try {
            PreparedStatement pr=conn.prepareStatement("SELECT * FROM MESSAGGIO WHERE  ID = ?");
            pr.setInt(1, id);
            pr.executeQuery();
            ResultSet rs=pr.getResultSet();
            if(rs.next())
            {
               return new Messaggio(rs.getInt("ID"), rs.getString("TESTO") , rs.getString("CIFRATO") , rs.getString("METODO_CRIPTAGGIO") ,rs.getString("METAKEY")  ,rs.getString("LINGUA") , rs.getString("SENDER") , rs.getString("RECEIVER") , rs.getInt("FLAG"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Messaggio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    /**
     * Crea la tabella MEssaggio nel DB
     */
    public static void CreateTable()
    {
        Database.CreateTable("create table IF NOT EXISTS MESSAGGIO"+
                            "("+
//                                "ID INTEGER not null primary key GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),"+
                                "ID INT not null AUTO_INCREMENT ,"+
                                "TESTO VARCHAR(250),"+
                                "CIFRATO VARCHAR(250),"+
                                "METODO_CRIPTAGGIO VARCHAR(30),"+
                                "LINGUA VARCHAR(30),"+
                                "SENDER VARCHAR(32),"+
                                "RECEIVER VARCHAR(32),"+
                                "ISREAD BOOLEAN default FALSE not null,"+
                                "PRIMARY KEY(ID)"+
                            ")");
    }

    public boolean IsRead() {
       return Flag.Letto;
    }

    public void SetRead() {
        Flag.Letto=true;
//        Flag.setRead();
        UpdateFlag();
    }

    private boolean IsRequested() {
        return !Flag.Accettato && !Flag.Rifiutato && !Flag.Letto;
    }

    private boolean IsAccepted() {
        return Flag.Accettato &&!Flag.Rifiutato || Flag.Letto;
    }

    public void SetRefuse() {
        Flag.Accettato = false;
        Flag.Rifiutato = true;
        UpdateFlag();
    }

    public void SetAccepted() {
        Flag.Accettato = true;
        Flag.Rifiutato = false;
        UpdateFlag();
    }

    public Integer GetFlagInteger() {
        return Flag.getInt();
    }
    
    
    /**
     * Classe contenente tutte le informazioni per la cifratura
     */
    protected class CifraturaOptions {
        
        /**
         * Metodo di Cifratura scelto.
         * Da qui è possibile accedere a tutte le opzioni necessarie per cifrare
         * e decifrare il messaggio
         */
        public CryptSystem InfoMethod;
        
        /**
         * Setta InfoMethod a seconda del metodo di cifratura scelto
         */
        public CifraturaOptions()
        {
            if(CryptMethod ==  Metodo_Criptaggio.CIFRARIO_DI_CESARE)
            {
                if(metakey != null && !metakey.equals(""))
                    InfoMethod = new CifrarioCesare(metakey);
                else
                    InfoMethod = new CifrarioCesare(); 
            }
            else if(CryptMethod ==  Metodo_Criptaggio.SOSTITUZIONE)
            {
                if(metakey != null && !metakey.equals(""))
                    InfoMethod = new CifrarioSostituzione(metakey);
                else
                    InfoMethod = new CifrarioSostituzione(); 
            }
            
            
        }
    }
    
    
    public static Messaggio GetRandomMessageExluseUser(String user)
    {
        try {
            PreparedStatement pr = conn.prepareStatement("select * from MESSAGGIO  \n" +
                    "WHERE\n" +
                    "SENDER != ? AND \n" +
                    "RECEIVER != ?\n" +
                    "ORDER BY RAND() LIMIT 0,1");
            pr.setString(1, user);
            pr.setString(2, user);
            pr.execute();
            ResultSet rs= pr.getResultSet();
            if(rs.next())
            {
                return new Messaggio(rs.getInt("ID"), rs.getString("TESTO") , rs.getString("CIFRATO") , rs.getString("METODO_CRIPTAGGIO") ,rs.getString("METAKEY")  ,rs.getString("LINGUA") , rs.getString("SENDER") , rs.getString("RECEIVER") , rs.getInt("FLAG"));
            }
            else
                throw new Exception("Nessun Messaggio valido salvato nel db");

        } catch (SQLException ex) {
            Logger.getLogger(Messaggio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Messaggio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    

    
    private class MessageFlag
    {
        public boolean Bozza;
        public boolean Accettato;
        public boolean Rifiutato;
        public boolean Letto;
        
        private int flag;
        public MessageFlag()
        {
            Bozza=false;
            Accettato = false;
            Rifiutato = false;
            Letto = false;
            flag=0;
        }
        
        
        public MessageFlag(Integer flag)
        {
            Bozza = ( (flag & 1) == 1 );
            Accettato = ((flag & 2) == 2 );
            Rifiutato = ((flag & 4) == 4 );
            Letto =  ((flag & 8) == 8 );
            flag=getInt();
        }
        
        public String toSring()
        {
            int ris=0;
            if(Bozza) ris=ris | 1;
            if(Accettato) ris = ris | 2;
            if(Rifiutato) ris = ris | 4;
            if(Letto) ris = ris | 8;
            return String.valueOf(ris);
        }
        
        public Integer getInt()
        {
            int ris=0;
            if(Bozza) ris=ris | 1;
            if(Accettato) ris = ris | 2;
            if(Rifiutato) ris = ris | 4;
            if(Letto) ris = ris | 8;
            return ris;
       
        }

       
        
        public void Update()
        {
            Bozza = ( (flag & 1) == 1 );
            Accettato = ((flag & 2) == 2 );
            Rifiutato = ((flag & 4) == 4 );
            Letto =  ((flag & 8) == 8 );
        }
    }
}



