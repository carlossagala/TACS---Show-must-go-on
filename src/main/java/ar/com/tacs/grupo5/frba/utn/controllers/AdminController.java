package ar.com.tacs.grupo5.frba.utn.controllers;

import com.google.gson.Gson;

import ar.com.tacs.grupo5.frba.utn.models.User;
import spark.Route;

public class AdminController {
	
	public static Route getUser = (request, response) ->{
		String nameUser = request.params(":name");
		
		//esto se encarga de convertir a json y viceversa 
		Gson gson = new Gson();
		   response.status(200);
           response.type("application/json");
		return gson.toJson(new User(nameUser,nameUser));
		
	};
	
	public static Route getAllUsers = (request, response)->"Users";
	
	public static Route getIntersactionBetweenListOfUsers = (request,response)->"intersection";
	
	public static Route postExample = (request,response)->{
		
		String body = request.body();
		Gson gson = new Gson();
		User aUser;
		aUser = gson.fromJson(body, User.class);
		
		return "Hello " + aUser.getFirstName() + aUser.getLastName();
		
		
	};
}
