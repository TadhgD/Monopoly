package sample;
/**
 * Created by Brian on 08/02/2017.
 */
abstract class Square {
    String name;

    public Square(String name) {
        setName(name);
    }

    public void Square(String name){
        setName(name);
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return (this.getName());
    }




    //public void Action(User u){}
}