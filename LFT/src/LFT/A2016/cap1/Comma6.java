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
public class Comma6 extends DFAModel {

    @Override
    protected int numState() {
        return 10;
    }

    @Override
    protected void initializeDFA() {

        setMove(0, '0', 0);
        setMove(0, '1', 1);    
        setMove(1, '1', 0);
        setMove(1, '0', 2);
        setMove(2, '0', 1);
        setMove(2, '1', 2);
        addFinalState(0);        

    }

    public static void main(String[] args) throws Exception {

        Comma6 comma = new Comma6();

        //comma.minimize();
        /*BUG 6-12-2016: Minimize currently not working properly*/
//
//        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
//        BufferedReader reader = new BufferedReader(inputStreamReader);
//        System.out.println("Type the line:");
//        String line = reader.readLine();
//        System.out.println(comma.scan(line));
        
        String outputDir = "/home/terasud/NetBeansProjects/LFT/src/LFT/A2016/cap1/";
        System.out.println(comma.toJava("Es1Comma6_Code"));
        comma.writeToJava("Es1Comma6_Code", outputDir);
        System.out.println(comma.toDot("Es1Comma6_Dot"));
        comma.writeToDot("Es1Comma6_Dot", outputDir);
        comma.ToPng("Es1Comma6_Png", outputDir);
    }

}
