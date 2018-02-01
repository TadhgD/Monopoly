package sample;

/**
 * Created by Brian on 08/02/2017.
 */
public class Railway extends Purchasable {
    //You don't need to initialise the cost, they're all 200
    //int cost = 200;

    int[] rents = {25, 50, 100, 200};

    public Railway(String name, int id){
        //cost = 200;
        super(name, 200, id);
    }

    int getRent(Game game) {
        int numberOwned = game.numRailwaysOwned(this.getOwner());
        System.out.println(this.getOwner() + " owns a total of " + numberOwned + " Railroads. The rent is $" + rents[numberOwned -1]);
        return rents[numberOwned -1]; // -1 because of indexing


        //find how many railway properties owned by this.user
        //rent = this.rents[howManyOwned];
        //return rent;
    }

    public String toString(){
        return String.format("%-25s Cost: %-5d Owner: %-10s",
                this.name, this.cost, this.getOwner());
    }
}
