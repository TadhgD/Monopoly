package sample;

/**
 * Created by Brian on 08/02/2017.
 */
public class Utility extends Purchasable {
    public Utility(String name, int cost, int id) {
        super(name, cost, id );
    }
    int[] rents = {4,10};

    //public Utility(String name, int cost, int id){
          //  superthis.name = name;
        //    this.cost = cost;

    //

    public String getString(){
        return "Name: " + this.name + ", Cost: " + this.cost + " , Owner: " + this.getOwner();
    }



    int getRent(Game game) {
        /*
        int multiplier = 2;
        if (game.squares[5].owner == game.squares[10]){
            multiplier = 10;
        }
        return rent = game.diceroll * multiplier;*/


        int numberOwned = game.numUtilitiesOwned(this.getOwner());
        int rent = game.dice.getTotal()*rents[numberOwned-1];
        System.out.println(this.getOwner() + " owns a total of " + numberOwned + " utilities. Rent is multiplied by "
                + rents[numberOwned -1] + ".");
        return rent;
    }
}
