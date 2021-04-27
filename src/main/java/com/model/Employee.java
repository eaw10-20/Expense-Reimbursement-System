package com.model;

import java.util.List;


public class Employee extends User{

    //stored variables
    //----------------
    public static final int roleID = 1;
    private List<Reimbursement> reimbursements;


    //constructors
    //------------
    public Employee() {
        super(roleID);
    }

    //methods
    //-------
    public void newRequest(){

    }

    public void viewStatus(){

    }
}
