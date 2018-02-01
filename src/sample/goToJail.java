package sample;

/**
 * Created by Brian on 18/02/2017.
 */
public class goToJail extends Square implements Actionable {
    String message;

    public goToJail(){
        super("Go to Jail");
        setMessage("Go to Jail.");
    }

    public void action(Game g, User u){
        g.putInJail(u);
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
