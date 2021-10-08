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
public class Comma3 extends DFAModel {
    @Override
    protected int numState() {
        return 7;
    }

    @Override
    protected void initializeDFA() {
        for (int i = 0; i <= 9; i++) {
            if (i % 2 == 0) {
                setMove(0, Character.forDigit(i, 10), 2);
                setMove(2, Character.forDigit(i, 10), 2);
                setMove(1, Character.forDigit(i, 10), 2);
            } else {
                setMove(0, Character.forDigit(i, 10), 1);
                setMove(2, Character.forDigit(i, 10), 1);
                setMove(1, Character.forDigit(i, 10), 1);

            }
        }
        /* //Metodo Pulito
        setMove(1, new RangeChar( 'A', 'K'), 3); //turno 1
        setMove(3, RangeChar.GetAlphabet(), 3);

        setMove(2, new RangeChar( 'A', 'K'), 4); //turno 2
        setMove(4, RangeChar.GetAlphabet(), 4);
        
        
        setMove(1, new RangeChar( 'L', 'Z'), 5); //turno 3
        setMove(5, RangeChar.GetAlphabet(), 5);

        setMove(2, new RangeChar( 'L', 'Z'), 6); //turno 4
        setMove(6, RangeChar.GetAlphabet(), 6);
        
        addFinalState(4);
         addFinalState(5);
        */
        //metodo minimizzato
        setMove(1, new RangeChar( 'A', 'K'), 3); //turno 1
        setMove(3, RangeChar.GetAlphabet(), 3);

        setMove(2, new RangeChar( 'A', 'K'), 4); //turno 2
        setMove(4, RangeChar.GetAlphabet(), 4);
        
        
        setMove(1, new RangeChar( 'L', 'Z'), 4); //turno 3

        setMove(2, new RangeChar( 'L', 'Z'), 3); //turno 4
        addFinalState(4);
    }

    public static void main(String[] args) throws Exception {

        Comma3 comma = new Comma3(); 
        
        comma.minimize();
        /*BUG 6-12-2016: Minimize currently not working properly*/
        
        //InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        //BufferedReader reader = new BufferedReader(inputStreamReader);
        //System.out.println("Type the line:");
        //String line = reader.readLine();
        //comma.scan(line);
        String outputDir = "/home/terasud/NetBeansProjects/LFT/src/LFT/A2016/cap1/";
        System.out.println(comma.toJava("Es1Comma3_Code"));
        comma.writeToJava("Es1Comma3_Code", outputDir);
        System.out.println(comma.toDot("Es1Comma3_Dot"));
        comma.writeToDot("Es1Comma3_Dot", outputDir);
        comma.ToPng("Es1Comma3_Png", outputDir);
    }

}
