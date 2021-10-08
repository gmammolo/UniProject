/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LFT.Assembler;

/**
 *
 * @author terasud
 */
public enum OpCode { 
    ldc , imul , idiv , iadd , 
    isub , istore , iload ,
    if_icmpeq , if_icmple , if_icmplt , if_icmpne, if_icmpge , 
    if_icmpgt , ifeq, ifne , GOto , invokestatic , label,
    pop
}
