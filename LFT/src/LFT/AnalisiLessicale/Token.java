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
public class Token {

    public final int tag;

    public Token(int t) {
        tag = t;
    }

    public String toString() {
        return "<" + tag + ">";
    }

    public static final Token not = new Token('!'),
            plus = new Token('+'),
            minus = new Token('-'),
            mult = new Token('*'),
            div = new Token('/'),
            colon = new Token(':'),
            semicolon = new Token(';'),
            lpt = new Token('('),
            rpt = new Token(')'),
            lpg = new Token('{'),
            rpg = new Token('}');

}
