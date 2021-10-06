package exceptions;

public class BadResponse extends Exception{
    //Exception to deal with any faulty inputs that the user attempts to enter into the console
    public BadResponse(String message) {
        super("Invalid response.\n" + message);
    }
}
