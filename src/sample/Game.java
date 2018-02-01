package sample;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Brian on 07/02/2017.
 */
//make one rent function
//Squares should be initialised with the Game which they belong to.
//Change the go implementation so that they get it when they pass rather than land on
//limit on number of get out of jail cards... like not that hard but not doing it
//static int in Card class // when a get out of jail card is issued ++ // if someone gets that card and there's too much out
//give them another card. // when someone uses a get out of jail card, decrease this int // you can do this by squares.get()
// and get any card, because it's a static int. // we'll just ignore that they can come from two separate decks //

//Make a mode where all users can borrow as much money as they want
//At a random round, the economy crashes, users must pay back
//A player called VultureFund buys all their debts
//The VultureFund does not pay rent, trade and of course are immune from paying tax
//Bots are either normal, or developers
//Developers borrow money
//Auctioning will be handy here
//Property inflation is a thing, the more people bit each round the value of houses increases
//Really funny lol

public class Game {

    //***************************************** Running Game  ****************************************8

    /**
     * Initialises the game.
     * Adds the users to the game and sets up the squares.
     */
    public Game() {
        setUp setUp = new setUp();

        squares = setUp.squares;
        for (User u : setUp.users) {
            addUser(u);
        }
        setUpStreets();
        currentUser = users.get(0);
        currentUserInt = users.size();
    }

    //*********************************** Game Variables **************************************************

    private int bail = 50;
    private int currentUserInt = 0;
    private boolean doubleRoll = false;
    Dice dice = new Dice();
    String turnString;
    boolean run = true;
    boolean card = false;
    private boolean autoPrintBoard = true;
    private int round = 0;
    private User currentUser;
    List<Square> squares = new ArrayList<>();
    List<User> users = new ArrayList<>();
    ArrayList<ArrayList<Property>> streets = new ArrayList<ArrayList<Property>>();
    Map<User, Integer> userPositions = new HashMap<User, Integer>();
    private Map<User, Integer> userMoney = new HashMap<User, Integer>();


    //************************************** Setting Up ****************************************************

    /**
     * Adds a user to the game.
     * @param u The user being added.
     */
    private void addUser(User u) {
        System.out.println("Adding " + u);
        this.users.add(u);
        this.userPositions.put(u, 0);
        this.userMoney.put(u, 1500);
        u.setGame(this);
    }

    /**
     * This function is called at initialisation. It puts the properties of the same street into a a street array.
     * It then puts these arrays into a larger array 'streets'.
     */
    private void setUpStreets() {//obvs will one day be in constructor
        for (int k = 0; k <= 8; k++) {
            List<Property> street = new ArrayList<>();
            for (Square square : squares) {
                if (square instanceof Property && ((Property) square).getID() == k) {
                    street.add((Property) square);
                }
            }
            streets.add((ArrayList<Property>) street);
            //Street street2 = new Street(street);
        }
    }

    //************************************** Gameplay Code *************************************************

    /**
     * Rolls the dice and moves the user the appropriate number of spaces.
     * @param u The current user who is rolling the dice.
     */
    private void makeRoll(User u) {
        dice.rollDice();
        if (dice.isDouble()) {
            this.doubleRoll = true;
        }
        moveUser(u, dice.getTotal());
    }

    /**
     * After a user has made their turn, this function process what happens based on what space they've landed on.
     * @param u The current user whose turn it is.
     */
    public void landedOnsSpace(User u) {
        Square currentPosition = squares.get(userPositions.get(u));
        //System.out.println(u + " rolled " + dice.getTotal() + " and moves to " + currentPosition.getName());
        if(!card){
            turnString += (u + " rolled " + dice.getTotal() + " and moves to " + currentPosition.getName() + ".\n");
        }

        if (currentPosition instanceof Purchasable) {

            if (!((Purchasable) currentPosition).isMortgaged()) {
                Purchasable property = (Purchasable) currentPosition;
                //System.out.println(property.getName() + " is owned by " + property.getOwner());
                if(!property.hasOwner()){
                    turnString += (property.getName() + " is unowned. \n");
                } else {
                    turnString += (property.getName() + " is owned by " + property.getOwner() + "\n");
                }


                if (!property.hasOwner()) {
                    if(currentUser instanceof HumanUser){
                        startBuyingInput(property);

                    } else {
                        if (u.buyOption(property, property.getCost())) {
                            //System.out.println(u + " buys " + property.getName() + " for $" + property.getCost());
                            turnString += (u + " buys " + property.getName() + " for $" + property.getCost() + ".\n");
                            buy(u, property);
                        } else {
                            turnString += (u + "doesn't buy " + property.getName() + ", so it goes to auction. But not really.\n");
                            auction(property, u);
                        }
                    }
                } else {
                    if (!(property.getOwner().equals(u))) {
                        if (property.getOwner().isUserInJail()) {
//                            System.out.println("Luckily the owner of " + property.getName()
//                                    + ", " + property.getOwner() + ", is in Jail.");
                            turnString += ("Luckily the owner of " + property.getName()
                                    + ", " + property.getOwner() + ", is in Jail.\n");
                        } else {
                            //If they haven't landed on their own space.
                            int rent = property.getRent(this);
                            if (hasFunds(u, rent)) {
                                takeMoney(u, rent);
                                giveMoney(property.getOwner(), rent);
                                //System.out.println(property.getName() + "'s rent of $" + rent + " is payed from " + u + " to " + property.getOwner());
                                turnString += (u + " pays " + "$" + rent + " to " + property.getOwner() + "\n");
                            } else {
                                turnString += (u + " can't afford the rent lol\n");
                                bankrupt(u);

                            }
                        }

                    }
                }
            }
            //This is the end of a mortgaged or not mortgaged if statement
            else {
                //System.out.println(currentPosition.getName() + " is mortgaged by " + ((Purchasable) currentPosition).getOwner());
                turnString += (currentPosition.getName() + " is mortgaged by " + ((Purchasable) currentPosition).getOwner());
            }

        } else if (currentPosition instanceof Actionable) {
            if(currentPosition instanceof Card){((Card) currentPosition).messageSetter(this, currentUser);}
            turnString += ((Actionable) currentPosition).getMessage() + "\n";
            ((Actionable) currentPosition).action(this, currentUser);
        }
    }

