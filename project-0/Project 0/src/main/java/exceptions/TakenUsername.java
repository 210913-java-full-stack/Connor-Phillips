package exceptions;

public class TakenUsername extends Exception {
    //Exception if user attempts to register an account with an already used username
    public TakenUsername() {
        super("Username is already taken. Please try again.");
    }
    public TakenUsername(String message) {
        super(message);
    }
}
