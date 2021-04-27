package com.controller;

import com.model.User;
import com.service.ReimbService;
import com.service.ReimbServiceImpl;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.List;

public class FinManController {

    //constructor
    public FinManController(Javalin app){
    }

    /**
     * Aquires all requests from all users. Should not be used for individual employers
     * The list of users is converted into a JSON and added to the context
     * @param context
     */
    public static void getAllRequests(Context context){
        if(context.sessionAttribute("userType").equals(2)) {
            ReimbService rs = new ReimbServiceImpl();
            context.json(rs.getAllReimb());
            System.out.println("Converted list to JSON");
        }
        else System.out.println("Bad call to getAllRequests");
    }

    /**
     * Takes in status information from the context and sends it towards the database
     * in order to update it with the new information
     * @param context
     */
    public static void updateStatus(Context context){
        if(context.sessionAttribute("userType").equals(2)) {
            ReimbService rs = new ReimbServiceImpl();

            int type = 1;
            List<String> ids = context.formParams("ids");
            System.out.println(ids);
            List<Integer> idList = new ArrayList<>();
            try {
                type = Integer.parseInt(context.formParam("type"));
                for (String num : ids) {
                    idList.add(Integer.parseInt(num));
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            //get user id from session attribute
            User u = context.sessionAttribute("userinfo");
            int resolver = u.getUserID();

            rs.updateStatus(type, resolver, idList);
        }
        else System.out.println("Bad request to update status");
    }
}
