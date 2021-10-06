package models;

public class Users {
    private String username;
    private String password;
    private String fName;
    private String lName;

    public Users(String username, String password, String fName, String lName){
        this.username = username;
        this.password = password;
        this.fName = fName;
        this.lName = lName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword() {
        this.password = password;
    }

    public String getfName() {
        return fName;
    }

    public void setfName() {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName() {
        this.lName = lName;
    }
}
