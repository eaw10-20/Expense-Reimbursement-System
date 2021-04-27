package com.frontcontroller;

import com.controller.EmployeeController;
import com.controller.FinManController;
import com.controller.LoginController;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Dispatcher {
    EmployeeController control;

    public Dispatcher(Javalin app){
        control = new EmployeeController(app);

        setupAllPaths(app);

    }

    /**
     * Establishes all paths that handle javalin app. Should only be called once
     * @param app a Javalin object handling current http request
     */
    public static void setupAllPaths(Javalin app){
        //all of the paths are added here
        setupLoginPaths(app);
        setupEmployeePaths(app);
        setupFinManPaths(app);
    }

    /**
     * Paths associated with login related calls. Should only be called from setupAllPaths()
     * @param app
     */
    public static void setupLoginPaths(Javalin app){

        app.routes(() ->{
            path("/login", ()->{
                    post(LoginController::postLoginPage);
                    });
            path("/home", ()->
                    post(LoginController::login));
            path("/logout", ()->
                    get(LoginController::logout));
        });
    }

    /**
     * Paths associated with employee related calls. Should only be called from setupAllPaths()
     * @param app
     */
    public static void setupEmployeePaths(Javalin app){
        app.routes(() ->{
            path("/employee", ()->{
                    get(EmployeeController::getRequestHistory);
                    post(EmployeeController::addNewRequest);
            });

        });
    }

    /**
     * Paths associated with financial manager related calls. Should only be called from setupAllPaths()
     * @param app
     */
    public static void setupFinManPaths(Javalin app){
        app.routes(() ->{
            path("/financemanager", ()->{
                get(FinManController::getAllRequests);
                post(FinManController::updateStatus);
            });

        });
    }
}
