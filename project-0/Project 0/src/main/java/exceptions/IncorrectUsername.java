package exceptions;

public class IncorrectUsername extends Exception{
    //Exception to deal with any incorrect username inputs
    public IncorrectUsername(){
        super("Incorrect username or password. Please try again or register for a new account");
    }

    public IncorrectUsername(String message){
        super(message);
    }
}
