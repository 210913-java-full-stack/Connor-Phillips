package views;

import utils.*;

import java.util.Scanner;

public class LoggedIn {

    public static void LMenu(String currentUser){
        Scanner sc = new Scanner(System.in);
        Boolean running = true;
        while(running){
            System.out.println("===WELCOME===\n1) Deposit Funds\n2) Withdraw Funds\n3) Create a new bank account\n4) View all accounts and balances\n===Enter Q or q to quit===");
            String input = sc.nextLine();

            switch(input){
                case "1":
                    Deposit.depositFunds(currentUser);
                    LMenu(currentUser);
                case "2":
                    Withdraw.WithdrawFunds(currentUser);
                    LMenu(currentUser);
                case "3":
                    CreateAccount.CreateNewAccount(currentUser);
                    LMenu(currentUser);
                case "4":
                    ListAccounts.callAccounts(currentUser);
                    LMenu(currentUser);
                case "Q":
                case "q":
                    System.out.println("Have a nice day!");
                    System.exit(0);
                default:
                    System.out.println("Invalid input. Try again.");
            }
        }

    }
}
