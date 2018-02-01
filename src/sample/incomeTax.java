package sample;

/**
 * Created by Brian on 18/02/2017.
 */
public class incomeTax extends Square implements Actionable {
    String message;

    public incomeTax(){
        super("Income Tax");
        setMessage(this.getName());
    }

    @Override
    public void action(Game g, User u){
        int tax = 200;
        int worth = u.getWorth();
        if(worth/10 < tax){ tax = worth/10; }
        setMessage("10 % of your worth is " + u.getWorth()/10 + ". Pay tax $" + tax);
        g.takeMoney(u, tax);
    }

    @Override
    public void setMessage(String message) {
        message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
