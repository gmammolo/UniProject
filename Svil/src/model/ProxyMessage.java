/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author Giuseppe
 */
public class ProxyMessage extends Messaggio {

    static ProxyMessage GetProxy(Messaggio mess) {
        return new ProxyMessage(mess.Id, mess.Testo, mess.Cifrato, mess.CryptMethod.name(),mess.metakey, mess.Lingua , mess.Sender, mess.Receiver, mess.GetFlagInteger());
    }

    public ProxyMessage(Integer id, String testo, String cifrato, String metodo_criptaggio, String metakey, String lingua, String sender, String receiver, Integer flag) {
        super(id, testo, cifrato, metodo_criptaggio, metakey, lingua, sender, receiver, flag);
    }

    
    public String getText()
    {
        return Testo;
    }
    
    public String getSender()
    {
        return Sender;
    }
    
    public String getReceiver()
    {
        return Receiver;
    }
    
//    public String getMethod()
//    {
//        return ;
//    }

    

    
    
}
