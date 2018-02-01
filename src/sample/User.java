package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by Brian on 08/02/2017.
 */
public abstract class User {


    //Users should probably know the game that they're in rather than being fed the game
    //Even like learnGame(Game g) function at the start of every

    //************************************** Variables ****************************************************

    Game game;
    String userName;
    private int userID;

    public abstract boolean roll();

    List<Integer> streetsOwned = new ArrayList<>();
    List<Purchasable> owned = new ArrayList<>();

    int turnsInJail = 0;
    int getOutOfJail = 0;
    boolean isInJail = false;

    //************************************** Set Up ****************************************************

    /**
     * Sets the game that user is a part of.
     * @param g The game object that the user is playing in.
     */
    public void setGame(Game g) {
        this.game = g;
    }

    /**
     * Initialised the User.
     * @param name
     * @param userID
     */
    public User(String name, int userID) {
        this.userName = name;
        this.userID = userID;
    }

    /**
     * A game asks a user for money using this function. If a user cannot afford the amount asked they may try to
     * find money by selling and mortgaging. If they cannot find the money, the user returns false, otherwise they
     * return true.
     * @param i The amount requested.
     * @return True if they can find the money.
     */
    public abstract boolean findFunds(int i);


    //************************************** Core *******************************************************


    /**
     * Update updates the users information. Currently just updates them on what properties they own.
     * They can access everything from the game anyway, but populating it every time is wasteful.
     */
    public void update (){
        this.streetsOwned = game.streetsOwned(this);
        owned = game.propertiesOwned(this);
    }

    //************************************** Buying ****************************************************

    /**
     * Called when a user is given the option to buy a property.
     * @param p The property they are being offered.
     * @param cost The price the property is being offered at.
     * @return True if the user chooses to buy it.
     */
    public abstract boolean buyOption(Purchasable p, int cost);

    public boolean tradeOption(Property theirs, Property yours){
        if(theirs.getCost() > yours.getCost()){
            return true;
        } else return false;
    }

    //************************************** Buying help ***************************************************

    /**
     * Generates the string presenting a user with the properties they can build on.
     * @return string of options.
     */
    public String buildString() {
        if(listOfPropertiesToBuildOn().isEmpty()){
            return "No properties to build";
        }
        String s = "";
        int option = 0;
        List<Property> properties = listOfPropertiesToBuildOn();
        if (!properties.isEmpty()) {
            for (Property p : properties) {
                s += (p.getName() + " currently has " + p.getNumHouses() + " houses");
                s += ("To built a house here for $" + p.getHouseCost() + ", enter: " + option);
                option++;
            }
            s += ("To exit, enter: " + option);//choice = input;
        } else s += ("you have nothing to build on?");
        return s;
    }
    /**
     * Generates the string presenting a user with the properties they can sell houses on.
     * @return string of options.
     */
    public String sellString(){
        String s = "";
        if (developedToSell()) {
            int option = 0;
            List<Property> properties = listOfDevelopedToSell();
            for (Property p : properties) {
                s += (p.getName() + " currently has " + p.getNumHouses() + " houses");
                s+=("To sell a house here for $" + p.getHouseCost() + ", enter: " + option);
                option++;
                //To build here enter option
                //option++;
            }
            s+=("To exit, enter: " + option);//choice = input;
            return s;
        }
        else return "No houses to sell.";
    }
    /**
     * Generates the string presenting a user with the properties they can mortgage.
     * @return string of options.
     */
    public String mortgageString(){
        //System.out.println("MS for " + this);
        String s = "";
        if (undevelopedToMortgage()) {
            //System.out.println("U 2 M");
            int option = 0;
            List<Purchasable> purchasables = listOfUndevelopedToMortgage();
            for (Purchasable p : purchasables) {
                s+=(p.getName() + " has a mortgage value $" + p.getMortgageValue());
                s+=("\nTo mortgage " + p.getName() + " for $" + p.getMortgageValue() + ", enter: " + option + "\n\n");
                option++;
            }
            s+=("\nTo exit, enter: " + option);//choice = input;
            //System.out.println(s);
            return s;
        } else return("you have nothing to mortgage?");
    }
    /**
     * Generates the string presenting a user with the properties they can unmortgage.
     * @return string of options.
     */
    public String unmortgageString(){
        String s = "";
        if (mortgagedToUnmortgage()) {
            int option = 0;
            List<Purchasable> mortgaged = listOfMortgaged();
            for (Purchasable p : mortgaged) {
                s+=("To pay off mortgage on " + p.getName() + " for $" + p.getMortgageValue()
                        + ", enter: " + option);
                option++;
            }
            s+=("To exit, enter: " + option);//choice = input;
            return s;
        } else return"you have nothing to unmortgage?";
    }

