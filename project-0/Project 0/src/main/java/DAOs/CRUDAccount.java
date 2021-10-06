package DAOs;

import exceptions.WithdrawTooMuch;
import list.ArrayList;

import java.sql.SQLException;


public interface CRUDAccount<E> {
    //Retrieves users account id
    public int getAc_id() throws SQLException;

    //Run when new account is registered to create their account
    public void createAccount(String username, String ac_type) throws SQLException;

    //Searches for an account connected to a username
    public ArrayList<E> accountUser(String username) throws SQLException;

    //Verifies the account belongs to the user
    public boolean verifyUsername(String username, int ac_id) throws SQLException;

    //Deposits funds
    public void deposit(int ac_id, double money) throws SQLException;

    //Withdraw funds
    public void withdraw(int ac_id, double money) throws SQLException, WithdrawTooMuch;
}
