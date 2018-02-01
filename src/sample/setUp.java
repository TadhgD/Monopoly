package sample;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brian on 25/02/2017.
 */
public class setUp {
    List<Square> squares = new ArrayList<>();
    List<User> users = new ArrayList<>();

    public setUp() {

        User u1 = new BotUser1("No1", 1);
        User u2 = new BotUser1("No2", 2);
        User u3 = new BotUser1("No3", 3);
        //User u4 = new BotUser1("No4", 4);
        User u4 = new HumanUser("Human Boy", 4);
//        User u5 = new BotUser1("No3", 5);
//        User u6 = new BotUser1("No3", 6);
//        User u7 = new BotUser1("No3", 7);
//        User u8 = new BotUser1("No3", 8);


        //User u4 = new HumanUser("Human Boy", 4);


        Square s0 = new Idle("Go"); //idle
        Square s1 = new Property("Southside", 60, 0, 50, new int[]{2, 10, 30, 90, 160, 250});
        Square s2 = new Card("Community Chest", 0); //com chest
        // System.out.println(s1.getString());
        Square s3 = new Property("The Sportsman", 60, 0, 50, new int[]{4, 20, 60, 180, 320, 450});
        //System.out.println(s2.getString());
        Square s4 = new incomeTax(); //income tax
        Square s5 = new Railway("Hillbillies", 8); //railway

        Square s6 = new Property("Moks", 100, 1, 50, new int[]{6, 30, 90, 270, 400, 550});
        Square s7 = new Card("Chance", 1);
        Square s8 = new Property("The Gallows", 100, 1, 50, new int[]{6, 30, 90, 270, 400, 550});
        Square s9 = new Property("Cissie Youngs", 120, 1, 50, new int[]{8, 40, 100, 300, 450, 600});
        Square s10 = new Idle("Just visiting");//jail

        Square s11 = new Property("Annie Macs", 120, 2, 100, new int[]{10, 40, 100, 300, 450, 600});
        Square s12 = new Utility("Electricty Company", 150, 9);//elec
        Square s13 = new Property("Bradleys", 120, 2, 100, new int[]{10, 40, 100, 300, 450, 600});
        Square s14 = new Property("Tom Lynches", 120, 2, 100, new int[]{12, 40, 100, 300, 450, 600});
        Square s15 = new Railway("Speedos", 8);//railway

        Square s16 = new Property("Tom Barrys", 180, 3, 100, new int[]{14, 40, 100, 300, 450, 600});
        Square s17 = new Card("Community Chest", 0);
        ;
        Square s18 = new Property("Barberella", 200, 3, 100, new int[]{14, 40, 100, 300, 450, 600});
        Square s19 = new Property("The Flying Enterprise", 200, 3, 100, new int[]{16, 40, 100, 300, 450, 600});
        Square s20 = new Idle("Free Parking");//free parking

        Square s21 = new Property("Costigans", 220, 4, 150, new int[]{18, 40, 100, 300, 450, 600});
        Square s22 = new Card("Chance", 1);
        Square s23 = new Property("Spalpín Fánach", 220, 4, 150, new int[]{18, 40, 100, 300, 450, 600});
        Square s24 = new Property("Old Oak", 240, 4, 150, new int[]{20, 40, 100, 300, 450, 600});
        Square s25 = new Railway("McDonalds", 8);

        Square s26 = new Property("An Bróg", 260, 5, 150, new int[]{22, 40, 100, 300, 450, 600});
        Square s27 = new Property("Fran Well", 260, 5, 150, new int[]{22, 40, 100, 300, 450, 600});
        Square s28 = new Utility("Water Works", 150, 9);//waterworks
        Square s29 = new Property("Sober Lane", 280, 5, 150, new int[]{22, 40, 100, 300, 450, 600});
        Square s30 = new goToJail();//Go to jail

        Square s31 = new Property("Vicarstown", 300, 6, 200, new int[]{26, 40, 100, 300, 450, 600});
        Square s32 = new Property("The Friary", 300, 6, 200, new int[]{26, 40, 100, 300, 450, 600});
        Square s33 = new Card("Community Chest", 0);//com chest
        Square s34 = new Property("Charlies", 320, 6, 200, new int[]{28, 40, 100, 300, 450, 600});
        Square s35 = new Railway("Four Star", 8);//railway

        Square s36 = new Card("Chance", 1);
        Square s37 = new Property("Havanas", 350, 7, 200, new int[]{35, 40, 100, 300, 450, 600});
        Square s38 = new luxuryTax();//luxury tax
        Square s39 = new Property("Voodoo", 400, 7, 200, new int[]{50, 40, 100, 300, 450, 600});



        this.squares.add(s0);
        this.squares.add(s1);
        this.squares.add(s2);
        this.squares.add(s3);
        this.squares.add(s4);
        this.squares.add(s5);
        this.squares.add(s6);
        this.squares.add(s7);
        this.squares.add(s8);
        this.squares.add(s9);
        this.squares.add(s10);
        this.squares.add(s11);
        this.squares.add(s12);
        this.squares.add(s13);
        this.squares.add(s14);
        this.squares.add(s15);
        this.squares.add(s16);
        this.squares.add(s17);
        this.squares.add(s18);
        this.squares.add(s19);
        this.squares.add(s20);
        this.squares.add(s21);
        this.squares.add(s22);
        this.squares.add(s23);
        this.squares.add(s24);
        this.squares.add(s25);
        this.squares.add(s26);
        this.squares.add(s27);
        this.squares.add(s28);
        this.squares.add(s29);
        this.squares.add(s30);
        this.squares.add(s31);
        this.squares.add(s32);
        this.squares.add(s33);
        this.squares.add(s34);
        this.squares.add(s35);
        this.squares.add(s36);
        this.squares.add(s37);
        this.squares.add(s38);
        this.squares.add(s39);

        this.users.add(u1);
        this.users.add(u2);
        this.users.add(u3);
        this.users.add(u4);
//        this.users.add(u5);
//        this.users.add(u6);
//        this.users.add(u7);
//        this.users.add(u8);
    }



}
