package views;

import java.util.Scanner;
import exceptions.BadResponse;

public class MainMenu {


    public static void runMMenu() throws BadResponse {
        //Scanner object, this is the console input from the user
        Scanner sc = new Scanner(System.in);

        boolean running = true;

        //Login Menu. Allows for users to Login or Register for a new account
        //While loop waiting for user input
        while(running){
            System.out.println("Welcome!\n===MAIN MENU===\n1) Register a new account.\n2) Login to your account.\n===Enter Q or q to quit===");
            String input = sc.nextLine();

            //Switch statement to
            switch(input){
                case "1":
                    RegAccount.RegMenu();
                    break;
                case "2":
                    LogAccount.LogMenu();
                    break;
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

