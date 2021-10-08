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
public class Comma5 extends DFAModel {

    @Override
    protected int numState() {
        return 10;
    }

    @Override
    protected void initializeDFA() {

        setMove(0, new RangeChar('A', 'K'), 1);
        setMove(1, RangeChar.GetAlphabetLovercase(), 1);
        setMove(1, ' ', 3);
        setMove(3, ' ', 3);
        setMove(3, RangeChar.GetAlphabetUppercase(), 1);

        setMove(0, new RangeChar('L', 'Z'), 2);
        setMove(2, RangeChar.GetAlphabetLovercase(), 2);
        setMove(2, ' ', 4);
        setMove(4, ' ', 4);
        setMove(4, RangeChar.GetAlphabetUppercase(), 2);
       
        for (int i = 0; i <= 9; i++) {
            if (i % 2 == 0) {
                setMove(1, Character.forDigit(i, 10), 5);
                setMove(2, Character.forDigit(i, 10), 6);
                setMove(7, Character.forDigit(i, 10) ,9);
                setMove(8, Character.forDigit(i, 10) ,5);
            } else {
                setMove(1, Character.forDigit(i, 10), 6);
                setMove(2, Character.forDigit(i, 10), 7);
                setMove(9, Character.forDigit(i, 10) ,7);
                setMove(5, Character.forDigit(i, 10) ,8);
              
            }
        }
        
        addFinalState(5);        
        addFinalState(7);

    }

    public static void main(String[] args) throws Exception {

        Comma5 comma = new Comma5();

        //comma.minimize();
        /*BUG 6-12-2016: Minimize currently not working properly*/

        //InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        //BufferedReader reader = new BufferedReader(inputStreamReader);
        //System.out.println("Type the line:");
        //String line = reader.readLine();
        //comma.scan(line);
        String outputDir = "/home/terasud/NetBeansProjects/LFT/src/LFT/A2016/cap1/";
        System.out.println(comma.toJava("Es1Comma5_Code"));
        comma.writeToJava("Es1Comma5_Code", outputDir);
        System.out.println(comma.toDot("Es1Comma5_Dot"));
        comma.writeToDot("Es1Comma5_Dot", outputDir);
        comma.ToPng("Es1Comma5_Png", outputDir);
    }

}
