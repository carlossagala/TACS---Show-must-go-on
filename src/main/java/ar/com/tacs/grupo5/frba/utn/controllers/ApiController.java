package ar.com.tacs.grupo5.frba.utn.controllers;

import com.google.gson.Gson;

import ar.com.tacs.grupo5.frba.utn.models.DummyResponse;
import spark.Route;

public class ApiController {

	public static Gson gson = new Gson();

	public static Route getGenericResponse = (request, response) -> {
		response.status(200);
		response.type("application/json");
		return gson.toJson(new DummyResponse("ok"));
	};
}
