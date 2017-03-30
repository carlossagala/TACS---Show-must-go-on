package ar.com.tacs.grupo5.frba.utn.controllers;

import com.google.gson.Gson;

import spark.Route;

public class UserController {
	
	public static Route register = (request, response) ->{
		//esto se encarga de convertir a json y viceversa 
		Gson gson = new Gson();
		   response.status(200);
           response.type("application/json");
		return gson.toJson("Mock para registro de usuarios");
		
	};

}
