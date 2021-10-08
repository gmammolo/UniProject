/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LFT.Validatore;

import LFT.AnalisiLessicale.IllegalStringException;
import LFT.AnalisiLessicale.Number;
import LFT.AnalisiLessicale.Lexer;
import LFT.AnalisiLessicale.Tag;
import LFT.AnalisiLessicale.Token;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Valutatore {

    private Lexer lex;
    private Token look;
    private BufferedReader pbr;

    public Valutatore(Lexer l, BufferedReader br) {
        lex = l;
        pbr = br;
        move();
    }

    void move() {
        try {
            look = lex.lexical_scan(pbr);
        } catch (IllegalStringException ex) {
            Logger.getLogger(Valutatore.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.err.println("token = " + look);
    }

    void error(String s) {
        throw new Error("near line " + lex.line + ": " + s);
    }

    void match(int t) {
        if (look.tag == t) {
            if (look.tag != Tag.EOF) {
                move();
            }
        } else {
            error("syntax error");
        }
    }

    public void start() {
        int expr_val ;
        expr_val = expr();
        match(Tag.EOF);
        System.out.println(expr_val);
    }

    private int expr() {
        int term_val, exprp_val;
        term_val = term();
        exprp_val = exprp(term_val);
        return exprp_val;
    }

    private int exprp(int exprp_i) {
        int term_val, exprp_val = 0;
        switch (look.tag) {
            case '+':
                match('+');
                term_val = term();
                exprp_val = exprp(exprp_i + term_val);
                break;
            case '-':
                match('-');
                term_val = term();
                exprp_val = exprp(exprp_i - term_val);
                break;
            case ')':
            case Tag.EOF:
                exprp_val= exprp_i;
                break;
            default:
                error("Syntax error in exprp "+ look.tag);
                break;
        }
        return exprp_val;
    }

    private int term() {
        int termp_i, fact_i;
        fact_i = fact();
        return termp(fact_i);
    }

    private int termp(int termp_i) {
        int fact_val, termp_val = 0;
        switch (look.tag) {
            case '*':
                match('*');
                fact_val = fact();
                termp_val = termp(termp_i * fact_val);
                break;
            case '/':
                match('/');
                fact_val = fact();
                termp_val =termp(termp_i / fact_val);
                break;
            case '+':
            case '-':
            case ')':
            case Tag.EOF:
                termp_val = termp_i;
                break;
            default:
                error("Syntax error in termp "+ (look.tag));
                break;
        }
        return termp_val;
    }

    private int fact() {
        int fact_val = 0;
        switch (look.tag) {
            case Tag.NUM:
                fact_val = ((Number)look).value;
                 match(Tag.NUM);
                break;
            case '(':
                match('(');
                fact_val= expr();
                match(')');
                break;
            default:
                fact_val = expr();
                break;  
        }
        return fact_val;
    }
    
    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = new File("src/LFT/Validatore/source.txt").getAbsolutePath();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Valutatore parser = new Valutatore(lex, br);
            parser.start();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
