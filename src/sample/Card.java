package sample;//

/**
 * Created by Brian on 18/02/2017.
 */
public class Card extends Square implements Actionable{

    // Make an array for each, chance 0-15, com chest 0-14
    // Get an array shuffle method online, shuffle them on initialisation
    // The switch works off switch chanceArray[chanceNumber] etc.
    String message = "";




    static int chanceNumber = 0;
    static int communityChestNumber = 0;
    private int type; //0 for Community chest, 1 for Chance.

    Card(String name, int t){ //This is surely unnecessary? It should do the default constructor
        super(name);
        setType(t);
        setMessage("Unset Card");
    }

    private void setType(int type) {
        this.type = type;
    }



    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    public void printMessage(){
        System.out.println(getMessage());
    }

    //@Override
    public void messageSetter(Game g, User u) {
        int pos = g.userPositions.get(u);
        if (type == 0) { //Community chest

            communityChestNumber ++;
            if(communityChestNumber>14) communityChestNumber = 0;
            //System.out.println("Player" + u.getUserName() + "draws a community chest card!\nIt reads...");

            //System.out.println("into com c switch, num : " + communityChestNumber);
            switch (communityChestNumber){
                case 0:
                    setMessage("It's " + u.getUserName() + "'s birthday! Each player pays them a tenner!");
                    break;
                case 1:
                    setMessage("You get caught drinking on the street. Go to Jail. Do not collect grant.");
                    break;
                case 2:
                    setMessage("Get out of Jail free.");
                    break;
                case 3:
                    setMessage("Advance to go. Collect your grant on the way. ");
                    break;
                case 4:
                    setMessage("Landlord charged excess rent by mistake. Collect 200$.");
                    break;
                case 5:
                    setMessage("Doctor's fees. Pay 50$.");
                    break;

                case 6:
                    setMessage("You find 50$ in your old trousers. Nice one!");
                    break;

                case 7:
                    setMessage("You host a bake sale fundraiser ( for yourself )!" + u.getUserName() + " collects " +
                            "50$ from each player.");
                    break;

                case 8:
                    setMessage("Parents are feeling generous! Collect 100$.");
                    break;

                case 9:
                    setMessage("Income tax refund! Collect 20$.");
                    break;

                case 10:
                    setMessage("Rich Auntie sends you money! Collect 100$.");
                    break;

                case 11:
                    setMessage("Pay capitation fees of 150$.");
                    break;

                case 12:
                    setMessage("Your friend pays you 25$ for doing their assignment for them.");
                    break;
                case 13:
                    setMessage("You are assessed for street repairs. Pay 40$ per house and 115$ per hotel.");
                    break;

                case 14:
                    setMessage("You take part in a dodgy medical trial. Collect 100$! You can hear colours now.");
                    break;

            }
        }
        else {
            chanceNumber++;
            if(chanceNumber>15) chanceNumber= 0;
            switch (chanceNumber) {


                case 0:
                    setMessage("Advance to go. Collect grant on the way.");
                    break;
                case 1:
                    setMessage("Advance to " + g.squares.get(29).getName() + ". If you pass go, collect grant.");
                    break;
                case 2:
                    setMessage("Advance to " + g.squares.get(0) + ". If you pass go, collect grant.");
                    break;
                case 3:
                    setMessage("Advance to nearest utility. If it is not owned, you may buy it. If it is owned, " +
                            "throw dice and pay the owner 10 times the amount.");
                    break;
                case 4:
                    Railway r;
                    int railwayInt;

                    setMessage("Advance to nearest takeaway and pay the owner double the rent " +
                            "they are otherwise entitled. If the property is not owned, you may" +
                            "purchase it.");
                    break;
                case 5:
                    setMessage("Your secret Santa is lazy and rich. Receive 50$");
                    break;
                case 6:
                    setMessage("Get out of Jail free.");
                    break;
                case 7:
                    setMessage("You're locked. Stumble back three spaces.");
                    break;
                case 8:
                    setMessage("You walked across the quad. Go to Jail. Do not pass go.");
                    break;
                case 9:
                    setMessage("Make general repairs on all your property. Pay 25$ for each" +
                            " house, and 100$ for each hotel.");
                    break;
                case 10:
                    setMessage("You're accosted by S.H.A.R.E workers and surrender your coppers. Pay 15$");
                    break;
                case 11:
                    setMessage("Take a trip to " + g.squares.get(25).getName() + ". If you pass go, collect 200$");
                    break;
                case 12:
                    setMessage("Take a walk on " + g.squares.get(39).getName ()
                            + ". Advance to that spot on the board.");
                    break;
                case 13:
                    setMessage("You kept all your housemates awake all night with your antics. Pay each player 50$.");
                    break;
                case 14:
                    setMessage("All those scratch cards paid off! Collect 150$.");
                    break;
                case 15:
                    setMessage("You won a like and share competition! Collect 100$.");
                    break;
            }
        }
        //System.out.println(message);
    }

