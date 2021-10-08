/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static model.Database.conn;

/**
 *
 * @author Giuseppe
 */
public class Dizionario {
    /*
     *Derby Wildcard:
     * % più caratteri
     * _ un solo carattere
     */

    public Dizionario()
    {
        
    }
    
    public static void AddParola(String Parola)
    {
        try {
            System.out.println(Parola);
            PreparedStatement pr= Database.conn.prepareStatement("INSERT INTO sviluppo.dizionario (`PAROLA`) \n" +
                    "	VALUES (?)\n" );
            pr.setString(1, Parola);
            pr.execute();
            //Database.conn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(Dizionario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public static void CreateTable()
    {

        Database.CreateTable("create table IF NOT EXISTS DIZIONARIO\n" +
                                "(\n" +
                                "PAROLA VARCHAR(30) not null," +
                                " primary key(PAROLA)\n" +
                                ")"
              );
    }
    
    
    public static void GenerateFromTxt(String url) throws FileNotFoundException, IOException
    {
        //C:\Users\Giuseppe\Documents\NetBeansProjects\ProjectSvil\DbTable
        
        FileReader f = new FileReader(url);
        BufferedReader b =new BufferedReader(f);
        String p="";
        while(( p = b.readLine()) != null)
        {
            AddParola(p.toUpperCase());
        }
    }
    
    public static ArrayList<String> searchParola(String s)
    {
        ArrayList<String> list= new  ArrayList<String>();
        try {
            PreparedStatement pr = conn.prepareStatement("SELECT * FROM DIZIONARIO WHERE  PAROLA LIKE ?");
            pr.setString(1, s);
            pr.execute();
            ResultSet rs= pr.getResultSet();
            while(rs.next())
            {
                list.add(rs.getString("PAROLA"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dizionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