    /**
     * Gets the list of properties with houses on them owned by the user.
     * @return List of developed properties.
     */
    public List<Property> builtProperties(){
        List<Property> built = new ArrayList<>();
        for(Purchasable p : owned){
            if((p instanceof Property)&&(((Property) p).hasHouses())){
                built.add((Property) p);
            }
        }
        return built;
    }

    /**
     * Gets a value of a property. If the property is on a street the user can still own, it has a greater value
     * @param p The property being valued.
     * @return The value of the property to the user.
     */
    double getValue(Purchasable p){
        double multiplier = 1.2;
        if((p instanceof Property) && canGetStreet(p.getID())){multiplier*=2;}
        return(p.getCost()*multiplier);
    }

    /**
     * Finds if the user can get the street.
     * @param street The ID of the street being queried.
     * @return True if the user can still own the street.
     */
    boolean canGetStreet(int street){
        if(street < 8 && street >= 0){
            for(Property p : game.streets.get(street)){
                if(p.hasOwner() && !p.getOwner().equals(this)){
                    return false;
                }
            }
        }return true;
    }

    //************************************** Selling ****************************************************


    /**
     * Checks if the user has properties to build on.
     * @return True if the user has properties to build on.
     */
    public boolean propertiesToBuildOn() {
        return (listOfPropertiesToBuildOn().size() > 0);
    }
    /**
     * Gets the list of properties the user can build on.
     * @return A list of properties the user can build on.
     */
    public List<Property> listOfPropertiesToBuildOn(){
        List<Property> listOfPropertiesToBuildOn = new ArrayList<>();
        //List<User> users = new ArrayList<>();
        for(int streetID : streetsOwned){
            for(Property p : game.buildablePropertiesInStreet(streetID)){
                //System.out.println("Build On ADDING: " + p.getName());
                listOfPropertiesToBuildOn.add(p);
            }
        }
        return listOfPropertiesToBuildOn;
    }

    /**
     * Build a house on a given property.
     * @param p Build house on this Property.
     */
    public void buildOnProperty(Property p){if(getMoney()>p.getHouseCost()){
        game.buildHouse(p);}}
    /**
     * Checks if the user has properties to mortgage.
     * @return True if the user has properties to mortgage.
     */
    public boolean undevelopedToMortgage(){
        return listOfUndevelopedToMortgage().size() > 0;
    }
    /**
     * Gets the list of properties the user can mortgage.
     * @return A list of properties the user can mortgage.
     */
    public List<Purchasable> listOfUndevelopedToMortgage(){
        List<Purchasable> listOfUndeveleopedToMortgage = new ArrayList<>();
        //List<User> users = new ArrayList<>();
        for(Purchasable p : owned){
            if(!p.isMortgaged()){// && (p instanceof Property) && !((Property) p).hasHouses()){
                //System.out.println("Found " + )
                if(!((p instanceof Property) &&((Property) p).hasHouses())){
                    listOfUndeveleopedToMortgage.add(p);
                }

            }
        }
        return listOfUndeveleopedToMortgage;
    }

    /**
     * Mortgages a given property.
     * @param p The property to be mortgaged.
     */
    public void mortgagePurchasable(Purchasable p){
        game.mortgageProperty(p);}
    /**
     * Checks if the user has properties with houses to sell.
     * @return True if the user has properties with houses to sell.
     */
    public boolean developedToSell(){
        return listOfDevelopedToSell().size()>0;
    }
    /**
     * Gets the list of properties with houses to sell.
     * @return A list of properties with houses to sell.
     */
    public List<Property> listOfDevelopedToSell() {
        //System.out.println("Looking for Houses to sell");
        List<Property> built = builtProperties();
        List<Property> maxbuilt = builtProperties();
        int maxHouses = 0;
        //System.out.println("BUILT1: " + built.size());
        for (int i : streetsOwned) {
            for (Property p : built) {
                if (p.getID()== i && p.getNumHouses() > maxHouses) {
                    maxHouses = p.getNumHouses();
                }
            }
            //System.out.println("BUILT2: " + built.size());
            for (Property p : built) {
                if (p.getID()== i && p.getNumHouses() >= maxHouses) {
                    maxbuilt.add(p);
                }
            }
        }
        //System.out.println("Found " + built.size() + " properties with " + maxHouses + " houses");
        String s = "BUILDABLE";
        for(Property p2 : maxbuilt){
            s += p2.getName() + ", ";
        }
        //System.out.println(s);
        return built;
    }

