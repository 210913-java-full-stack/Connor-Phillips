package utils;

import java.sql.SQLException;
import java.sql.Connection;
import models.Accounts;
import list.ArrayList;
import DAOs.DAOAccount;

public class ListAccounts {
    public static void callAccounts(String username){
        System.out.println("===Accounts===");
        try{
            //Retrieve all the accounts connected to the username
            Connection conn = ConnectionManager.getConnection();
            DAOAccount account = new DAOAccount(conn);
            ArrayList<Accounts> aList;
            aList = account.accountUser(username);
            //Print the associated accounts
            for(int i = 0; i < aList.size(); i++){
                PrintList.pList(aList.get(i));
            }
        }
        catch (SQLException message) {
            System.out.println(message.getMessage());
        }
        System.out.println("==============");
    }
}
