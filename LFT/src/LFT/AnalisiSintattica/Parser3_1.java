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

public class Parser3_1 {

    private Lexer lex;
    private BufferedReader pbr;
    private Token look;

    public Parser3_1(Lexer l, BufferedReader br) {
        lex = l;
        pbr = br;
        move();
    }

    void move() {
        try {
            look = lex.lexical_scan(pbr);
        } catch (IllegalStringException ex) {
            Logger.getLogger(Parser3_1.class.getName()).log(Level.SEVERE, null, ex);
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
            exprp();
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
            termp();
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
            Parser3_1 parser = new Parser3_1(lex, br);
            parser.start();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