    /**
     * Sell a house on given property.
     * @param p The property with a house to be sold on.
     */
    public void sellHouse(Property p){
        game.sellHouse(p);}
    /**
     * Checks if the user has mortgaged properties.
     * @return True if the user has mortgaged properties.
     */
    public boolean mortgagedToUnmortgage(){
        return listOfMortgaged().size()>0;
    }
    /**
     * Gets the list of mortgaged properties.
     * @return A list of mortgaged properties.
     */
    public List<Purchasable> listOfMortgaged(){
        //System.out.println("Looking for mortgaged");
        List<Purchasable> mortgaged = new ArrayList<>();
        for(Purchasable p : owned){
            if(p.isMortgaged()){
                mortgaged.add(p);
            }
        }
        //System.out.println("Found " + built.size() + " houses with " + maxHouses + " houses");
        return mortgaged;
    }

    /**
     * Unmortgages specified property
     * @param p The property to be unmortgaged.
     */
    public void unmortgaged(Purchasable p){
        game.unMortgageProperty(p);
    }

//    public Purchasable smallestMortgage2(){
//        System.out.println("SIZE: " + listOfMortgaged().size());
//        int smallest = 0;
//        Purchasable purchasable = null;
//        for(Purchasable p : listOfMortgaged()){
//            System.out.println("P: " + p.getName() + " mVal: " + p.getMortgageValue());
//            if(smallest == 0){
//                smallest = p.getMortgageValue();
//                purchasable = p;
//                System.out.println("Smallest: " + p.getName() + " mVal: " + p.getMortgageValue());
//            } else if( p.getMortgageValue() < smallest){
//                System.out.println("Smaller: " + p.getName() + " mVal: " + p.getMortgageValue());
//                smallest = p.getMortgageValue();
//                purchasable = p;
//            } System.out.println("Bigger: " + p.getName() + " mVal: " + p.getMortgageValue());
//        }
//        if(purchasable!=null) {
//            System.out.println(purchasable.getName());
//        } else  System.out.println("ERROR");
//        return purchasable;}

    /**
     * Finds the house with the most expensive property you can morthage.
     * @return Property with the biggest mortgage.
     */
    public Purchasable biggesttMortgage(){
        //returns the biggest you can afford
        int biggest = 0;
        Purchasable purchasable = null;
        for(Purchasable p : listOfUndevelopedToMortgage()){
            if( p.getMortgageValue() > biggest && p.getMortgageValue() >= getMoney()){
                biggest = p.getMortgageValue();
                purchasable = p;
            }
        }
        return purchasable;
    }




    //************************************** Boring ****************************************************

    /**
     * Get the user's user name.
     * @return The username string.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Gets a string detailing the properties they own.
     * @return String containing their properties.
     */
    public String propertyString(){
        String s = "";
        s += "They own ";
        if(owned.size() == 0){
            s += " nothing. ";
        }
        else if (owned.size() == 1){
            s += owned.get(0).getName() + ". ";
        } else {
            int i = 0;
            for(Purchasable p : owned) {
                i++;
                s += p.getName();

                if (i == owned.size()) {
                    s += ". ";
                } else if (i == owned.size() - 1) {
                    s += " and ";
                } else {
                    s += ", ";
                }
            }
        }
        return s + "\n";

    }

    /**
     * Gets a string of the users info.
     * @return A string containing their money and properties.
     */
    public String getInfo(){
        update();
        String s = "";
        s += getUserName() + " has $" + getMoney() + ". They have " + getOutOfJail + " Jail Cards.\n";
        s += "They own " + streetsOwned.size() + " streets";
        if(!streetsOwned.isEmpty()){
            //s += "Street " + streetsOwned.get(0) + " is owned by " + game.ownerOfStreet(streetsOwned.get(0));

            for(int i : streetsOwned){
                s += ", " + i;
            }
        }s += ".\n";


        int houses = 0;
        s += propertyString();

        return s + "\n";
    }

