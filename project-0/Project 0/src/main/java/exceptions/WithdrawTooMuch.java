package exceptions;

public class WithdrawTooMuch extends Exception{
    //Exception if user tries to withdraw too much money
    public WithdrawTooMuch(){
        super("You do not have sufficient funds to withdraw requested amount.");
    }

    public WithdrawTooMuch(String message){super(message);
    }
}
