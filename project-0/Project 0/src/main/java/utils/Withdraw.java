package utils;

import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Connection;

import exceptions.WithdrawTooMuch;
import DAOs.DAOAccount;
import views.LoggedIn;

public class Withdraw {
    static double amountWithdrawn;
    static int ac_num;

    public static void WithdrawFunds(String username){
        ListAccounts.callAccounts(username);
        Scanner sc = new Scanner(System.in);
        System.out.println("===WITHDRAW===\nSelect the account that you would like to withdraw from:");
        ac_num = Integer.parseInt(sc.nextLine());
        try{
            Connection conn = ConnectionManager.getConnection();
            DAOAccount account = new DAOAccount(conn);
            //Verify that the username is associated with the account number given
            if(account.verifyUsername(username, ac_num)) {
                //Prompt the user for how much they would like to withdraw from specified account
                System.out.println("How much would you like to Withdraw?");
                amountWithdrawn = Double.parseDouble(sc.nextLine());
                //Ensure that they cannot withdraw negative money
                if (amountWithdrawn <= 0) {
                    System.out.println("Invalid amount, please enter a value greater than 0");
                    WithdrawFunds(username);
                }
            }
            else{
                WithdrawFunds(username);
            }

            account.withdraw(ac_num, amountWithdrawn);
            System.out.printf("$%,.2f has been withdrawn from your account!\n", amountWithdrawn);
            LoggedIn.LMenu(username);
        }
        catch (SQLException | WithdrawTooMuch message){
            System.out.println(message.getMessage());
        }
    }
}