    @Override
    public void action(Game g, User u) {
        g.card = true;
        //System.out.println("SQAURE: " + u + "landed on pos " + g.userPositions.get(u) + " type : " + this.type);
        int pos = g.userPositions.get(u);
        //Chance.
        //System.out.println("Player" + u.getUserName() + "draws a chance card!\nIt reads...");
        if (type == 0) { //Community chest
            if(communityChestNumber>14) communityChestNumber = 0;
            //System.out.println("Player" + u.getUserName() + "draws a community chest card!\nIt reads...");

            //System.out.println("into com c switch, num : " + communityChestNumber);
            switch (communityChestNumber){


                case 0:
                    for(User user : g.users){
                        if(!(u.equals(user))){
                            g.takeMoney(user, 10);
                            g.giveMoney(u, 10);
                        }
                    }
                    break;

                case 1:
                    g.putInJail(u);
                    break;

                case 2:
                    u.giveJailCard();
                    break;

                case 3:
                    g.moveToPosition(u, 0);
                    break;

                case 4:
                    g.giveMoney(u, 200);
                    break;

                case 5:
                    g.takeMoney(u, 50);
                    break;

                case 6:
                    g.giveMoney(u, 50);
                    break;

                case 7:
                    for(User user : g.users){
                        if(!(u.equals(user))){
                            g.takeMoney(user, 50);
                            g.giveMoney(u, 50);
                        }
                    }
                    break;

                case 8:
                    g.giveMoney(u, 100);
                    break;

                case 9:
                    g.giveMoney(u, 20);
                    break;

                case 10:
                    g.giveMoney(u, 100);
                    break;

                case 11:
                    g.takeMoney(u, 150);
                    break;

                case 12:
                    g.giveMoney(u, 25);
                    break;

                case 13:
                    int total = (u.numHouses() * 40 + u.numHotels() * 115);
                    g.takeMoney(u, total);
                    break;

                case 14:
                    g.giveMoney(u, 100);
                    break;

            }
            communityChestNumber ++;
        }
        else {

            if(chanceNumber>15) chanceNumber= 0;
            switch (chanceNumber) {

                case 0:
                    /* some function to move to go */////////////////////////////////// BRIAN DO THIS plz
                    break;

                case 1:
                    g.moveToPosition(u, 29);
                    g.landedOnsSpace(u);
                    break;

                case 2:
                    g.moveToPosition(u, 29);
                    g.landedOnsSpace(u);
                    break;

                case 3:
                    //Utilities are at 12 or 28.
                    //If you're between 12 and 28, the nearest will be 28. Else 12.
                    int nearestUtility = 12;
                    if (pos >= 12 || pos < 28) {
                        nearestUtility = 28;
                    }

                    g.moveToPosition(u, nearestUtility);
                    Utility utility = (Utility) g.squares.get(nearestUtility);
                    if (utility.hasOwner()) {
                        g.dice.rollDice();
                        int rent = g.dice.getTotal() * 10;
                        g.rent(u, utility.getOwner(), rent);
                    } else {
                        g.landedOnsSpace(u);
                    }
                    // Is owned? If not, buy, else, roll dice and pay * 10 dice roll
                    break;

                case 4:
                    Railway r;
                    int railwayInt;

                    /*int pos = g.userPositions.get*/
                    /*Railways are at 5, 15, 25 or 35.*/

                    if (pos <= 5 || pos > 35) railwayInt = 5;
                    else if (pos <= 15) {
                        railwayInt = 15;
                    } else if (pos <= 25) {
                        railwayInt = 25;
                    } else { //If position is 26-35
                        railwayInt = 35;
                    }

                    g.moveToPosition(u, railwayInt);
                    r = (Railway) g.squares.get(railwayInt);
                    if (r.hasOwner()) {
                        r.getRent(g);
                        g.rent(u, r.getOwner(), r.getRent(g)*2);
                    } else {
                        g.landedOnsSpace(u);
                    }
                    // Is owned? If not, buy, else, pay rent * 2
                    break;

                case 5:
                    g.giveMoney(u, 50);
                    break;

                case 6:
                    u.giveJailCard();
                    break;

                case 7:
                    g.moveToPosition(u, g.getUserPosition(u)-3);
                    if(g.userPositions.get(u)==0){
                        g.passGo(u);
                    }
                    break;

                case 8:
                    g.putInJail(u);
                    //Some go to jail function.
                    break;

                case 9:
                    int total = (u.numHouses() * 25 + u.numHotels() * 100);
                    g.takeMoney(u, total);
                    break;

                case 10:
                    g.takeMoney(u, 15);
                    break;

                case 11:
                    g.moveToPosition(u, 25);
                    //Move to pos x, if you pass go collect 200$.
                    break;

                case 12:
                    g.moveToPosition(u, 39);
                    g.landedOnsSpace(u);
                    break;

                case 13:
                    for (User user : g.users) {
                        if (!(u.equals(user))){
                            g.takeMoney(u, 50);
                            g.giveMoney(user, 50);
                        }
                    }
                    break;

                case 14:
                    g.giveMoney(u, 150);
                    break;

                case 15:
                    g.giveMoney(u, 100);
                    break;

            }
            chanceNumber++;
        }
        //System.out.println(message);
    }

}

/**
 // * Created by Brian on 18/02/2017.
 // */
//public class Card extends Square implements Actionable{
//    //Just do all of this in the game
//    /*public card(String name){//"Chance" or "Community Chest"
//        setName(name); //This should be in super or whatever
//        this.numberOfCards = numberOfCards;
//    }*/
//    int cardNumber = 0;
//
//    public Card(String name){ //This is surely unnecessary? It should do the default constructor
//        super(name);
//    };
//
//    @Override
//    public void action(Game g, User u) {
//    }
//
//}