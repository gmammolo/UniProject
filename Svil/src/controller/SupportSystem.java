/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import model.Dizionario;

/**
 * Classe che gestisce i vari supporti.
 * Attualmente supporta solo il dizionario (italiano)
 * @author Giuseppe
 */
public class SupportSystem {
    
    
    /**
     * Manda una parola automaticamente al Dizionario Italiano
     * @param parola 
     */
    public static void inviaParola(String parola)
    {
        Dizionario.AddParola(parola);
    }
    
    /**
     * 
     * @param parola
     * @param dizionario attualmente superflup in quanto esiste solo il dizionario italiano
     */
    public static void inviaParola(String parola, String dizionario)
    {
        Dizionario.AddParola(parola);
    }
    
    /**
     * Cerca una parola nel dizionario
     * @param parola
     * @return 
     */
    public static ArrayList<String> chiediParola(String parola)
    {
       
      String ris="";
       for(int i=0; i< parola.length(); i++)
       {
           if(parola.charAt(i) >= 'A' && parola.charAt(i) <= 'Z')
               ris+=parola.charAt(i);
           else
               ris+='_';
       }
        
        return CheckLista(parola, Dizionario.searchParola(ris));
    }
    
    protected static ArrayList<String> CheckLista(String parola , ArrayList<String> list )
    {
        
        for(int i=0; i<parola.length();i++)
        {
            ArrayList<Integer> tmp=new ArrayList<Integer>();
            char c=parola.charAt(i);
            tmp.add(i);
            if(c >= 'a' && c <= 'z')
            {
                for(int j=i+1;j<parola.length();j++)
                {
                    if(c == parola.charAt(j))
                    {
                        tmp.add(j);
                    }
                }
                if(tmp.size()>1)
                {
                   list = SubCheckLista(tmp, list);
                }
            }
        }
        
        return list;
    }

    private static  ArrayList<String>  SubCheckLista(ArrayList<Integer> tmp, ArrayList<String> list)
    {
        Iterator<String> it = list.iterator();
        while(it.hasNext()) 
        {
            String parList = it.next();
            char ck = parList.charAt(tmp.get(0));
            for( Integer t : tmp )
            {
                if( parList.charAt(t) != ck  )
                {
                    it.remove();
                }
            }
        }
        
        return list;
    }

    public static HashMap<Character,Double> CheckFreq(String testo)
    {
        HashMap<Character,Double> map = new HashMap<Character,Double>();
        String s = testo;
        int lenght= s.length();
        for(int i = 0; i < s.length(); i++){ 
            char c = s.charAt(i); 
            Double val = map.get(new Character(c));
            if(val != null && c!=' '){
                map.put(c, new Double(val + 1));
            }
            else if(c == ' '){
               lenght--;
            }
            else
            {
                 map.put(c,1.0); 
            }
        }
        
        for(Character c : map.keySet())
        {
            Double fre =(double)((int)(map.get(c) / lenght *10000))/100 ;
            map.put(c, fre  );
        }
        
        return map;
    }
}


