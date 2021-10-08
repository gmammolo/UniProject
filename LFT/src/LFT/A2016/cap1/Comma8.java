/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LFT.A2016.cap1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import main.java.com.opendfa.DFA.RangeChar;
import main.java.com.opendfa.DFAModel;

/**
 *
 * @author Giuseppe
 */
public class Comma8 extends DFAModel {

    @Override
    protected int numState() {
        return 4;
    }

    @Override
    protected void initializeDFA() {

        setMove(0, new RangeChar(new char[]{'a' , '*'}), 0);
        setMove(0, '/', 1);    
        setMove(1, '*', 2);
        setMove(2, 'a', 2);
        setMove(2, '*', 3);
        setMove(3, 'a', 2);
        setMove(3, '/', 0);
        addFinalState(0);
        addFinalState(1);  

    }

    public static void main(String[] args) throws Exception {

        Comma8 comma = new Comma8();

        //comma.minimize();
        /*BUG 6-12-2016: Minimize currently not working properly*/
//
//        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
//        BufferedReader reader = new BufferedReader(inputStreamReader);
//        System.out.println("Type the line:");
//        String line = reader.readLine();
//        System.out.println(comma.scan(line));
//        
        String outputDir = "/home/terasud/NetBeansProjects/LFT/src/LFT/A2016/cap1/";
        System.out.println(comma.toJava("Es1Comma8_Code"));
        comma.writeToJava("Es1Comma8_Code", outputDir);
        System.out.println(comma.toDot("Es1Comma8_Dot"));
        comma.writeToDot("Es1Comma8_Dot", outputDir);
        comma.ToPng("Es1Comma8_Png", outputDir);
    }

}
