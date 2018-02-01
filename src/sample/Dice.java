package sample; /**
 * Created by Brian on 16/02/2017.
 */

import java.util.Random;

class Dice {
    private int dice1 = 1;
    private int dice2 = 1;

    private Random rn = new Random();

    void rollDice(){
        int min = 1;
        int max = 6;
        dice1 = rn.nextInt(max - min + 1) + min;
        dice2 = rn.nextInt(max - min + 1) + min;
    }

    int getTotal(){
        return dice1 + dice2;
    }

    int getDice1(){
        return dice1;
    }

    int getDice2() {
        return dice2;
    }

    boolean isDouble(){
        return dice1 == dice2;
    }
}
