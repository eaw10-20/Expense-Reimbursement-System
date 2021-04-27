package com.driver;

import com.frontcontroller.FrontController;
import com.model.Employee;
import com.model.User;
import io.javalin.Javalin;

public class MainDriver {
    public static void main(String[] args) {
        Javalin app = Javalin.create(

                config-> {
                    config.addStaticFiles("/html");
                    config.addStaticFiles("/js");
                    config.addStaticFiles("/css");
                }
        ).start(11235);

        FrontController fc = new FrontController(app);
    }
}
