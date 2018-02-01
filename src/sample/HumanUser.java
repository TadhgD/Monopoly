package sample;

import sample.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Brian on 27/02/2017.
 */
public class HumanUser extends User {

    Scanner scan = new Scanner(System.in);

    @Override
    public boolean roll() {
        return true;
//        System.out.println("Enter 1 to roll: ");
//        int answer = scan.nextInt();


    }

    public HumanUser(String name, int userID) {
        //These players need to be added from code within the game
        super(name, userID);
    }

    @Override
    public boolean findFunds(int i) {
        return(i<=getMoney());
    }

    public boolean buyOption(Purchasable p, int cost){
        return true;
//        boolean loop = true;
//        boolean response = false;
//        int answer;
//        if(getMoney()<cost){return false;}//One day, they should be able to sell things
//        while(loop) {
//            System.out.println("Heres");
//
//            System.out.println("You have $" + getMoney() + "Do you want to buy " + p.getName() + " for $ " + cost + "?");
//            System.out.println("Enter 0 for No, 1 for Yes");
//            answer = scan.nextInt();
//            if(answer == 0){response = false; loop = false;}
//            else if(answer == 1){response = true; loop = false;}
//            System.out.println("You entered: " + answer);
//        }
//        return response;
    }

    public void buildOption() {
        if (streetsOwned.size() > 0) {
            int cheapestStreet = streetsOwned.get(0);
            //[1,5];
            if (game.isStreetBuildable(cheapestStreet)) {
                //literally if the cheepest street is buildable,
                //this will change obvz needs to go through the other streets
                Property p = game.buildablePropertiesInStreet(cheapestStreet).get(0);
                if(!(p.isMortgaged()) && p.getHouseCost()<this.getMoney()){
                    game.buildHouse(p);
                }
            }
        }
    }
    //It shouldnt ever call this

    public boolean rollOrBail(int bail){
        return true;
//        //This loop is v bad im sorry
//        boolean loop = true;
//        boolean response = false;
//        int answer;
//        //while(loop) {
//
//        System.out.println("Do you want to pay bail $" + game.bail + ", or try to roll a double? You have $" + getMoney());
//        System.out.println("Enter 0 to pay bail, 1 to try to roll");
//        answer = scan.nextInt();
//        if(answer == 0){System.out.println("A");response = true; loop = false;}
//        else if(answer == 1){System.out.println("B");response = false; loop = false;turnsInJail++;}
//        System.out.println("You entered: " + answer);
//        //}
//        System.out.println("Free");
//        return response;


    }

    public void manage(){
        System.out.println("Do you want to manage? Yes = 1, No = 0.");
        int manage = scan.nextInt();

        while(manage == 1){
            System.out.println("Properties to build on: " + propertiesToBuildOn());
            System.out.println("Properties with houses to sell: " + developedToSell());
            System.out.println("Properties to mortgage: " + undevelopedToMortgage());
            System.out.println("Properties to unmortgage: " + mortgagedToUnmortgage());
            System.out.println("To build: 0, To sell: 1, To mortgage: 2, To unmortgage: 3, To exit: 4");
            int answer = scan.nextInt();
            if(answer >= 0 && answer <= 3) {
                if (answer == 0) {
                    userBuildHouse();
                } else if (answer == 1) {
                    userSellHouse();
                } else if (answer == 2) {
                    userMortgage();
                } else if (answer == 3 ){
                    userUnmortgage();
                }//sell}
                System.out.println("Do you want to manage more? Yes : 1, No : 0");
                manage = scan.nextInt();
            }
        }System.out.println("Done so");
    }

    public void userBuildHouse(){
        if (propertiesToBuildOn()) {
            int option = 0;
            List<Property> properties = listOfPropertiesToBuildOn();
            for (Property p : properties) {
                System.out.println(p.getName() + " currently has " + p.getNumHouses() + " houses");
                System.out.println("To built a house here for $" + p.getHouseCost() + ", enter: " + option);
                option++;
            }
            System.out.println("To exit, enter: " + option);//choice = input;
            int choice = scan.nextInt();
            if (choice < properties.size()) {//Then it's not out of index
                Property p = properties.get(choice);
                System.out.println("You chose " + p.getName() + " c: " + p.getHouseCost() + " m: " + getMoney());

                if (p.getHouseCost() < getMoney()) {
                    buildOnProperty(p);
                } else {
                    System.out.println("Not enough money");
                }
            }
        } else System.out.println("you have nothing to build on?");
    }

    public void userSellHouse(){
        if (developedToSell()) {
            int option = 0;
            List<Property> properties = listOfDevelopedToSell();
            for (Property p : properties) {
                System.out.println(p.getName() + " currently has " + p.getNumHouses() + " houses");
                System.out.println("To sell a house here for $" + p.getHouseCost() + ", enter: " + option);
                option++;
                //To build here enter option
                //option++;
            }
            System.out.println("To exit, enter: " + option);//choice = input;
            int choice = scan.nextInt();
            if (choice < properties.size()) {//Then it's not out of index
                Property p = properties.get(choice);
                System.out.println("You chose " + p.getName() + " c: " + p.getHouseCost() + " m: " + getMoney());
                sellHouse(p);
            }


            //else print "exiting"
            //If they don't have the money: print
            //else: g.build house
            //
        } else System.out.println("You have no houses to sell?");
    }

    public void userMortgage(){
        if (undevelopedToMortgage()) {
            int option = 0;
            List<Purchasable> purchasables = listOfUndevelopedToMortgage();
            for (Purchasable p : purchasables) {
                System.out.println(p.getName() + " has a mortgage value $" + p.getMortgageValue());
                System.out.println("To mortgage " + p.getName() + " for $" + p.getMortgageValue() + ", enter: " + option);
                option++;
            }
            System.out.println("To exit, enter: " + option);//choice = input;
            int choice = scan.nextInt();
            if (choice < purchasables.size()) {//Then it's not out of index
                Purchasable p = purchasables.get(choice);
                System.out.println("You chose " + p.getName());
                mortgagePurchasable(p);
            }


            //else print "exiting"
            //If they don't have the money: print
            //else: g.build house
            //
        } else System.out.println("you have nothing to mortgage?");
    }

    public void userUnmortgage(){
        if (mortgagedToUnmortgage()) {
            int option = 0;
            List<Purchasable> mortgaged = listOfMortgaged();
            for (Purchasable p : mortgaged) {
                System.out.println("To pay off mortgage on " + p.getName() + " for $" + p.getMortgageValue()
                        + ", enter: " + option);
                option++;
            }
            System.out.println("To exit, enter: " + option);//choice = input;
            int choice = scan.nextInt();
            if (choice < mortgaged.size()) {//Then it's not out of index
                Purchasable p = mortgaged.get(choice);
                System.out.println("You chose " + p.getName());
                mortgagePurchasable(p);
            }
        } else System.out.println("you have nothing to unmortgage?");
    }

}