    /**
     * This is called at the start of a users turn. It updates the user on the state of the game.
     * It allows bot users to manage their properties. It then waits for the user to roll the dice, and calls the
     * landedOnSpace() function to process what happens next.
     * @param u The current user.
     */
    public void makeTurn(User u) {
        System.out.println(turnString);
        u.update();//this updates the user on the state of the game
        if(u instanceof BotUser1){((BotUser1) u).manage();}//manage their affairs i guess
        if(u.roll()){makeRoll(u);}
        landedOnsSpace(u);
        //They should manage and update here as well. Will put that in.
    }

    /**
     * Returns the user whose turn is next.
     * @return The user whose turn is next.
     */
    protected User getNextUser(){
        User tempUser;
        int tempInt = currentUserInt;
        tempInt++;
        if (tempInt >= users.size()) {
            tempInt = 0;
        }
        return users.get(tempInt);
    }

    //************************************** Running it Code *************************************************

    /**
     * Moves the current user onto the next user.
     */
    private void nextUser(){
        currentUserInt++;
        if (currentUserInt >= users.size()) {
            currentUserInt = 0;
        }
        currentUser = users.get(currentUserInt);

    }

    /**
     * Initialises the output string at the beginning of the turn.
     */
    public void beginTurnString(){
        turnString = "";
        turnString += "*** Round " + round + " ***\n\n";
        for(User u : users){
            turnString += u + " has $" + u.getMoney() + ".\n";
        }
        turnString += "\n********************************************************\n";


        //System.out.println("It's " + currentUser + "'s turn.");
        turnString += ("It's " + currentUser + "'s turn. \n");
        turnString += (currentUserInfo());

        turnString += "\n********************************************************\n";

    }
    /**
     * The game runs on turns. This function starts a new turn.
     * At the start of a turn, it checks if there are still multiple players. If not the remaining player has won.
     * Otherwise, it selects the current user. It processes the users turn based on whether or not they're in jail.
     */
    public void turn() {
        delay(200);
        System.out.print(turnString);
        printBoard();
        printInfo();
        printInfo();
        card = false;
        if(waitingForInput){
            System.out.println("Waiting for input");
            turnString += "\nWaiting for input...";
        }else {

            if (users.size() <= 1 || round > 500) {
                System.out.println(users.get(0) + " is the winner. The game is over.");
            }

            else {
                nextUser();
                if (currentUser.equals(users.get(0))) round++;
                beginTurnString();

                if (currentUser.isUserInJail()) {
                    userIsInJail(currentUser);
                } else {
                    makeTurn(currentUser);
                }

                turnString += "\n********************************************************\n\n";
                if(!waitingForInput) turnString += "It's now " + getNextUser() + "'s turn!";

                System.out.println("------------------------------ Start of Turn ------------------------------");
                System.out.println(turnString);
                System.out.println("------------------------------ Printed During below Turn------------------------------");
            }
        }
    }


    /**
     * For testing, runs the an entire game without waiting for input.
     */
    void autoGame() {
        //setUpStreets();
        //run = true;
        while ((users.size() > 1) && round < 500) {
            turn();
        }
        System.out.println("Winner: " + users.get(0) + " on round " + round);
    }

    //************************************** Street Code ****************************************************

