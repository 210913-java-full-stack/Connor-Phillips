package utils;

import java.sql.SQLException;
import java.sql.Connection;
import java.util.Scanner;
import DAOs.DAOAccount;

public class Deposit {

    static double deposit;
    static int ac_num;

    public static void depositFunds(String username){
        //Prints the accounts associated with the username for the user to see
        ListAccounts.callAccounts(username);
        Scanner sc = new Scanner(System.in);
        //Prompt the user for the account they'd like to deposit money into
        System.out.println("===DEPOSIT===\nSelect the account that you would like to deposit into:");
        ac_num = Integer.parseInt(sc.nextLine());
        try{
            Connection conn = ConnectionManager.getConnection();
            DAOAccount account = new DAOAccount(conn);
            //Verify that the username is associated with the account number given
            if(account.verifyUsername(username, ac_num)){
                //Prompt the user for how much they would like to deposit into specified account
                System.out.println("How much would you like to Deposit?");
                deposit = Double.parseDouble(sc.nextLine());
                //Ensure that they cannot deposit negative money
                if(deposit <= 0){
                    System.out.println("Invalid amount, please enter a value greater than 0");
                    depositFunds(username);
                }

            }
            else{
                depositFunds(username);
            }
            account.deposit(ac_num, deposit);
            System.out.printf("$%,.2f successfully deposited into your account!\n", deposit);
        }
        catch (SQLException message){
            System.out.println(message.getMessage());
        }

    }
}
