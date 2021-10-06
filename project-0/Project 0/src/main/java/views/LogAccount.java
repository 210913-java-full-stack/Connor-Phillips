package views;

import java.util.Scanner;
import java.sql.SQLException;
import java.sql.Connection;
import DAOs.DAOUser;
import exceptions.BadResponse;
import exceptions.IncorrectUsername;
import exceptions.IncorrectPassword;
import utils.ConnectionManager;


public class LogAccount {
    static String username;
    static String password;
    static Scanner sc = new Scanner(System.in);

    public static String LogMenu() throws BadResponse {
        //Retrieve the login credentials from the user input
        System.out.println("===Log into your account===\nUsername:");
        username = sc.nextLine();
        System.out.println("Password:");
        password = sc.nextLine();
        //Attempt to log in to their account
        try{
            Connection conn = ConnectionManager.getConnection();
            DAOUser User = new DAOUser(conn);
            if(User.Login(username, password) != null){
                System.out.println("Login successful! Logging in now...");
                LoggedIn.LMenu(username);
            }
        }
        //Throw exceptions if log in credentials are incorrect
        catch (SQLException | IncorrectUsername | IncorrectPassword message) {
            System.out.println(message.getMessage());
            MainMenu.runMMenu();
        }
        return null;
    }
}
