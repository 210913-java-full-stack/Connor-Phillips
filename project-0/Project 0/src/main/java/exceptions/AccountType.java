package exceptions;

public class AccountType extends Exception{
    //Exception if the user attempts to create an invalid type of account
    public AccountType(){
        super("Please select either a 'Checking' or 'Savings' account. These are the only types provided.");
    }

    public AccountType(String message){
        super(message);
    }
}
