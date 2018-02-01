package sample;

/**
 * Created by Brian on 18/02/2017.
 */
public interface Actionable {
    public void action(Game g, User u);

    abstract void setMessage(String message);
    abstract String getMessage();
}
