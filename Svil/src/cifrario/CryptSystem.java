/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cifrario;

/**
 * Base del sistema di Criptaggio.
 * Questa classe ha informazioni base che saranno usate in tutti i metodi di cifratura
 * @author Giuseppe
 */
public abstract class CryptSystem {
    
    /**
     * Alfabeto contenete tutti i caratteri riconosciuti e che subitanno la ciratura.
     * gli altri caratteri rimarranno invariati
     */
    protected static String alfabeto = new String("abcdefghilmnopqrstuvz") ;

    /**
     * Lunghezza dell' alfabeto
     */
    protected static int length = alfabeto.length();
    
    /**
     * Cripta un testo
     * @param s testo da criptare
     * @return testo criptato
     */
    public abstract String Crypt( String s );
    
    /**
     * Decripta un testo
     * @param s testo criptato
     * @return  testo in chiaro
     */
    public abstract String Decrypt( String s );
    
    /**
     * @return Una chiave da usare per criptare 
     */
    public abstract String GenerateKey() ;
    
    /**
     * @return Trasforma la chiave in una stringa che può essere salvata nel Db
     */
    public abstract String Serialize() ;
    /**
     * Trasforma una chiave Serializzata in una chiave utilizzabile dal sistema
     * @param Metakey Chiave Serializzata
     */
    public abstract void Deserialize(String Metakey) ;
    
    public CryptSystem()
    {
        
    }
    /**
     * 
     * @param Metakey Chiave Serializzata
     */
    public CryptSystem(String Metakey)
    {
        if(!Metakey.equals(""))
            Deserialize(Metakey);
    }
}
