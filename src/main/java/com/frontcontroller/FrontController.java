package com.frontcontroller;

import com.controller.LoginController;
import com.model.Employee;
import com.model.User;
import io.javalin.Javalin;
import io.javalin.http.Context;

import static io.javalin.apibuilder.ApiBuilder.get;

public class FrontController {
    Javalin app;
    Dispatcher dispatcher;

    //constructors
    public FrontController(Javalin app){
        this.app = app;

        app.before("*", FrontController::checkAllRequests);

        dispatcher = new Dispatcher(app);

    }

    /**
     * Runs before all http requests. Context taken in from request
     * @param context
     */
    public static void checkAllRequests(Context context){
        //TODO: Will need to check if logged in. For now will assume not

        //checking to see if a session has been created for the user yet
        //if no session
        if(context.sessionAttribute("userType") == null){
            System.out.println("No session detected. Creating session");
            //create session
            context.sessionAttribute("userType", 0);
        }
    }
}
