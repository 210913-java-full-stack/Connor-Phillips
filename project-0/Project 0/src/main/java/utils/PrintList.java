package utils;

import models.Accounts;

public class PrintList {

    public static void pList(Accounts a){
        String output = a.getAc_id() + "|" + a.getAc_type() + ":";
        System.out.printf(output + "$%.2f\n", a.getBal());
    }
}
