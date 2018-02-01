package sample; /**
 * Created by Brian on 18/02/2017.
 */

/**
 * Created by Brian on 16/02/2017.
 */
//import Monopoly.Dice;

public class DiceTest {
    public static void main(String args[]) {
        Dice d = new Dice();
        for(int x = 0; x < 10; x = x + 1) {
            System.out.println("Rolling Dice...");
            d.rollDice();
            System.out.println("D1: " + d.getDice1() + " D2: " + d.getDice2() + " Total: " + d.getTotal());
            if (d.isDouble()){
                System.out.println("Double!");
            }
        }
    }
}