package DAOs;

import models.Users;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import exceptions.IncorrectUsername;
import exceptions.IncorrectPassword;
import exceptions.TakenUsername;

public class DAOUser implements CRUDUser<Users>{
    private Connection conn;
    public int newAc_id;
    public DAOUser(Connection conn){
        this.conn = conn;
    }
    @Override
    public int getAc_id() throws SQLException{
        //gets the current users account id
        String sql = "SELECT * FROM accounts";
        PreparedStatement AcNum = conn.prepareStatement(sql);
        ResultSet n = AcNum.executeQuery();
        while(n.next()){
            newAc_id = n.getInt("ac_id");
        }
        return newAc_id;

    }

    @Override
    public void Register(Users currentUser) throws SQLException, TakenUsername{
        getAc_id();
        //Double check to ensure user does not already exist
        String sql = "SELECT username FROM users WHERE username = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, currentUser.getUsername());
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            //if Username is already taken exception is thrown
            throw new TakenUsername();
        }
        else{
            //Inserts username and id into the accounts table
            String Insert = "INSERT INTO accounts (username, ac_id) VALUES (?,?)";
            PreparedStatement psInsert = conn.prepareStatement(Insert);
            psInsert.setString(1, currentUser.getUsername());
            newAc_id++;
            psInsert.setInt(2, newAc_id);
            psInsert.executeUpdate();
            //Inserts username password and user's name into the users table
            Insert = "INSERT INTO users (username, password, fName, lName) VALUES (?,?,?,?)";
            psInsert = conn.prepareStatement(Insert);
            psInsert.setString(1, currentUser.getUsername());
            psInsert.setString(2, currentUser.getPassword());
            psInsert.setString(3, currentUser.getfName());
            psInsert.setString(4, currentUser.getlName());
            psInsert.executeUpdate();
            //Inserts id account type and default balance of 0 into the accounts_bal table
            Insert = "INSERT INTO accounts_bal (ac_id, ac_type, bal) VALUES (?,?,? )";
            psInsert = conn.prepareStatement(Insert);
            psInsert.setInt(1, newAc_id);
            psInsert.setString(2, "Checking");
            psInsert.setInt(3, 0);
            psInsert.executeUpdate();


        }

    }

    @Override
    public Users Login(String username, String password) throws SQLException, IncorrectUsername, IncorrectPassword {
        Users login;
        //double-check the username exists
        String getUser = "SELECT * FROM users WHERE username = ?";
        PreparedStatement ps = conn.prepareStatement(getUser);
        ps.setString(1, username);
        ResultSet check = ps.executeQuery();
        //Throw the incorrect Username exception if it is not found
        if(! check.next()){
            throw new IncorrectUsername();
        }
        //Username and password check
        getUser = "SELECT * FROM users WHERE (username = ?) AND (password = ?)";
        ps = conn.prepareStatement(getUser);
        ps.setString(1, username);
        ps.setString(2, password);

        check = ps.executeQuery();
        //When the login is found, log them in
        if(check.next()){
            login = new Users(check.getString("username"), check.getString("password"), check.getString("fName"), check.getString("lName"));
            return login;
        }
        //Throw the IncorrectPassword exception if the login is not found
        else{
            throw new IncorrectPassword();
        }
    }
}
