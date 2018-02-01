package sample;

import sample.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by Brian on 08/02/2017.
 */
public class BotUser1 extends User {

    //Users should probably know the game that they're in rather than being fed the game
    //Even like learnGame(Game g) function at the start of every

    //************************************** Variables ****************************************************


    @Override
    public boolean roll() {
        return true;
    }

    public BotUser1(String name, int userID) {
        super(name, userID);
        //System.out.println("bUc: " + name);

    }

    @Override
    //If the user has no money, they can manage their things to raise cash, selling houses and mortgaging properties.
    public boolean findFunds(int i) {
        if(i<getMoney()){
            return true;
        } else{ //System.out.println("NEEDS FUNDS I: " + i + " MONEY: " + getMoney());
            //System.out.println("I: " + i + " M: " + getMoney() + "UtM: " + undevelopedToMortgage() + " DtS:" + developedToSell());
            int r = 0;
            while((i >= getMoney()) && (undevelopedToMortgage() || developedToSell())){
                r++;
                //System.out.println("round: " + r);
                System.out.println("I: " + i + " M: " + getMoney() + "UtM: " + undevelopedToMortgage() + " DtS:" + developedToSell());
                if(undevelopedToMortgage()){
                    //System.out.println("UtM");
                    mortgageSomething();
                } else if (developedToSell()){
                    //System.out.println("SS");
                    sellSomething();
                }
            } //System.out.println("FOUND FUNDS I: " + i + " MONEY: " + getMoney());
        }
        return i<getMoney();
    }
    //They decide whether or not they wan to buy something
    public boolean buyOption(Purchasable p, int cost){
        if(p.getID() < 8 || canGetStreet(p.getID())){
            return (cost < this.getMoney() && cost < getValue(p));
        } else {return (cost < this.getMoney() && cost < getValue(p)*10);}
        //System.out.println("Offered house: " + p.getName());
        //System.out.println(p.getCost() < this.money);
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

    //Choose whether to roll or to bail.
    public boolean rollOrBail(int bail){
        //true for bail, false for roll
        return(getMoney()>bail);
    }


    //The lets the bot spend moeney, building houses and unmortgaging properties if they have the money.
    public void manage() {
        //while stuff to build, build
        //then, while stuff to unmortgage, unmortgage
        if(propertiesToBuildOn() && listOfPropertiesToBuildOn().get(0).getHouseCost()<=getMoney()){
            buildOnSomething();
        }
        while(mortgagedToUnmortgage() && listOfMortgaged().get(0).getCost() <= getMoney()){
            unmortgageSomething();
        }
    }
    //The bot picks and mortgages something
    public void mortgageSomething(){
        //System.out.println("In mortgage something");
        List<Purchasable> list = listOfUndevelopedToMortgage();
        if(!list.isEmpty()){
            Purchasable p = list.get(0);
            game.turnString += this + " mortgages " + p.getName() + " for $" + p.getMortgageValue() + ".\n";
            game.mortgageProperty(p);
        }
    }
    //The bot unmortages something.
    public void unmortgageSomething(){
        if(mortgagedToUnmortgage()){
            int choice = 1;
            Purchasable p;
            if(choice == 1) {
                p = listOfMortgaged().get(0);
            } else {
                p = biggesttMortgage();
            }
            //System.out.println("UNMORTGAGING: " + p);
            unmortgaged(p);

        }
    }

    //Called when bot wants to build something, picks what house to buiild on
    public void buildOnSomething(){
        //System.out.println("Building on: " + listOfPropertiesToBuildOn().get(0));
        buildOnProperty(listOfPropertiesToBuildOn().get(0));
    }

    //Called when the bot wants to sell a house.
    public void sellSomething(){
        List<Property> list = listOfDevelopedToSell();
        if(!list.isEmpty()){
            Property p = list.get(0);
            game.turnString += this + " sells a house on " + p.getName() + " for $" + p.getHouseCost() + ".\n";
            game.sellHouse(p);
        }
    }//Called when there is buildings to sell. Choose what property to sell. Only for bots.

}
