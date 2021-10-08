/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LFT.A2016.cap1;

import main.java.com.opendfa.DFA.RangeChar;
import main.java.com.opendfa.DFAModel;

/**
 *
 * @author Giuseppe
 */
public class Comma2 extends DFAModel {

    @Override
    protected void initializeDFA() {
        setMove(0, RangeChar.GetAlphabet() , 1);
        setMove(1, RangeChar.GetFullAlphabet(), 1);
        setMove(1, '_', 1);

        setMove(0, '_', 2);
        setMove(2, RangeChar.GetFullAlphabet(), 1);
        //Testato su Java che sono accettabili come variabile '__' e '_2'
        
        addFinalState(1);
    }

    @Override
    protected int numState() {
        return 4;
    }

    public static void main(String[] args) throws Exception {

        Comma2 comma = new Comma2();

        //InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        //BufferedReader reader = new BufferedReader(inputStreamReader);
        //System.out.println("Type the line:");
        //String line = reader.readLine();
        //comma.scan(line);
        String outputDir = "/home/terasud/NetBeansProjects/LFT/src/LFT/A2016/cap1/";
        System.out.println(comma.toJava("Es1Comma2_Code"));
        comma.writeToJava("Es1Comma2_Code",outputDir);
        System.out.println(comma.toDot("Es1Comma2_Dot"));
        comma.writeToDot("Es1Comma2_Dot",outputDir);
        comma.ToPng("Es1Comma2_Png", outputDir);
    }

}
