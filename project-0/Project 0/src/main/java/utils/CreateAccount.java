package utils;

import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Connection;
import DAOs.DAOAccount;
import views.LoggedIn;

public class CreateAccount {
    static String ac_type;
    static Scanner sc = new Scanner(System.in);

    public static void CreateNewAccount(String username){
        //prompt the user which type of account they want to create
        System.out.println("Enter what type of account you'd like to create: Checking or Savings?");
        ac_type = sc.nextLine();
        try{
            //make the new checking or savings account
            if(ac_type.equals("Checking") || ac_type.equals("Savings")){
                Connection conn = ConnectionManager.getConnection();
                DAOAccount newAccount = new DAOAccount(conn);
                newAccount.createAccount(username, ac_type);
                System.out.println("Account has been created!");
            }
            else{
                System.out.println("Invalid account type, needs to be Checking or Savings");
            }
        }
        catch (SQLException message) {
            System.out.println(message.getMessage());
        }
        LoggedIn.LMenu(username);
    }
}
