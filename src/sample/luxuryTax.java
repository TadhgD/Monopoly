package sample;

/**
 * Created by Brian on 18/02/2017.
 */
public class luxuryTax extends Square implements Actionable {
    int tax = 100;
    String message;
    public luxuryTax(){
        super("Luxury Tax");
        setMessage("Pay Luxury tax 100");
    }

    @Override
    public void action(Game g, User u){g.takeMoney(u, tax);}

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return null;
    }


}
