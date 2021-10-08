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

public class Valutatore3_1 {

    private Lexer lex;
    private Token look;
    private BufferedReader pbr;

    public Valutatore3_1(Lexer l, BufferedReader br) {
        lex = l;
        pbr = br;
        move();
    }

    void move() {
        try {
            look = lex.lexical_scan(pbr);
        } catch (IllegalStringException ex) {
            Logger.getLogger(Valutatore3_1.class.getName()).log(Level.SEVERE, null, ex);
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
        if (look.tag == (int) '(' || look.tag == Tag.NUM) {
            int expr_val = expr();
            match(Tag.EOF);
            System.out.println(expr_val);
        } else {
            error("Syntax error in start " + look.tag);
        }
    }

    private int expr() {
        if (look.tag == (int) '(' || look.tag == Tag.NUM) {
            int term_val, exprp_val;
            term_val = term();
            exprp_val = exprp(term_val);
            return exprp_val;
        } else {
            error("Syntax error in expr " + look.tag);
        }
        return -1;
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
                exprp_val = exprp_i;
                break;
            default:
                error("Syntax error in exprp " + look.tag);
                break;
        }
        return exprp_val;
    }

    private int term() {
        if (look.tag == (int) '(' || look.tag == Tag.NUM) {
            int termp_i, fact_i;
            fact_i = fact();
            return termp(fact_i);
        } else {
            error("Syntax error in term " + look.tag);
        }
        return -1;
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
                termp_val = termp(termp_i / fact_val);
                break;
            case '+':
            case '-':
            case ')':
            case Tag.EOF:
                termp_val = termp_i;
                break;
            default:
                error("Syntax error in termp " + (look.tag));
                break;
        }
        return termp_val;
    }

    private int fact() {
        int fact_val = 0;
        switch (look.tag) {
            case Tag.NUM:
                fact_val = ((Number) look).value;
                match(look.tag);
                break;
            case '(':
                match('(');
                fact_val = expr();
                match(')');
                break;
            default:
                error("Syntax error in fact " + (look.tag));
                break;
        }
        return fact_val;
    }

    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = new File("src/LFT/Validatore/source.txt").getAbsolutePath();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Valutatore3_1 parser = new Valutatore3_1(lex, br);
            parser.start();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
