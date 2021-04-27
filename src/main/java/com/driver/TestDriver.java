package com.driver;


import com.model.Reimbursement;
import com.model.User;
import com.service.ReimbService;
import com.service.ReimbServiceImpl;
import com.service.UserService;
import com.service.UserServiceImpl;

import java.util.List;

public class TestDriver {
    public static void main(String[] args) {

        ReimbService rs = new ReimbServiceImpl();

        List<Reimbursement> list = rs.getReimbByRequestee(1);
        System.out.println(list.get(0).getStatus());
    }
}
