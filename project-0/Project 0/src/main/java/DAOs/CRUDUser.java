package DAOs;

import models.Users;
import java.sql.SQLException;

import exceptions.IncorrectPassword;
import exceptions.IncorrectUsername;
import exceptions.TakenUsername;

public interface CRUDUser<E> {
    //Retrieves users account id
    public int getAc_id() throws SQLException;

    //Creates a new account for the user
    public void Register(Users newUser) throws SQLException, TakenUsername;

    //Checks the inputted username and password to login the user into their account
    public E Login(String username, String password) throws SQLException, IncorrectPassword, IncorrectUsername;
}
