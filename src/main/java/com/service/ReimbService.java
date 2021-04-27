package com.service;

import com.model.Reimbursement;
import com.model.Request;

import java.util.List;

public interface ReimbService {
    public int addReimbursementRequest(Request req);

    public List<Reimbursement> getAllReimb();
    public Reimbursement getReimbBySerial(int id);
    public List<Reimbursement> getReimbByRequestee(int id);

    public void updateStatus(int status, int reslover, List<Integer> ids);
}
