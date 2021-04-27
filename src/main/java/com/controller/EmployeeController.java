package com.controller;

import com.frontcontroller.Dispatcher;
import com.model.Employee;
import com.model.Reimbursement;
import com.model.Request;
import com.model.User;
import com.service.ReimbService;
import com.service.ReimbServiceImpl;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class EmployeeController {

    Employee employee;

    //constructor
    public EmployeeController(Javalin app){
    }

    /**
     * Used to get a user's request history. The user is taken from the session attribute
     * held within the context. The recieved value is an object containing a list of requests
     * which are converted into a JSON and added to the context
     * @param context
     */
    public static void getRequestHistory(Context context){
        if(context.sessionAttribute("userType").equals(1)) {
            User u = context.sessionAttribute("userinfo");      //to get user id
            ReimbService rs = new ReimbServiceImpl();
            context.json(rs.getReimbByRequestee(u.getUserID()));
            System.out.println("Converted list to JSON");
        }
        else System.out.println("Bad request getRequestHistory");
    }

    /**
     * Recieves a request from the context which is sent to be added to the database The request
     * is then taken back in from the database with additional information to be added to context
     * to be updated on the client end
     * @param context
     */
    public static void addNewRequest(Context context){
        if(context.sessionAttribute("userType").equals(1)) {
            User u = context.sessionAttribute("userinfo");
            ReimbService rs = new ReimbServiceImpl();

            //create a new request using given info
            Request request = context.bodyAsClass(Request.class);
            request.setAuthor(u.getUserID());

            //add request to db and get id
            int id = rs.addReimbursementRequest(request);

            //use id to get completed request
            context.json(rs.getReimbBySerial(id));
            System.out.println("Added new request to database");
        }
        else System.out.println("bad request - addNewRequest");
    }
}
