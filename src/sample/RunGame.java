package sample;
import java.util.*;

/**
 * Created by Brian on 16/02/2017.
 */
public class RunGame {


    public static void main(String args[]) {
        Scanner reader = new Scanner(System.in);  // Reading from System.in

        Game g = new Game();
//        while(true){
//            System.out.println("Enter number: ");
//            int n = reader.nextInt(); // Scans the next token of the input as an int.
//            if(n==0){
//                g.printBoard();
//            }
//            g.turn();
//        }
//
//        Property p1 = (Property) g.squares.get(1);
//        Property p2 = (Property) g.squares.get(3);
//        Property p3 = (Property) g.squares.get(39);
//        Property p4 = (Property) g.squares.get(37);
//        Property p5 = (Property) g.squares.get(34);
//        Property p6 = (Property) g.squares.get(32);






        g.autoGame();

        /*

        g.setUpGame();

        u1.setGame(g);



        g.buy(g.users.get(0), (Purchasable) g.squares.get(1));
        g.buy(g.users.get(0), (Purchasable) g.squares.get(3));

        u1.buyOption(p1, p1.getCost());
        u1.buyOption(p2, p2.getCost());
        u1.update();
        u1.manage();
        u1.manage();
        g.buy(g.users.get(0), p3);
        System.out.println("UM: " + u1.getMoney());
        g.buy(g.users.get(0), p4);
        g.buy(g.users.get(0), p5);
        g.buy(g.users.get(0), p6);
        //g.buy(g.users.get(0), (Purchasable) g.squares.get(3));
        System.out.println("UM: " + u1.getMoney());


        u1.buyOption(p3, p3.getCost());
        u1.buyOption(p4, p4.getCost());
        u1.buyOption(p5, p5.getCost());
        u1.buyOption(p6, p6.getCost());
        System.out.println("UM: " + u1.getMoney());

        /*
        g.buildHouse((Property) g.squares.get(1));
        g.buildHouse((Property) g.squares.get(1));
        g.buildHouse((Property) g.squares.get(1));
        g.buildHouse((Property) g.squares.get(3));
        g.buildHouse((Property) g.squares.get(3));
        g.buildHouse((Property) g.squares.get(3));*/


        //g.startGame();
    }
}
