package views;

import DAOs.DAOUser;
import exceptions.BadResponse;
import exceptions.TakenUsername;
import models.Users;
import utils.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class RegAccount {
    static String username;
    static String password;
    static String fName;
    static String lName;
    static Scanner sc = new Scanner(System.in);

    public static void RegMenu() throws BadResponse {
        //Retrieves new account info from user
        System.out.println("===REGISTER NEW ACCOUNT===\nUsername:");
        username = sc.nextLine();
        System.out.println("Password:");
        password = sc.nextLine();
        System.out.println("First name:");
        fName = sc.nextLine();
        //Checks to make sure First and Last name have no symbols or numbers
        if(!checkString(fName)){
            throw new BadResponse(fName);
        }
        System.out.println("Last name:");
        lName = sc.nextLine();
        if(!checkString(lName)){
            throw new BadResponse(lName);
        }
        //Construct the currentUser
        Users currentUser = new Users(username, password, fName, lName);
        //Connect to database and create account
        try{
            Connection conn = ConnectionManager.getConnection();
            DAOUser User = new DAOUser(conn);
            User.Register(currentUser);
            System.out.println("Your account has been created. Now logging you in...");
            LoggedIn.LMenu(username);
        }
        //throw the TakenUsername exception if username is found in the database
        catch(SQLException | TakenUsername message) {
            System.out.println(message.getMessage());
        }


    }

    public static boolean checkString(String string){
        //makes sure the string is not empty or null
        if(string.equals("")|| string == null){
            return false;
        }
        for(int i = 0; i < string.length(); i++){
            char c = string.charAt(i);
            //makes sure there are no strange characters in the string
            if((!(c >= 'A' && c <= 'Z')) && (!(c >= 'a' && c <= 'z'))){
                return false;
            }
        }
        return true;
    }
}