    /**
     * Gets the users ID number.
     * @return User ID.
     */
    public Integer getUserID() {return userID;}

//    public void printSituation(){
//        //Just for bug testing
//        System.out.println("Properties with houses: ");
//        for(Purchasable p : owned){
//            if(p instanceof Property && ((Property) p).hasHouses()){
//                System.out.println(p.getName() + " has " + ((Property) p).getNumHouses() + "houses");
//            }
//        }
//        System.out.println("Unmortgaged and undeveloped: ");
//        for(Purchasable p : owned){
//            if(!p.isMortgaged()){
//                if(p instanceof Property && !((Property) p).hasHouses()){
//                    System.out.println(p.getName());
//                } else if (!(p instanceof Property)){
//                    System.out.println(p.getName());
//                }//Could be better, I know.
//            }
//        }
//        System.out.println("Already mortgaged: ");
//        for(Purchasable p : owned){
//            if(p.isMortgaged()){
//                System.out.println(p.getName());
//            }
//        }
//    }

    /**
     * Finds how much money the user has.
     * @return The users money as an int.
     */
    public int getMoney(){
        return game.getMoney(this);
    }

    /**
     * Gets how much houses the user owns.
     * @return Number of houses owned.
     */
    int numHouses(){
        int numHouses = 0;
        for(Purchasable p : owned){
            if((p instanceof Property) && ((Property)p).getNumHouses()<5){
                numHouses += ((Property)p).getNumHouses();
            }
        }
        return numHouses;
    }

    /**
     * Gets the number of hotels the user owns.
     * @return The number of hotels owned by user.
     */
    int numHotels(){
        int numHotels = 0;
        for(Purchasable p : owned){
            if((p instanceof Property) && ((Property)p).getNumHouses()==5){
                numHotels ++;
            }
        }
        return numHotels;
    }

    /**
     * Calculates the net worth of the user, which is their money, the value of their property and the value of the
     * houses they own.
     * @return The user's networth.
     */
    int getWorth(){
        int worth = getMoney();
        for(Purchasable p : owned){
            if(!p.isMortgaged()){
                worth += p.getCost();
            } else { worth += p.getCost()/2;}
            if(p instanceof Property){
                worth += ((Property) p).getNumHouses()*((Property) p).getHouseCost();
            }
        }
        return worth;
    }

    /**
     * The toString method returns the users user name.
     */
    public String toString() { return this.getUserName(); }

    //************************************** Jail ****************************************************

    /**
     * Returns whether or not the user is in jail.
     * @return True if they are in Jail.
     */
    public boolean isUserInJail(){return this.isInJail;}

    /**
     * Sets the users inJail flag to true.
     */
    public void putInJail(){
        isInJail = true;
        turnsInJail = 0;
    }

    /**
     * Sets the users inJail flag to false.
     */
    public void freeFromJail(){
        isInJail = false;
        turnsInJail = 0;
    }

    /**
     * Gets the number of turns the user has been in jail for.
     * @return The number of turns the user has been in Jail.
     */
    public int getTurnsInJail() {
        return turnsInJail;
    }

    /**
     * Increase the count of the users turns in jail.
     */
    public void increaseTurnsInJail(){
        this.turnsInJail ++;
    }

    /**
     * Asks the user if they want to roll or bail.
     * @param bail The amount of bail being charged.
     * @return True if they want to pay bail, false if the want to roll.
     */
    abstract boolean rollOrBail(int bail);

    /**
     * Finds if the user has a get out of jail card.
     * @return True if the user has a get out of jail card.
     */
    public boolean hasJailCard(){ return (getOutOfJail > 0);}

    /**
     * Increases the number of jail cards a user has by 1.
     */
    public void giveJailCard(){
        this.getOutOfJail += 1;
    }

    /**
     * Reduces the number of jail cards by 1.
     */
    public void useJailCard(){
        //System.out.println("Usin");
        //Needs to check if they have it
        this.getOutOfJail -= 1;
    }

}
