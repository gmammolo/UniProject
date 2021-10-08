/**
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
public class Comma4 extends DFAModel {

    @Override
    protected int numState() {
        return 8;
    }

    @Override
    protected void initializeDFA() {
        setMove(0, ' ', 0);
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

        setMove(1, ' ', 5);
        setMove(5, ' ', 5);

        setMove(2, ' ', 6);
        setMove(6, ' ', 6);

        setMove(1, new RangeChar('A', 'K'), 3); //turno 1
        setMove(5, new RangeChar('A', 'K'), 3);

        setMove(2, new RangeChar('A', 'K'), 4); //turno 2
        setMove(6, new RangeChar('A', 'K'), 4);

        setMove(1, new RangeChar('L', 'Z'), 4); //turno 3
        setMove(5, new RangeChar('L', 'Z'), 4);

        setMove(2, new RangeChar('L', 'Z'), 3); //turno 4
        setMove(6, new RangeChar('L', 'Z'), 3);

        //setMove(3, RangeChar.GetAlphabet(), 3); //stato pozzo per quanto riguarda l'esercizio
        
        setMove(4, RangeChar.GetAlphabetLovercase(), 4);
        setMove(4, ' ', 7);
        setMove(7, ' ', 7);
        setMove(7, RangeChar.GetAlphabetUppercase(), 4);
        
        addFinalState(4);
        addFinalState(7);
    }

    public static void main(String[] args) throws Exception {

        Comma4 comma = new Comma4();

        //comma.minimize();
        /*BUG 6-12-2016: Minimize currently not working properly*/
        //InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        //BufferedReader reader = new BufferedReader(inputStreamReader);
        //System.out.println("Type the line:");
        //String line = reader.readLine();
        //comma.scan(line);
        String outputDir = "/home/terasud/NetBeansProjects/LFT/src/LFT/A2016/cap1/";
        System.out.println(comma.toJava("Es1Comma4_Code"));
        comma.writeToJava("Es1Comma4_Code", outputDir);
        System.out.println(comma.toDot("Es1Comma4_Dot"));
        comma.writeToDot("Es1Comma4_Dot", outputDir);
        comma.ToPng("Es1Comma4_Png", outputDir);
    }

}
