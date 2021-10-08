/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.swing.JOptionPane;


/**
 *
 * @author Giuseppe
 */
public class ListaIpotesi {
    private NodeIpotesi root; 

    
    public ListaIpotesi()
    {
        root= new NodeIpotesi(' ', ' ');
    }

    public  boolean AddNewNode(char a, char b)
    {    
        NodeIpotesi Nodo=new NodeIpotesi(a,b);
        return AddNode(Nodo);
    }
    
    
    public  boolean AddNode(NodeIpotesi Nodo)
    {    
        //Nodo.parent=root;
        NodeIpotesi index=root;
        while(index.son.size()>0)
        {
            index = index.son.get(0);
            if(index.a == Nodo.a || index.b == Nodo.b)
            {
                JOptionPane.showMessageDialog(null,"Sostituzione già presente");
                //index.b=Nodo.b;
                return false;             
            }
        }
        
        index.addNode(Nodo);
        return true;
        //root = Nodo;
    }

    public String Serialize() {
        NodeIpotesi n=root;
        String ris = "";
        while(n.HasNext())
        {
            ris+=n.GetLastSon().toString();
        }
        
        return ris;
    }
    

    /**
     * 
     * @return 
     */
    @Override
    public String toString()
    {
        NodeIpotesi n=root;
        String ris = "";
        while(n.HasNext())
        {
            n=n.GetLastSon();
            ris+=n.toString()+"\n";
            
        }
        
        return ris;
    }
    
    public void Deserialize(String string)
    {
        NodeIpotesi r=root;
        try{
            int i=0;
            while(string.substring(i*6).length() >= 6)
            {
                NodeIpotesi n=new NodeIpotesi(string.substring(i*6,i*6+6));
                r.son.add(n);
                i++;
                r=n;
            }
        }
        catch(Exception e)
        {
            System.out.println("Errore Durante la deserializzazione");
        }
    }

    void RemoveLastNode() {
        NodeIpotesi n=root;
        while(n!= null && n.HasNext())
        {
            if(!n.GetLastSon().HasNext())
            {
                n.son.remove(n.GetLastSon());
            }
            n=n.GetLastSon();
//            if(!n.Next())
//                n=null;
        }
    }
    
    
    public String GetTextIpotesi( String Text)
    {
        NodeIpotesi n=root;
        while(n.HasNext())
        {
            n=n.GetLastSon();
            Text = Text.replace(n.a,n.b);            
        }
        
        return Text;
        
    }
    
}
