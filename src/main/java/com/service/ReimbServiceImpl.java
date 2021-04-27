package com.service;

import com.dao.ReimbDao;
import com.dao.ReimbDaoImpl;
import com.model.Reimbursement;
import com.model.Request;

import java.util.List;

public class ReimbServiceImpl implements ReimbService{

    ReimbDao rDao = new ReimbDaoImpl();

    @Override
    public int addReimbursementRequest(Request req) {
        return rDao.addReimbursementRequest(req);
    }

    @Override
    public List<Reimbursement> getAllReimb() {
        return rDao.getAllReimb();
    }

    @Override
    public Reimbursement getReimbBySerial(int id) {
        return rDao.getReimbBySerial(id);
    }

    @Override
    public List<Reimbursement> getReimbByRequestee(int id) {
        return rDao.getReimbByRequestee(id);
    }

    @Override
    public void updateStatus(int status, int resolver, List<Integer> ids) {
        rDao.updateStatus(status, resolver, ids);
    }
}
