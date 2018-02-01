package sample;

/**
 * Created by Brian on 08/02/2017.
 */
abstract class Purchasable extends Square{
    private User owner = null;
    int cost;
    int id;
    boolean mortgaged = false;




    public Purchasable(String name, int cost, int id){
        //not sure about the whole id thing, makes sense for properties
        //But it might be okay for the others too
        super(name);
        this.id = id;
        setCost(cost);
    }

    public void resetOwner(){
        owner = null;

    }

    abstract int getRent(Game game);

    public int getID() {
        return id;
    }

    public void setOwner(User user){
        this.owner = user;
    }

    public User getOwner(){
        return this.owner;
        //some safety in here, if owner != null etc
    }

    public boolean hasOwner(){
        //System.out.println(this.getName() + " queried");
        if (this.owner != null){
            return true;
        } else {
            return false;
        }
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return this.cost;
    }

    public int getMortgageValue() {
        return cost/2;
    }

    public void mortgage(){
        mortgaged = true;
    }

    public void unMortgage(){
        mortgaged = false;
    }

    public boolean isMortgaged(){
        return mortgaged;
    }

}
