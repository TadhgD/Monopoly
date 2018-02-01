package sample;

/**
 * Created by Brian on 08/02/2017.
 */

public class Property extends Purchasable {
    //need to change mortgages, they're just the cost/2 no need to manual enter them
    private int houseCost;



    private int numHouses = 0;
    private int[] rents = new int[6];
    //User owner = null;

    public String getName() {
        String s = name;

        if(isMortgaged()){
            s+="(M)";
        } else if(hasHouses()){
            s+= "(" + getNumHouses() + ")";
        }
        return s;
    }



    public int getNumHouses() {
        return numHouses;
    }

    public boolean hasHouses(){
        return numHouses > 0;
    }

    public Property(String name, int cost, int id, int houseCost, int[] rents){
        super(name, cost, id);
        setHouseCost(houseCost);
        this.rents = rents;
    }

    public void setHouseCost(int houseCost) {
        this.houseCost = houseCost;
    }

    public int getHouseCost(){
        return this.houseCost;
    }

    public void buildHouse(){
        if(numHouses < 5){
            this.numHouses += 1;
        } else {
            //throw exception
        }

    }

    public void sellHouse(){
        //check house num
        if(this.numHouses > 0){
            this.numHouses -= 1;
        }else System.out.println("Exception here no houses to sell houses on " + getName());
    }

    int getRent(Game game) {
        return rents[numHouses];
    }

    public int totalHouseValue(){
        //this is used for income tax
        //returns the total value of the houses built on the property
        return (this.houseCost * this.numHouses);

    }

    public boolean hasHotel(){
        if(this.numHouses>=5){
            return true;
        } else {
            return false;
        }
    }

    public String toString(){
        return String.format("%-25s Cost: %-5d Owner: %-10s, Houses: %d",
                this.name, this.cost, this.getOwner(), this.numHouses);
    }

}
