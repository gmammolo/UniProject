/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import com.sun.org.apache.xerces.internal.dom.NodeImpl;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Giuseppe
 */
public class NodeIpotesi implements Serializable
{
    private static final long serialVersionUID =  6078824793244634070L;
    
    protected char a;
    protected char b;

//    protected NodeIpotesi parent;
    protected ArrayList<NodeIpotesi> son;


    public NodeIpotesi(char a, char b)
    {
        this.a=a;
        this.b=String.valueOf(b).toUpperCase().charAt(0);
        son=new ArrayList<NodeIpotesi>();
    }

    public NodeIpotesi(String value)
    {
        this.a=value.charAt(0);
        this.b=value.charAt(value.length()-1);
        son=new ArrayList<NodeIpotesi>();
    }

    public void addNode(NodeIpotesi node)
    {
//        node.parent = this;
        son.add(node);
    }

    @Override
    public String toString()
    {
        return a+" => "+b;
    }

    
    public String toStringNode(int level)
    {
        String ris=toString();
        for(int i=0;i<son.size();i++)
        {
            ris+="\n"+repeatDelimit(level)+son.get(i).toStringNode(level+1);
        }
        return ris;
    }
    
    private static String repeatDelimit(int num)
    {
        String r="";
        for(int i=0; i<num; i++)
            r+="|\t";
        return r;
    }
    
    /**
     * Solo per Lista ipotesi
     * @return 
     */
    public boolean HasNext() {
        return (son == null || son.size() == 0) ? false : true;
    }
    
    public NodeIpotesi GetLastSon()
    {
        if(son.size()<1)
            return null;
        return son.get(son.size()-1);
    }
    
    
    public boolean HasNode(NodeIpotesi  node)
    {
        if(equals(node))
            return true;
        for(int i=0; i< son.size(); i++)
        {
           boolean tr = son.get(i).HasNode(node);
           if(tr)
               return true;
        }
        return false;
    }
    
    /**
     * Metodo Di supporto per la generazione della lista dall' albero delle 
     * ipotesi. 
     * @param index index dell' albero
     * @return 
     */
    public NodeIpotesi GenerateLista(NodeIpotesi index)
    {
        NodeIpotesi node=Clone(this);
        for(int i=0; i< son.size(); i++)
        {
            if(son.get(i).HasNode(index))
                node.addNode(son.get(i).GenerateLista(index));
        }
        return node;
    }
    
    
    /**
     * Ritorna il Parent del NodeIpotesi
     * @param node
     * @return NodeIpotesi or NULL se non esiste quel nodo
     */
    public NodeIpotesi GetParentNode(NodeIpotesi  node)
    {
        for(int i=son.size()-1; i>= 0; i-- )
        {
            if(son.get(i).equals(node))
                return this;
           NodeIpotesi n =  son.get(i).GetParentNode(node);
           if(n!=null)
               return n;
        }
        
        return null;
    }
    
   
    public static NodeIpotesi Clone(NodeIpotesi Node)
    {
        return new NodeIpotesi(Node.a,Node.b);
    }

    public boolean equals(NodeIpotesi node)
    {
        if(node!=null)
        {
            if(a == node.a && b==node.b)
                return true;
        }
        return false;
    }
   
}