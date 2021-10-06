package DAOs;

import list.ArrayList;
import models.Accounts;
import models.Users;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import exceptions.WithdrawTooMuch;
import exceptions.IncorrectUsername;
import exceptions.IncorrectPassword;
import exceptions.TakenUsername;

public class DAOAccount implements CRUDAccount<Accounts> {
    private Connection conn;
    public int newAc_id;
    public DAOAccount(Connection conn){
        this.conn = conn;
    }

    @Override
    public int getAc_id() throws SQLException{
        //gets the current users account id
        String sql = "SELECT * FROM accounts";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            newAc_id = rs.getInt("ac_id");
        }
        return newAc_id;
    }

    @Override
    public void createAccount(String username, String ac_type) throws SQLException{
        //grab the current account id
        getAc_id();

        String Insert = "INSERT INTO accounts (username, ac_id) VALUES (?,?)";
        PreparedStatement ps = conn.prepareStatement(Insert);
        ps.setString(1, username);
        //Get the new account id by incrementing
        newAc_id++;
        ps.setInt(2, newAc_id);
        ps.executeUpdate();
        Insert = "INSERT INTO accounts_bal (bal, ac_id, ac_type) VALUES (?,?,?)";
        ps = conn.prepareStatement(Insert);
        ps.setInt(1, 0);
        ps.setInt(2, newAc_id);
        ps.setString(3, ac_type);
        ps.executeUpdate();

    }

    @Override
    public ArrayList<Accounts> accountUser(String username) throws SQLException{
        //Find any accounts that is linked to the username
        String getAccounts = "SELECT * FROM accounts_bal a JOIN accounts ua ON a.ac_id = ua.ac_id WHERE username = ?";
        PreparedStatement ps = conn.prepareStatement(getAccounts);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        ArrayList<Accounts> aList = new ArrayList<>();

        while(rs.next()){
            Accounts newAc = new Accounts(rs.getDouble("bal"), rs.getInt("ac_id"), rs.getString("ac_type"));
            aList.add(newAc);

        }
        return aList;
    }

    @Override
    public boolean verifyUsername(String username, int ac_id) throws SQLException{
        //Double-check the account id inputted matches the username
        String check = "SELECT * FROM accounts WHERE (username = ?) AND (ac_id = ?)";
        PreparedStatement ps = conn.prepareStatement(check);
        ps.setString(1, username);
        ps.setInt(2, ac_id);
        ResultSet rs = ps.executeQuery();

        if(!rs.next()){
            System.out.println("Error: Account Id does not match the user");
            return false;
        }
        else{
            return true;
        }

    }

    @Override
    public void deposit(int ac_id, double money) throws SQLException{
        //Deposit money into the account
        String Deposit = "UPDATE accounts_bal SET bal = (bal + ?) WHERE ac_id = ?";
        PreparedStatement ps = conn.prepareStatement(Deposit);
        ps.setDouble(1, money);
        ps.setInt(2, ac_id);
        ps.executeUpdate();
    }

    @Override
    public void withdraw(int ac_id, double money) throws SQLException, WithdrawTooMuch{
        //Withdraw money from the account
        String check = "SELECT bal from accounts_bal WHERE ac_id = ?";
        PreparedStatement ps = conn.prepareStatement(check);
        ps.setInt(1, ac_id);
        ResultSet rs = ps.executeQuery();
        //Ensure they have enough funds
        while(rs.next()){
            double acBal = rs.getDouble("bal");
            if(acBal < money){
                //Throw the exception if they don't have enough
                throw new WithdrawTooMuch();
            }

        }
        String Withdraw = "UPDATE accounts_bal SET bal = (bal - ?) WHERE ac_id = ?";
        ps = conn.prepareStatement(Withdraw);
        ps.setDouble(1, money);
        ps.setInt(2, ac_id);
        ps.executeUpdate();

    }
}
