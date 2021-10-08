/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LFT.AnalisiSintattica;

import LFT.AnalisiLessicale.IllegalStringException;
import LFT.AnalisiLessicale.Lexer;
import LFT.AnalisiLessicale.Tag;
import LFT.AnalisiLessicale.Token;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Parser3_2 {

    private Lexer lex;
    private BufferedReader pbr;
    private Token look;

    public Parser3_2(Lexer l, BufferedReader br) {
        lex = l;
        pbr = br;
        move();
    }

    void move() {
        try {
            look = lex.lexical_scan(pbr);
        } catch (IllegalStringException ex) {
            Logger.getLogger(Parser3_2.class.getName()).log(Level.SEVERE, null, ex);
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
            expr();
            match(Tag.EOF);
        } else {
            error("Syntax error in start " + look.tag);
        }
    }

    private void expr() {
        if (look.tag == (int) '(' || look.tag == Tag.NUM) {
            term();
            switch (look.tag) {
                case (int) '+':
                    match('+');
                    expr();
                    break;
                case (int) '-':
                    match('-');
                    expr();
                    break;
                case (int) '(':
                case Tag.EOF:
                    return;
                default:
                    break;
            }
        } else {
            error("Syntax error in expr " + look.tag);
        }
    }

    private void exprp() {
        switch (look.tag) {
            case '+':
                match('+');
                term();
                exprp();
                break;
            case '-':
                match('-');
                term();
                exprp();
                break;
            case ')':
            case Tag.EOF:
                break;
            default:
                error("Syntax error in exprp " + look.tag);
                break;
        }
    }

    private void term() {
        if (look.tag == (int) '(' || look.tag == Tag.NUM) {
            fact();
            switch (look.tag) {
                case (int) '*':
                    match('*');
                    term();
                    break;
                case (int) '/':
                    match('/');
                    term();
                    break;
                case (int) '(':
                case Tag.EOF:
                case (int) '+':
                case (int) '-':
                    return;
                default:
                    break;
            }
        } else {
            error("Syntax error in term " + look.tag);
        }
    }

    private void termp() {
        switch (look.tag) {
            case '*':
                match('*');
                fact();
                termp();
                break;
            case '/':
                match('/');
                fact();
                termp();
                break;
            case '+':
            case '-':
            case ')':
            case Tag.EOF:
                break;
            default:
                error("Syntax error in termp " + (look.tag));
                break;
        }

    }

    private void fact() {
        switch (look.tag) {
            case Tag.NUM:
                match(look.tag);
                break;
            case '(':
                match('(');
                expr();
                match(')');
                break;
            default:
                error("Syntax error in fact " + (look.tag));
                break;
        }
    }

    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = new File("src/LFT/AnaSint/source.txt").getAbsolutePath();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Parser3_2 parser = new Parser3_2(lex, br);
            parser.start();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
