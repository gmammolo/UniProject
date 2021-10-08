/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cifrario;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;


/**
 *
 * @author Giuseppe
 */
public class CifrarioSostituzione extends CryptSystem {
    
    protected Map KeyCrypt;
    protected Map KeyDecrypt;
    
    public CifrarioSostituzione()
    {
        KeyCrypt = new Hashtable();
        KeyDecrypt = new Hashtable();
    }

    public CifrarioSostituzione(String metakey)
    {
        super(metakey);
    }
    
    @Override
    public String Crypt(String s) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return CryptDecrypt(s,KeyCrypt);
    }

    @Override
    public String Decrypt(String s) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return CryptDecrypt(s,KeyDecrypt);
    }
    
    private String CryptDecrypt(String s, Map Key)
    {
          String result = "";
          for ( int i = 0 ; i < s.length() ; i++ ) {
              char el = s.charAt(i);
              if(Key.containsKey(el))
              {
                  el=(char)Key.get(el);
              }
              result+=el;
          }
          return result;
    }

    @Override
    public String GenerateKey() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        GenerateCryptKey();
        return Serialize();
    
    }
    
    
    /**
    * Returns a pseudo-random number between min and max, inclusive.
    * The difference between min and max can be at most
    * <code>Integer.MAX_VALUE - 1</code>.
    *
    * @param min Minimum value
    * @param max Maximum value.  Must be greater than min.
    * @return Integer between min and max, inclusive.
    * @see java.util.Random#nextInt(int)
    */
   private static int randInt(int min, int max) {

       // NOTE: Usually this should be a field rather than a method
       // variable so that it is not re-seeded every call.
       Random rand = new Random();

       // nextInt is normally exclusive of the top value,
       // so add 1 to make it inclusive
       int randomNum = rand.nextInt((max - min) + 1) + min;

       return randomNum;
   }
    
    private void GenerateCryptKey()
    {
        String alph = alfabeto;
        int tmpleng = length-1;
//        if(tmpleng % 2 != 0)
//        {
//            KeyCrypt.put(alph.charAt(tmpleng), alph.charAt(tmpleng));
//            KeyDecrypt.put(alph.charAt(tmpleng), alph.charAt(tmpleng));
//            tmpleng--;
//        }
        for(int i=0;i<length;i++)
        {
            int randomValue = randInt(0,tmpleng);
            //genera chiave
            KeyCrypt.put(alfabeto.charAt(i), alph.charAt(randomValue));
            KeyDecrypt.put(alph.charAt(randomValue), alfabeto.charAt(i));
//            char tmp= alph.charAt(randomValue);
            //sposta a fine stringa e riduce array
            alph= alph.replace(alph.charAt(randomValue),alph.charAt(tmpleng));
            tmpleng--;
//            System.out.println("PRENDO "+tmp+" QUINDI RIMANE "+alph.substring(0,tmpleng));
            
        }

    }
    
    public String Serialize()
    {
        String result="";
        for (Object key : KeyCrypt.keySet())
        {
            result+=String.valueOf(key)+String.valueOf(KeyCrypt.get(key));
        }
        return result;
    }
    
    public void Deserialize(String metakey)         
    {
        KeyCrypt = new Hashtable();
        KeyDecrypt = new Hashtable();
        
        for(int i=0; i< metakey.length(); i+=2)
        {
            int j=i+1;
            KeyCrypt.put(i, j);
            KeyDecrypt.put(j, i);
        }
        
    }
    
}
