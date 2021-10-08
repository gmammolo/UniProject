/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LFT.Assembler;

import LFT.AnalisiSintattica.*;
import LFT.AnalisiLessicale.IllegalStringException;
import LFT.AnalisiLessicale.Lexer;
import LFT.AnalisiLessicale.Tag;
import LFT.AnalisiLessicale.Token;
import LFT.AnalisiLessicale.Word;
import LFT.AnalisiLessicale.Number;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Traslator {

    private Lexer lex;
    private BufferedReader pbr;
    private Token look;
    private SymbolTable st = new SymbolTable();
    private CodeGenerator code = new CodeGenerator();
    private int count = 0;

    public Traslator(Lexer l, BufferedReader br) {
        lex = l;
        pbr = br;
        move();
    }

    void move() {
        try {
            look = lex.lexical_scan(pbr);
        } catch (IllegalStringException ex) {
            Logger.getLogger(Traslator.class.getName()).log(Level.SEVERE, null, ex);
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

    public void prog() {
        if (look.tag == Tag.PRINT || look.tag == Tag.READ || look.tag == Tag.IF || look.tag == Tag.WHILE || look.tag == Tag.ID || look.tag == (int) '{') {
            statlist();
            if (look.tag == Tag.EOF) {
                match(Tag.EOF);
            } else {
                error("Syntax error in prog. Expected '" + Tag.EOF + "', instead read " + look.tag);
            }
            try {
                code.toJasmin();
            } catch (java.io.IOException e) {
                System.out.println("IO error\n");
            };
        } else {
            error("Syntax error in prog " + look.tag);
        }
    }

    private void stat() {
        switch (look.tag) {
            case Tag.PRINT:
                match(Tag.PRINT);
                if (look.tag == '(') {
                    match('(');
                } else {
                    error("Syntax error in stat. Expected '(', instead read '" + _printTag(look) + "'");
                }
                expr();
                code.emit(OpCode.invokestatic, 1);
                if (look.tag == ')') {
                    match(')');
                } else {
                    error("Syntax error in prog. Expected ')', instead read '" + _printTag(look) + "'");
                }
                break;
            case Tag.READ:
                match(Tag.READ);
                if (look.tag == '(') {
                    match('(');
                } else {
                    error("Syntax error in stat. Expected '(', instead read '" + _printTag(look) + "'");
                }
                if (look.tag == Tag.ID) {
                    int read_id_addr = st.lookupAddress(((Word) look).lexeme);
                    if (read_id_addr == -1) {
                        read_id_addr = count;
                        st.insert(((Word) look).lexeme, count++);
                    }
                    match(Tag.ID);
                    if (look.tag == ')') {
                        match(')');
                    } else {
                        error("Syntax error in prog. Expected ')', instead read '" + _printTag(look) + "'");
                    }
                    code.emit(OpCode.invokestatic, 0);
                    code.emit(OpCode.istore, read_id_addr);
                } else {
                    error("Syntax error in stat. Expected a valid id, instead read '" + _printTag(look) + "'");
                }
                break;
            case Tag.IF:
                match(Tag.IF);
                if (look.tag == '(') {
                    match('(');
                } else {
                    error("Syntax error in stat. Expected '(', instead read '" + _printTag(look) + "'");
                }
                bexpr();
                if (look.tag == ')') {
                    match(')');
                } else {
                    error("Syntax error in prog. Expected ')', instead read '" + _printTag(look) + "'");
                }
                int end_else = code.newLabel();
                int end_if = code.newLabel();
                code.emit(OpCode.ifeq, end_else); //eq controlla se cima stack==0
                stat();
                code.emit(OpCode.ldc, 0);
                code.emit(OpCode.GOto, end_if);
                code.emitLabel(end_else);
                code.emit(OpCode.ldc, 1);
                code.emitLabel(end_if);
                stat_p();
                break;
            case Tag.WHILE:
                match(Tag.WHILE);
                int repeat_do = code.newLabel();
                int repeat_exit = code.newLabel();
                code.emitLabel(repeat_do);
                if (look.tag == '(') {
                    match('(');
                } else {
                    error("Syntax error in stat. Expected '(', instead read '" + _printTag(look) + "'");
                }
                bexpr();
                code.emit(OpCode.ifeq, repeat_exit);
                if (look.tag == ')') {
                    match(')');
                } else {
                    error("Syntax error in prog. Expected ')', instead read '" + _printTag(look) + "'");
                }
                stat();
                code.emit(OpCode.GOto, repeat_do);
                code.emitLabel(repeat_exit);
                break;
            case (int) '{':
                match('{');
                statlist();
                if (look.tag == '}') {
                    match('}');
                } else {
                    error("Syntax error in prog. Expected '}', instead read '" + _printTag(look) + "'");
                }
                break;
            case Tag.ID:
                Token m_token = look;
                match(Tag.ID);
                String id = ((Word) m_token).lexeme;
                match(Tag.ASSIGN);
                expr();
                int m_val = st.lookupAddress(id);
                if (m_val == -1) {
                    code.emit(OpCode.istore, count);
                    st.insert(id, count++);
                } else {
                    code.emit(OpCode.istore, m_val);
                }

                break;
            default:
                error("Syntax error in stat " + look.tag);
                break;
        }
    }

    private void stat_p() {
        switch (look.tag) {
            case Tag.ELSE:
                match(Tag.ELSE);
                int end_else = code.newLabel();
                code.emit(OpCode.ifeq, end_else); //eq controlla se cima stack==0
                stat();
                code.emitLabel(end_else);
                break;
            case (int) ';':
            case (int) '}':
            case Tag.EOF:
                code.emit(OpCode.pop); // rimuove lo 0 inserito in stat in assenza di else
                break;
            default:
                error("Syntax error in stat_p " + look.tag);
                break;
        }
    }

    private void statlist() {
        switch (look.tag) {
            case Tag.PRINT:
            case Tag.READ:
            case Tag.IF:
            case Tag.WHILE:
            case (int) '{':
            case Tag.ID:
                stat();
                statlist_p();
                break;
            default:
                error("Syntax error in statlist " + look.tag);
                break;
        }
    }

    private void statlist_p() {
        switch (look.tag) {
            case ';':
                match(';');
                stat();
                statlist_p();
                break;
            case (int) '}':
            case Tag.EOF:
                break;
            default:
                error("Syntax error in statlist_p " + look.tag);
                break;
        }
    }

    private void bexpr() { //sullo stack: 1 true, 0 false 
        if (look.tag == Tag.ID || look.tag == Tag.NUM || look.tag == (int) '(') {
            expr();
            Token m_relop = look;
            if (look.tag == Tag.RELOP) {
                match(Tag.RELOP);
            } else {
                error("Syntax error in bexpr. Expected '<=, >=, ==, < or >', instead read '" + _printTag(look) + "'");
            }
            expr();
            int ltrue = code.newLabel();
            int lnext = code.newLabel();
            if (((Word) m_relop).equals(Word.eq)) {
                code.emit(OpCode.if_icmpeq, ltrue);
            } else if (((Word) m_relop).equals(Word.lt)) {
                code.emit(OpCode.if_icmplt, ltrue);
            } else if (((Word) m_relop).equals(Word.le)) {
                code.emit(OpCode.if_icmple, ltrue);
            } else if (((Word) m_relop).equals(Word.gt)) {
                code.emit(OpCode.if_icmpgt, ltrue);
            } else if (((Word) m_relop).equals(Word.ge)) {
                code.emit(OpCode.if_icmpge, ltrue);
            } else if (((Word) m_relop).equals(Word.ne)) {
                code.emit(OpCode.if_icmpne, ltrue);
            } else {
                error("Errore del traduttore nel RELOP");
            }
            code.emit(OpCode.ldc, 0);
            code.emit(OpCode.GOto, lnext);
            code.emitLabel(ltrue);
            code.emit(OpCode.ldc, 1);
            code.emitLabel(lnext);

        } else {
            error("Syntax error in bexpr " + look.tag);
        }
    }

    private void expr() {
        if (look.tag == Tag.ID || look.tag == Tag.NUM || look.tag == (int) '(') {
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
                term(); // un intero su stack
                exprp(); // un intero su stack
                code.emit(OpCode.iadd);
                break;
            case '-':
                match('-');
                term();
                exprp();
                code.emit(OpCode.isub);
                break;
            case ')':
            case ';':
            case '}':
            case Tag.ELSE:
            case Tag.RELOP:
            case Tag.EOF:
                break;
            default:
                error("Syntax error in exprp " + look.tag);
                break;
        }
    }

    private void term() {
        if (look.tag == (int) '(' || look.tag == Tag.NUM || look.tag == Tag.ID) {
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
                code.emit(OpCode.imul);
                break;
            case '/':
                match('/');
                fact();
                termp();
                code.emit(OpCode.idiv);
                break;
            case '+':
            case '-':
            case ')':
            case ';':
            case '}':
            case Tag.ELSE:
            case Tag.RELOP:
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
                code.emit(OpCode.ldc, ((Number) look).value);
                match(look.tag);
                break;
            case Tag.ID:
                Token m_id = look;
                match(look.tag);
                code.emit(OpCode.iload, st.lookupAddress(((Word) m_id).lexeme));
                break;
            case '(':
                match('(');
                expr();
                if (look.tag == ')') {
                    match(')');
                } else {
                    error("Syntax error in fact. Expected ')', instead read '" + _printTag(look) + "'");
                }
                break;
            default:
                error("Syntax error in fact " + (look.tag));
                break;
        }
    }

    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = new File("src/LFT/AnalisiSintattica/source3.txt").getAbsolutePath();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Traslator parser = new Traslator(lex, br);
            parser.prog();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String _printTag(Token t) {
        if (t.tag >= 0 && t.tag < 255) {
            return "" + (char) t.tag;
        } else if (t instanceof Word) {
            return ((Word) t).lexeme;
        } else if (t instanceof Number) {
            return "" + ((Number) t).value;
        } else {
            return "" + t.tag;
        }
    }
}
