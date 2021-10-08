/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LFT.Assembler;

import java.util.*;

public class SymbolTable {

     Map <String, Integer> OffsetMap = new HashMap <String,Integer>();

	public void insert( String s, int address ) {
            if( !OffsetMap.containsValue(address) ) 
                OffsetMap.put(s,address);
            else 
                throw new IllegalArgumentException("Reference to a memory location already occupied by another variable");
	}

	public int lookupAddress ( String s ) {
            if( OffsetMap.containsKey(s) ) 
                return OffsetMap.get(s);
            else
                return -1;
//                throw new IllegalArgumentException("Unknown variable");
	}
}