    /**
     * Checks if a street can be built on. The requirement are one user owning the entire street, and any property
     * not being fully developed;
     * @param i The street ID.
     * @return True if there is property on the street which can be developed.
     */
    boolean isStreetBuildable(int i) {
        if(ownerOfStreet(i)!= null) {
            for (Property p : streets.get(i)) {
                if (!p.hasHotel()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Gets a user an array list of the properties on a street which they can build on. A user may only build on a
     * property if no other property on the street is more developed.
     * @param i The ID of the street being queried.
     * @return An array of properties which can be developed.
     */
    ArrayList<Property> buildablePropertiesInStreet(int i) {
        ArrayList<Property> buildableProperties = new ArrayList<Property>();
        int leastNumHouses = streets.get(i).get(0).getNumHouses();//least num houses is the houses on the first property
        for (Property p : streets.get(i)) {
            if (p.getNumHouses() < leastNumHouses) {
                leastNumHouses = p.getNumHouses();
            }
        }
        for (Property p : streets.get(i)) {
            if (!p.hasHotel() && p.getNumHouses() <= leastNumHouses) {
                buildableProperties.add(p);
            }
        }
        return (buildableProperties);
    }

    /**
     * Finds the owner of a street
     * @param streetID The ID of the street being queried.
     * @return the User who owns the street. Returns null if the street is not owned.
     */
    User ownerOfStreet(int streetID) {
        User firstOwner = null;
        if (!(streetID < 0 || streetID > 7)) {
            List<Property> street = streets.get(streetID);
            firstOwner = street.get(0).getOwner();
            for (Property p : street) {
                if (p.getOwner() == null || !p.getOwner().equals(firstOwner)) {
                    return null;
                }
            }
        }
        return firstOwner;
    }

    /**
     * Finds the streets owned by a user.
     * @param u The user being queried
     * @return A list of integers, the integers pertaining to the ID of the streets the user owns.
     */
    List<Integer> streetsOwned(User u) {
        List<Integer> streetsOwned = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            if (ownerOfStreet(i) != null && ownerOfStreet(i).equals(u)) {
                streetsOwned.add(i);
            }
        }
        return streetsOwned;
    }

    /**
     * Finds the properties owned by a user.
     * @param user The user being queried.
     * @return A list of purchasable objects that are owned by the user.
     */
    List<Purchasable> propertiesOwned(User user) {
        List<Purchasable> owned = new ArrayList<>();
        for (Square s : squares) {
            if (s instanceof Purchasable && ((Purchasable) s).getOwner() != null
                    && ((Purchasable) s).getOwner().equals(user)) {
                owned.add((Purchasable) s);
            }
        }
        return owned;
    }

    //************************************** Jail Code ****************************************************

    /**
     * A function to put a user in jail. Moves them to the jail positionon the board and calls a function in the user
     * to out them in jail.
     * @param u The user being put in jail.
     */
    void putInJail(User u) {
        turnString += ("Sending " + u + " to Jail.");
        moveToPosition(u, 11);
        u.putInJail();
    }

    /**
     * This is called if a user in Jail. Several things can happen here. If the user has a get out of jail card, they
     * can leave jail. If not they can choose to try to roll a double to be released or else pay bail to leave.
     * They may only try three times to roll a double before being forced to pay bail. If they cannot pay bail they are
     * bankrupt.
     * @param u The user who is currently in jail.
     */
    void userIsInJail(User u) {


        //System.out.println("Step one.");
        //System.out.print(u + " is in jail. Jail Card: " + u.hasJailCard());
        String doOrDont = "don't";
        if(u.hasJailCard()){doOrDont = "do";}
        turnString += (u + " is in jail. They " +doOrDont + " have a Get out of Jail Card. \n");

        /**
         * 1. Check if they have jail card. (use it) If no:
         * 2. Check if it's their third turn in jail. (Force them to pay) or
         * 3. Give them the option to pay bail or roll out.
         */
        //System.out.println("Count: " + u.getJailCount());
        if (currentUser.hasJailCard()) {
            //System.out.println(u + " uses their jail card.");
            turnString += (u + " uses their jail card.\n");
            currentUser.useJailCard();
            makeTurn(currentUser);
        }

//        if(currentUser instanceof HumanUser){
//            waitingForInput = true;
//            setOptionBox("Do you want to try to roll a double, or bay bail of $" + bail +"?" +
//                    "\nEnter 0 to Roll. \nEnter 1 to pay bail.");
//        }

        else if (u.getTurnsInJail() < 3) {
            //System.out.println(u + " can choose to  roll or bail. They have $" + u.getMoney());
            turnString += (u + " can choose to  roll or bail. They have $" + u.getMoney()) + "\n";
            //The get to decide if they want to roll or bail.
            if (u.rollOrBail(bail)) {//The user opted to bail
                //System.out.println(u + " chooses to pay bail.");
                turnString += (u + " chooses to pay bail.\n");
                takeMoney(u, bail);
                //System.out.println("They now have $" + u.getMoney());
                u.freeFromJail();
                makeTurn(u);
            } else {
                //System.out.println("They choose to roll.");
                turnString += ("They choose to roll.\n");
                dice.rollDice();
                //System.out.println("They roll " + dice.getDice1() + " and " + dice.getDice2());
                turnString += ("They roll " + dice.getDice1() + " and " + dice.getDice2() +"\n");

                if (dice.isDouble()) {
                    //System.out.println("Congrats on the double " + u);
                    turnString += ("Congrats on the double " + u + "! \n");
                    u.freeFromJail();
                    moveUser(u, dice.getTotal());
                    landedOnsSpace(u);
                } else {
                    u.increaseTurnsInJail();
                }
            }
        } else {
            //System.out.println("Their " + u.getTurnsInJail() + "rd turn in jail. Forcing to pay bail.");
            turnString += (u + "has to pay bail.");
            if (u.findFunds(bail)) {
                takeMoney(u, bail);
                makeTurn(u);
            } else {
                turnString += ("They can't make bail.");
            }
        }
    }

    //************************************** Position Code ****************************************************

    /**
     * Moves the user forward a certain number of positions. If they move past past space 39, it loops around and they
     * pass go.
     * @param u The user being moved
     * @param spaces The number of spaces they are being moved
     */
    public void moveUser(User u, int spaces) {
        int currentPosition = getUserPosition(u);
        int newPosition = currentPosition + spaces;
        if (newPosition > 39) {
            newPosition = newPosition - 40;
            passGo(u);
        }
        userPositions.put(u, newPosition);
    }

    /**
     * Moces the user to a specific position on the board.
     * @param u The user being moved.
     * @param position The position they are being moved to.
     */
    public void moveToPosition(User u, int position) {
        if (userPositions.get(u) > position) { //Then it will have to loop around
            passGo(u);
        }
        userPositions.put(u, position);
    }

    /**
     * When a user passes go this function gives them $200.
     * @param u The user who has passed go.
     */
    public void passGo(User u) {
        //System.out.println(u + " Passing Go");
        //int go = 200;
        giveMoney(u, 200);
    }

    /**
     * Returns the position of a user, from the user positions map.
     * @param u The user being queried.
     * @return An int which represents their position.
     */
    public int getUserPosition(User u) {
        return this.userPositions.get(u);
    }

    //************************************** Game management Code ***********************************************

    /**
     * When a user declines to buy a property, it is auctioned to the highest bidder.
     * It is initially offered to ever user, except the user who declined to buy it in
     * the first instance. The price increases incrementally until there is only one bid.
     * That user then buys the property.
     * @param p The property being bid on.
     * @param userWhoPassed The initial user who isn't interested in buying.
     */
    public void auction(Purchasable p, User userWhoPassed) {
        List<User> interestedUsers = new ArrayList<>();
        for(User x : users){
            interestedUsers.add(x);
        }

        List<User> tempInterestedUsers = new ArrayList<>();
        interestedUsers.remove(userWhoPassed);

        int offeredPrice = p.getCost();
        int increment = 10;
        boolean ongoing = true;

        while (ongoing) {
            tempInterestedUsers.clear();
            System.out.println(p.getName() + " going for $" + offeredPrice);
            for (User u : interestedUsers) {
                if(getMoney(u)<offeredPrice){
                    //tempInterestedUsers.add(u);
                }
                //System.out.println(u + "decision: " + (u.buyOption(p, offeredPrice)));
                else if((u.buyOption(p, offeredPrice))) {
                    //System.out.println("removing " + u);
                    tempInterestedUsers.add(u);
                }
            }
            interestedUsers = tempInterestedUsers;
            System.out.println(interestedUsers.size() + " users interested");

            if (interestedUsers.size() == 1) {
                buy(interestedUsers.get(0), p);
                turnString += (p.getName() + " is auctioned to " + interestedUsers.get(0) + " for $" + offeredPrice);
                ongoing = false;
            } else if (interestedUsers.size() == 0) {
                turnString += ("No one buys " + p.getName() + " for $" + offeredPrice + ". Sad!");
                ongoing = false;
            }
            offeredPrice += increment;
            increment+=5;
            interestedUsers = new ArrayList<>();
            //raise the price by 10, start loop again
        }
    }

    /**
     * Finds if the user has can pay the amount i.
     * @param u The user whose finances are being queried.
     * @param i The amount of money they are being asked for.
     * @return boolean, true if they can pay that amount.
     */
    public boolean hasFunds(User u, int i) {
        //System.out.println("HERE");
        return u.findFunds(i);
    }

    /**
     * Gives money to a user
     * @param u User who is being given money
     * @param i Ammount they are being given
     */
    public void giveMoney(User u, int i) {
        //int money = userMoney.get(u);
        userMoney.put(u, userMoney.get(u) + i);

        //.giveMoney(i);
    }

    /**
     * Takes money from a user
     * @param u The user being given money.
     * @param i The amount they are being given.
     */
    public void takeMoney(User u, int i) {
        if (u.findFunds(i)) {
            //System.out.println("jj");
            userMoney.put(u, userMoney.get(u) - i);
        } else {
            System.out.println("no money");

            bankrupt(u);
        }
    }

    /**
     * The function which processes a rent payment between two users.
     * @param from The user paying rent
     * @param to The user receiving rent
     * @param i The amount of rent
     */
    public void rent(User from, User to, int i) {
        takeMoney(from, i);
        giveMoney(to, i);
    }

    public void buy(User u, Purchasable p) {
        System.out.println("Buying: " + u + ", " + p.getName());
        takeMoney(u, p.getCost()); //I suppose it should check if they can afford it
        p.setOwner(u);
    }

    /**
     * Builds a house on a property
     * @param p The property to be built on
     */
    protected void buildHouse(Property p) {
        //Should have some error testing.
        //System.out.println(p.getOwner() + " BUILDS HOUSE ON " + p.getName() + " FOR $" + p.getHouseCost());
        if (!p.isMortgaged()) {
            takeMoney(p.getOwner(), p.getHouseCost());
            p.buildHouse();
        } else {
            //System.out.println("User: " + p.getOwner() + " tries to build on mortgaged property " + p.getName());
        }
    }

    /**
     * Sells a house on a property
     * @param p The property where the house is being sold
     */
    protected void sellHouse(Property p) {
        if (p.getNumHouses() > 0) {
            p.sellHouse();
            giveMoney(p.getOwner(), p.getHouseCost() / 2);
        }
    }

    /**
     * When a user goes bankrupt, this function returns their properties to the bank
     * and removes the user from the game
     * @param u The User being bankrupted.
     */
    public void bankrupt(User u) {
        turnString += u +" IS BANKRUPT";
        for (Purchasable p: u.owned) {
            if(p.isMortgaged()){p.unMortgage();}
            p.resetOwner();
        }
        users.remove(u);
    }

    /**
     * Return the number of railways owned by a user
     * @param u User being queried
     * @return the numbe of railways they own
     */
    public int numRailwaysOwned(User u) {
        int numOwned = 0;
        //List<Square> railways = new ArrayList<>(); //maybe make this in street set up
        for (Square s : squares) {
            if (s instanceof Railway && ((Railway) s).hasOwner() && ((Railway) s).getOwner().equals(u)) {
                numOwned++;
            }
        }
        return numOwned;
    }

    /**
     * Return the number of utilities owned by a user
     * @param u User being queried
     * @return the numbe of utilities they own
     */
    public int numUtilitiesOwned(User u) {
        int numOwned = 0;
        //List<Square> railways = new ArrayList<>(); //maybe make this in street set up
        for (Square s : squares) {
            if (s instanceof Utility && ((Utility) s).hasOwner() && ((Utility) s).getOwner().equals(u)) {
                numOwned++;
            }
        }
        return numOwned;
    }

    /**
     * Mortgages a property. Sets the property to mortgaged, gives the owner the value
     * of the mortgage.
     * @param p The value to be mortgaged
     */
    public void mortgageProperty(Purchasable p) {
        //System.out.println("MP function for " + p.getName());
        p.mortgage();
        //System.out.println("p is mortgaged: " + p.isMortgaged());
        giveMoney(p.getOwner(), p.getMortgageValue());
    }

    /**
     * Unmortgages a property
     * @param p is the property to be unrortgaged.
     */
    public void unMortgageProperty(Purchasable p) {
        System.out.println("Unmortgaging:  " + p.getName());
        p.unMortgage();
        takeMoney(p.getOwner(), p.getMortgageValue());
    }

    /**
     * Returns the amount of money a user has, from the userMoney map, which maps users to
     * their money.
     * @param user The user being queried.
     * @return The amount of money they have.
     */
    public int getMoney(User user) {
        return userMoney.get(user);
    }

    /**
     * Checks if a property can still be mortgaged
     * @param p The property being queried.
     * @return True if they can be mortgaged.
     */
    public boolean mortgagable(Purchasable p) {
        if ((!(p instanceof Property)) || ((Property) p).hasHouses()) {
            return true;
        }
        return false;
    }

    //***************************************** Input Stuff ****************************************

    private boolean waitingForInput = false;
    private boolean jailInput = false;
    private boolean buyingInput = false;
    String optionBox;
    String buyString = "";
    Purchasable forSale;

    String manageString;

    int manageID = 0;
    boolean managing = false;
    User managingUser;

    /**
     * Sets the string for the management box on the GUI
     * @param s The string it's being set to.
     */
    private void setManageString(String s){
        manageString = s;
    }

    /**
     * Gets the managing options.
     * @return The string containing the management options.
     */
    public String getManageString(){
        return manageString;
    }

    /**
     * For the string with the options when a user gets a chance to buy a property.
     * @return The string with the options
     */
    public String getBuyString(){
        return buyString;
    }

    /**
     * A string with the options for the user input.
     * @return The string for the option box on the GUI.
     */
    public String getOptionBox(){
        return optionBox;
    }

    /**
     * Checks if the game is waiting for input of a choice on whether to buy a property.
     * @return boolean, the flag set if the user has an option to buy
     */
    public boolean waitingForBuyingInput() {
        return buyingInput;
    }


    /**
     * If a human user lands on an unowned property, this function is called.
     * It flags that the game is waiting for input.
     * It flags that the game is waiting for a response to buy a property.
     * It sets that the property for sale.
     * It sets the option string the user will see.
     * @param property The property that is for sale.
     */
    public void startBuyingInput(Purchasable property){
        buyString = ("Do you wan't to buy " + property.getName() + " for $" + property.getCost()
                + "? \nEnter 1 for Yes, enter 0 for No.");
        forSale = property;
        waitingForInput = true;
        buyingInput = true;
    }

    /**
     * This takes the user input, i, which corresponds to their decision. If they
     * decide to buy, the game check their funds. If they can afford it, they buy the property.
     * @param i if this is 1, the user has decided they want to buy the house.
     */
    public void buyingResponse(int i){
        if(i==1){
            if(currentUser.getMoney()>=forSale.getCost()) {
                buy(currentUser, forSale);
                turnString += "\n" + currentUser + " buys " + forSale.getName() + " for $" + forSale.getCost() + ".";
            } else {
                turnString += "\n" + currentUser + " can't afford " + forSale.getName() + " for $" + forSale.getCost() + ".";
                System.out.println("Can't afford");
            }
        } else {
            turnString += "\n" + currentUser + " chooses not to buy " + forSale.getName() + ".";
            System.out.println("Chose not to buy");}

        buyingInput = false;
        waitingForInput = false;
        //gaveInput();
    }

    /**
     * Process input after the first option page of management. User enters between 1-4 for build, sell, mortage
     * and unmortage.
     * @param choiceInt The users input.
     */
    public void userManage(int choiceInt){
        if (choiceInt == 1) {
            setManageString(managingUser.buildString());
            optionBox = (getManageString());
            manageID = 1;

        } else if (choiceInt == 2) {
            setManageString(managingUser.sellString());

            manageID = 2;

        } else if (choiceInt == 3) {
            System.out.println("THREE " + managingUser.mortgageString());
            setManageString(managingUser.mortgageString());
            manageID = 3;

        } else if (choiceInt == 4) {
            setManageString(managingUser.unmortgageString());
            manageID = 4;
        } else if (choiceInt == 5) {
            setManageString("Exiting.");
            managing = false;
            managingUser = null; //clear this later
            waitingForInput = false;
        } else {
            setManageString("Invalid choice!");
            managing = false;
            managingUser = null; //clear this later
            waitingForInput = false;
        }

        //It't the first screen, 1 Build, 2 Sell, 3 mortgage, 4 unmortgage

    }

    /**
     * Called when the user has decided to exit.
     */
    public void userExit(){
        managing = false;
        managingUser = null; //clear this later
        waitingForInput = false;
    }

    /**
     * The user has been given a list of properties they can build on.
     * This function processes their choice, based on the int they send back.
     * @param choiceInt The int that corresponds to a property. If out of range it exits.
     */
    public void userBuild(int choiceInt) {
        //Should check their funds.
        String outputAfterChoice = "";
        if (manageID == 1) {
            if(choiceInt == managingUser.listOfPropertiesToBuildOn().size()){userExit();}
            else if (choiceInt < managingUser.listOfPropertiesToBuildOn().size()) {
                outputAfterChoice += "Building on " + managingUser.listOfPropertiesToBuildOn().get(choiceInt).getName();
                managingUser.buildOnProperty(managingUser.listOfPropertiesToBuildOn().get(choiceInt));
            } else {
                outputAfterChoice += "Output " + choiceInt + " is out of range.\n";
            }
            System.out.println("Choice out of range");
            outputAfterChoice += "Do you want to manage more? 1 for Yes, 0 for No \n";
            setManageString(outputAfterChoice);
            manageID = 5;
        }
    }

    /**
     * The user has been given a list of properties they can sell on.
     * This function processes their choice, based on the int they send back.
     * @param choiceInt The int that corresponds to a property on the list. If out of range it exits.
     */
    public void userSell(int choiceInt){
        String outputAfterChoice = "";
        if(choiceInt == managingUser.listOfDevelopedToSell().size()){userExit();}
        else if (choiceInt < managingUser.listOfDevelopedToSell().size()) {
            outputAfterChoice += "Sell house on " + managingUser.listOfDevelopedToSell().get(choiceInt).getName();
            managingUser.sellHouse(managingUser.listOfDevelopedToSell().get(choiceInt));
        } else {outputAfterChoice += "Output " + choiceInt + " is out of range.\n" ;}
        System.out.println("Choice out of range");
        outputAfterChoice += "Do you want to manage more? 1 for Yes, 0 for No \n";
        setManageString(outputAfterChoice);
        manageID = 5;
    }

    /**
     * The user has been given a list of properties they can mortgage.
     * This function processes their choice, based on the int they send back.
     * @param choiceInt The int that corresponds to a property on the list. If out of range it exits.
     */
    public void userMortgage(int choiceInt){
        System.out.println("User M " + managingUser);
        System.out.println("C: " + choiceInt + ", " + managingUser.listOfUndevelopedToMortgage().size() + "B: " +
                (choiceInt == managingUser.listOfUndevelopedToMortgage().size()));
        String outputAfterChoice = "";

        if(choiceInt == managingUser.listOfUndevelopedToMortgage().size()){
            System.out.println("chose to exit");
            userExit();}
        else if (choiceInt < managingUser.listOfUndevelopedToMortgage().size()) {
            outputAfterChoice += "Mortgaging: " + managingUser.listOfUndevelopedToMortgage().get(choiceInt).getName() + "\n\n";
            managingUser.mortgagePurchasable(managingUser.listOfUndevelopedToMortgage().get(choiceInt));
        } else {outputAfterChoice += "Choice " + choiceInt + " is out of range.\n" ;}
        System.out.println("Choice out of range");
        outputAfterChoice += "Do you want to manage more? 1 for Yes, 0 for No \n";
        setManageString(outputAfterChoice);
        manageID = 5;
    }

    /**
     * The user has been given a list of properties they can unmortgage.
     * This function processes their choice, based on the int they send back.
     * @param choiceInt The int that corresponds to a property on the list. If out of range it exits.
     */
    public void userUnmortgage(int choiceInt){
        String outputAfterChoice = "";
        if(choiceInt == managingUser.listOfMortgaged().size()){userExit();}
        else if (choiceInt < managingUser.listOfMortgaged().size()) {
            outputAfterChoice += "Unmortgaging " + managingUser.listOfMortgaged().get(choiceInt);
            managingUser.unmortgaged(managingUser.listOfMortgaged().get(choiceInt));
        } else {outputAfterChoice += "Output " + choiceInt + " is out of range.\n" ;}
        System.out.println("Choice out of range");
        outputAfterChoice += "Do you want to manage more? 1 for Yes, 0 for No \n";
        setManageString(outputAfterChoice);
        manageID = 5;
    }

    /**
     * A user has sent input. This function first checks what input is being awaited, managing,
     * buying, or jail. It then processes what their input
     * @param input The string the user has sent. It's converted to an int.
     */
    public void sendInput(String input) {
        int choiceInt = Integer.parseInt(input);
        if (managing) {
            if (manageID == 0) {
                userManage(choiceInt);
            } else if (manageID == 5) {
                //They've been asked if they want to go again // Do you want to go again?
                manageID = 0;
                if (choiceInt == 1) {
                    manage1();
                } else {
                    managing = false;
                    managingUser = null; //clear this later
                    waitingForInput = false;
                }
            } else {
                if (manageID == 1) {
                    userBuild(choiceInt);
                    System.out.println("choice 1");
                } else if (manageID == 2) {
                    userSell(choiceInt);
                    System.out.println("choice 2");
                } else if (manageID == 3) {
                    userMortgage(choiceInt);
                    System.out.println("choice 3");
                } else if (manageID == 4) {
                    userUnmortgage(choiceInt);
                    System.out.println("choice 4");
                } else {
                    //quit6
                }
                //made their choice
                //ask them if they want to manage more.
            }
        } else if (buyingInput) {
            buyingResponse(choiceInt);
        } else if (jailInput){
            if (choiceInt == 0){
                //roll
                dice.rollDice();
                if(dice.isDouble()){
                    turnString += currentUser + " rolled a double";
                    currentUser.freeFromJail();
                    moveUser(currentUser, dice.getTotal());
                    landedOnsSpace(currentUser);
                }
            } else if(choiceInt == 1){
                takeMoney(currentUser, bail);
                //System.out.println("They now have $" + u.getMoney());
                currentUser.freeFromJail();
                makeTurn(currentUser);
            } else {
                optionBox = ("0 to roll, 1 to pay bail.");
            }
        }
    }

    /**
     * This is called when the user presses the manage button.
     * It sets up the option box to display the option and it sets the appropriate input flags.
     */
    public void manage1(){
        waitingForInput = true;
        managing = true;
        manageID = 0;


        managingUser = getNextUser();
        System.out.println("Managing: " + managingUser);

        String s = "";
        s += "For user " + managingUser + "\n\n";
        s += ("You have " + managingUser.listOfPropertiesToBuildOn().size()
                + " properties to build on.\n");
        s += ("You have " + managingUser.listOfDevelopedToSell().size()
                + " properties to sell from.\n");
        s += ("You have " + managingUser.listOfUndevelopedToMortgage().size()
                + " properties to mortgage.\n");
        s += ("You have " + managingUser.listOfMortgaged().size()
                + " properties to unmortgage.\n\n");

        s+=("To Build: Enter 1\nTo Sell: Enter 2\n To Mortgage: Enter 3\n " +
                "To Unmortgage: Enter 4\n To Exit: Enter 5"+"\n");
        manageString = s;
        setManageString(s);
    }


    //************************************** Print Code ****************************************************

    /**
     * Gets a list of positions and the users at each position.
     * @return A list of lists of users. Each position on the array corresponds to the position on the board
     */
    public ArrayList<List<User>> getPositions(){
        ArrayList<List<User>> positions = new ArrayList<List<User>>();
        int position = 0;
        for(Square s : squares){
            positions.add(usersAtPosition(position));
        }
        return positions;
    }

    /**
     * Gets a list of users at any given position.
     * @param position an integer of the location of the position on the board.
     * @return A list of users at any given position.
     */
    public List<User> usersAtPosition(int position){
        List<User> users = new ArrayList<>();
        for(User u : userPositions.keySet()){
            if(userPositions.get(u) == position){
                users.add(u);
            }
        }
        return users;
    }

    /**
     *
     * @return An array list with the ammount of users at each position between 0-39
     */
    public ArrayList<Integer> positionList(){
        ArrayList<Integer> occupiedPositions = new ArrayList<>();
        for(int i = 0; i <40; i++){
            if(usersAtPosition(i).size()>0){
                occupiedPositions.add(i);
            }
        }
        return occupiedPositions;
    }

    /**
     *
     * @return a string with information about the current user.
     */
    public String currentUserInfo(){
        return currentUser.getInfo();
    }

    /**
     *
     * @return a string position number, and the users at that position.
     */
    public String positionsString() {
        ArrayList<List<User>> positions = getPositions();

        String s = "";
        int square = 0;
        for (int i = 0; i < 40; i++) {
            if (usersAtPosition(i).size() > 0) {
                s += ("Pos: " + i + " ");
                for (User u : usersAtPosition(i)) {
                    s += (u.getUserName() + ", ");
                }
            }
        }
        return s;
    }

    /**
     * Gets a string with users at the square.
     * @param position an integer that represent the position of the square on the board.
     * @return a string of user names, for the users at the position.
     */
    public String usersAtSquare(int position){
        String s = "";
        for(User u : usersAtPosition(position)){
            s += u.getUserID() + ", ";
        }
        return s;
    }

    /**
     * The GUI has an info box with information on the next player. This gets that information.
     * @return the generated information about the next player.
     */
    public String infoBox(){
        return getNextUser().getInfo();
    }

    /**
     * Prints a text based version of the board, and positions.
     */
    public void printBoard() {

        int i = 0;
        System.out.println("\n___________________________________________Board___________________________________________");
        System.out.println("|  ");
        System.out.println("|  ");
        for (Square s : squares) {
            //System.out.println("At square: " + i);

            String usersHere = "";
            List<User> userList = usersAtPosition(i);

//            if(!usersAtPosition(0).isEmpty()){
//                int j = 0;
//                for(User u : usersAtPosition(0)){
//                    deleteThis[i] = u.getUserName();
//                }
//            }

            //System.out.println("Num Users: " + userList.size());

            if (!(userList.isEmpty())) {
                for (User u : userList) {
                    usersHere += " (User: " + u + ")";
                }
            }


            //System.out.println("Users: " + usersHere);
            String finalS = String.format("|  %-66s :: %s   ", s, usersHere);
            //System.out.println(s + usersHere);
            System.out.println(finalS);
            i++;
        }
        System.out.println("___________________________________________________________________________________________");

    }

    /**
     * Prints the users, their money and their positions.
     */
    public void printInfo() {
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            System.out.println("User: " + u.getUserName() + ", Money: "
                    + u.getMoney() + ", Position: " + squares.get(getUserPosition(u)).getName());
        }
        for (User u : users) {
            if (streetsOwned(u).size() > 0) {
                System.out.println(u + " owns a street: " + streetsOwned(u).get(0));
            }
        }

    }

    public void printRound(){
        System.out.println(" *************************************** Round: " + this.round + "**************************************");
        printInfo();
        if(autoPrintBoard){printBoard();}
        round++;
    }

    public void printOwnership(){
        for (Square s : squares){
            if(s instanceof Property){
                Property p = (Property) s;
                String owner = "null";
                if(p.getOwner()!=null){
                    owner = p.getOwner().getUserName();
                }
                System.out.println(p.getID() + " Name: " + " Owner: " + owner);
            }
        }
    }

    public void generatePositions(){

    }

    public String getPos(int x, int y){
        System.out.println("Len at go: " + usersAtPosition(0).size());

        String s = "";
        String[] userIDs = new String[8];
        int i = 0;
        List<User> userList = usersAtPosition(x);
        System.out.println("Len at go2: " + userList.size());
        System.out.println("Y: " + y);
        System.out.println("S: " + userList.size());
        System.out.println("B: " + (y<userList.size()-1));
        if(y<=userList.size()-1){
            System.out.println("In the IF: " + userList.get(y));

            s = userList.get(y).getUserID().toString();
        }
        return s;
    }



    //************************************** Other *********************************************************

    public void delay(int microseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(microseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}