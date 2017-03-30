package ar.com.tacs.grupo5.frba.utn;

import static spark.Spark.*;

import ar.com.tacs.grupo5.frba.utn.controllers.AdminController;


public class App 
{
    public static void main( String[] args )
    {
    
    port(7117);

    path("/api", () -> {
        
        path("/admin", () -> {
            get("/user/:name",       AdminController.getUser);
            get("/users",     AdminController.getAllUsers);
            
            //it is a example, i dont like very long method names
            get("/intersaction",  AdminController.getIntersactionBetweenListOfUsers);
            
            post("/example", AdminController.postExample);
        });
        
    });
    }
}
