/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cifrario;

import java.util.* ;
public class CifrarioCesare extends CryptSystem {

    
    protected int Key;
    
    public CifrarioCesare()
    {
        
    }
    
    
    public CifrarioCesare(String metakey)
    {
        super(metakey);
    }
    
    @Override
    public String GenerateKey() {
        GenerateKeyCesare();
        return Serialize();
    }
    
    
    protected void GenerateKeyCesare()
    {
        Key = 1 + (int)(Math.random()*length); 
    }
    
    @Override
    public String Crypt( String s ) 
    {
        return Crypt(  s , Key );
    }
    
    public String Crypt( String s , int chiave ) {

                String stringa = new String("");
                int index ;
                int j = s.length();
                char at ;
                for ( int i = 0 ; i < j ; i++ ) {
                    at = s.charAt(i);
                    index = alfabeto.indexOf(at);
                    if(index == -1)
                        stringa = stringa + at ;
                    else
                    {
                        at = alfabeto.charAt( (index+chiave) % length );
                        stringa = stringa + at ;
                    }
               }
               return stringa ;
       }
    
    
    public String Decrypt( String s) 
    {
        return Decrypt(  s , Key );
    }
    
    public String Decrypt(String s , int chiave)
    {
        String stringa = new String("");
        int index ;
        int j = s.length();
        char at ;
        for ( int i = 0 ; i < j ; i++ ) {
            at = s.charAt(i);
            index = alfabeto.indexOf(at);
            if(index == 1)
                stringa = stringa + at ;
            else
            {
                at = alfabeto.charAt( (index+length-chiave) % length );
                stringa = stringa + at ;
            }         
        }
        return stringa;
    }

    @Override
    public String Serialize() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return String.valueOf(Key);
    
    }

    @Override
    public void Deserialize(String Metakey) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            Key=Integer.parseInt(Metakey);
        }
        catch(NumberFormatException e)
        {
            System.out.println(e.getMessage());
        }
    }
       
       

}