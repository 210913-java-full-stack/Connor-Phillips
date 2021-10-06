package exceptions;

public class IncorrectPassword extends Exception{
    //Exception if user enters an incorrect password when attempting to log in
    public IncorrectPassword(){
        super("Incorrect username or password. Try again, or register a new account.");
    }

    public IncorrectPassword(String message){
        super(message);
    }
}
