/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LFT.AnalisiLessicale;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import LFT.AnalisiLessicale.IllegalStringException;

/**
 *
 * @author Giuseppe
 */
public class Lexer {

    public static int line = 1;
    private char peek = ' ';
    HashMap<String, Word> words = new HashMap();

    void reserve(Word w) {
        words.put(w.lexeme, w);
    }

    public Lexer() {
//        reserve(new Word(Tag.VAR, "var"));
//        reserve(new Word(Tag.NOT, "not"));
//        reserve(new Word(Tag.BOOLEAN, "boolean"));
//        reserve(new Word(Tag.INTEGER, "integer"));
//        reserve(new Word(Tag.TRUE, "true"));
//        reserve(new Word(Tag.FALSE, "false"));
        reserve(new Word(Tag.PRINT, "print"));
        reserve(new Word(Tag.READ, "read"));
        reserve(new Word(Tag.IF, "if"));
        reserve(new Word(Tag.ELSE, "else"));
        reserve(new Word(Tag.WHILE, "while"));
        
    }

    private void readch(BufferedReader br) {
        try {
            peek = (char) br.read();
        } catch (IOException exc) {
            peek = (char) -1;
        }
    }

    public Token lexical_scan(BufferedReader br) throws IllegalStringException {
        while (peek == ' ' || peek == '\t' || peek == '\n' || peek == '\r') {
            if (peek == '\n') {
                line++;
            }
            readch(br);
        }
        switch (peek) {
            //case ',':
            //    peek = ' ';
            //   return Token.comma;
            case ';':
                peek = ' ';
                return Token.semicolon;
            case '(':
                peek = ' ';
                return Token.lpt;
            case ')':
                peek = ' ';
                return Token.rpt;
            case '+':
                peek = ' ';
                return Token.plus;
            case '-':
                peek = ' ';
                return Token.minus;
            case '*':
                peek = ' ';
                return Token.mult;
            case '/':
                readch(br);
                if (peek == '/') { //commento //
                    do {
                        readch(br);
                    } while (peek != '\n' && peek != '$' && peek != (char) -1); //fine linea
                    return lexical_scan(br);
                } else if (peek == '*') { //commento /*
                    do {
                        do {
                            readch(br);
                        } while (peek != '*');
                        readch(br);
                        if (peek == '/') {
                            peek = ' ';
                            return lexical_scan(br);
                        }
                    } while (peek != '$' && peek != (char) -1);
                } else {
                    return Token.div;
                }
            case '&':
                readch(br);
                if (peek == '&') {
                    peek = ' ';
                    return Word.and;
                } else {
                    throw new IllegalStringException("Erroneous character after & at line" + line + ": " + peek);
                }
            case '|':
                readch(br);
                if (peek == '|') {
                    peek = ' ';
                    return Word.or;
                } else {
                    throw new IllegalStringException("Erroneous character after | at line" + line + ": " + peek);
                }
            case '=':
                readch(br);
                if (peek == '=') {
                    peek = ' ';
                    return Word.eq;
                } else {
                    throw new IllegalStringException("Erroneous character after = at line" + line + ": " + peek);
                }
            case '<':
                readch(br);
                if (peek == '=') {
                    peek = ' ';
                    return Word.le;
                } else if (peek == '>') {
                    peek = ' ';
                    return Word.ne;
                } else {
                    return Word.lt;
                }
            case '>':
                readch(br);
                if (peek == '=') {
                    peek = ' ';
                    return Word.ge;
                } else {
                    return Word.gt;
                }
            case ':':
                readch(br);
                if (peek == '=') {
                    peek = ' ';
                    return Word.assign;
                } else {
                    return Token.colon;
                }
            case '{':
                peek = ' ';
                return Token.lpg;
            case '}':
                peek = ' ';
                return Token.rpg;
            default:
                if (Character.isLetter(peek) || peek == '_') {
                    String s = "";
                    do {
                        s += peek;
                        readch(br);
                    } while (Character.isDigit(peek) || Character.isLetter(peek) || peek == '_');
                    if (words.get(s) != null) {
                        return words.get(s);
                    } else {
                        words.put(s, new Word(Tag.ID, s));
                        return words.get(s);
                    }
                    //Gestione not
                } else if (Character.isDigit(peek)) {
                    Integer i = peek - 48;
                    readch(br);
                    while (Character.isDigit(peek)) {
                        i = i * 10 + (peek - 48);
                        readch(br);
                    }
                    return new Number(i);
                } else if (peek == '$' || peek == (char) -1) {
                    return new Token(Tag.EOF);
                } else {
                    throw new IllegalStringException("Erroneous character at line " + line + ": "
                            + peek);
                }
        }
    }

    public static void main(String[] args) throws IllegalStringException {
        Lexer lex = new Lexer();
        //String path = "C:\\Users\\Giuseppe\\Documents\\NetBeansProjects\\LFT\\src\\LFT\\AnaLess\\source.txt";
        String path = new File("src/LFT/AnalisiLessicale/source.txt").getAbsolutePath();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Token tok;
            do {
                tok = lex.lexical_scan(br);
                System.out.println("Scan: " + tok);
            } while (tok.tag != Tag.EOF);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
