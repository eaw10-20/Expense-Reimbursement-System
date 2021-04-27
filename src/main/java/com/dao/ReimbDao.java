package com.dao;

import com.model.Reimbursement;
import com.model.Request;

import java.util.List;

public interface ReimbDao {
    //create
    public int addReimbursementRequest(Request req);
    //read
    public List<Reimbursement> getAllReimb();
    public Reimbursement getReimbBySerial(int id);
    public List<Reimbursement> getReimbByRequestee(int id);
    //update
    public void updateStatus(int status, int resolver, List<Integer> ids);
}
