/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LFT.AnalisiLessicale;

/**
 *
 * @author Giuseppe
 */
public class Number extends Token {
        
    public Integer value = 0;

    public Number( Integer v) {
        super(Tag.NUM);
        value = v;
    }

    public String toString() {
        return "<" + tag + ", " + value + ">";
    }
}
