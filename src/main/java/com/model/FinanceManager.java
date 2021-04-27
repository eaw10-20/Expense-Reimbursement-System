package com.model;

import java.util.List;

public class FinanceManager extends User{

    public static final int roleID = 2;
    private List<Reimbursement> reimbursements;

    //constructors
    //------------

    public FinanceManager(){
        super(roleID);
    }


    //getters and setters
    //-------------------


    //methods
    //-------

    public void viewRequests(){

    }

    public void updateRequest(){

    }
}
