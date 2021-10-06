package models;

public class Accounts {
    private double bal;
    private int ac_id;
    private String ac_type;

    public Accounts(String ac_type){
        this.bal = 0;
        this.ac_id = ac_id;
        this.ac_type = ac_type;

    }

    public Accounts(int ac_id, String ac_type){
        this.bal = 0;
        this.ac_id = ac_id;
        this.ac_type = ac_type;
    }

    public Accounts(double bal, int ac_id, String ac_type){
        this.bal = bal;
        this.ac_id = ac_id;
        this.ac_type = ac_type;
    }

    public double getBal(){
        return bal;
    }

    public void setBal(double bal){
        this.bal = bal;
    }

    public int getAc_id(){
        return ac_id;
    }

    public String getAc_type() {
        return ac_type;
    }

    public void setAc_type(String ac_type){
        this.ac_type = ac_type;
    }
}
