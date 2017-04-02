package ar.com.tacs.grupo5.frba.utn.controllers;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import ar.com.tacs.grupo5.frba.utn.models.Response;
import ar.com.tacs.grupo5.frba.utn.models.User;
import spark.Route;

public class ApiController {

	public static Gson gson = new Gson();

	public static Route getGenericResponse = (request, response) -> {
		response.status(200);
		response.type("application/json");
		Response resp = new Response();
		resp.setTotal_pages(1);
		resp.setTotal_results(1);
		resp.setStatus_code(0);
		resp.setPage(1);
		resp.setMessage("message");
		resp.setData("data");
		return gson.toJson(resp);
	};

	/**
	 * Returns all users
	 */
	public static Route getUsers = (request, response) -> {
		response.status(200);
		response.type("application/json");
		Response resp = new Response();
		resp.setTotal_pages(1);
		resp.setTotal_results(2);
		resp.setStatus_code(0);
		resp.setPage(1);
		resp.setMessage("mensaje");
		List<User> users = new ArrayList<User>();
		users.add(new User("Kun","Aguero"));
		users.add(new User("Lio", "Messi"));
		resp.setData(gson.toJson(users));
		return gson.toJson(resp);
	};

	/**
	 * Returns the user with the specified Id
	 */
	public static Route getUser = (request, response) -> {
		response.status(200);
		response.type("application/json");
		Response resp = new Response();
		resp.setTotal_pages(1);
		resp.setTotal_results(1);
		resp.setStatus_code(0);
		resp.setPage(1);
		resp.setMessage("mensaje");
		resp.setData(gson.toJson(new User("Kun","Aguero")));
		return gson.toJson(resp);
	};

	/**
	 * Returns the user's favourite movies
	 */
	public static Route getUserFavMovies = (request, response) -> {
		response.status(200);
		response.type("application/json");
		Response resp = new Response();
		resp.setTotal_pages(1);
		resp.setTotal_results(1);
		resp.setStatus_code(0);
		resp.setPage(1);
		resp.setMessage("mensaje");
		resp.setData(gson.toJson(new User("Kun","Aguero")));
		return gson.toJson(resp);
	};

	/**
	 * Returns the user's favourite actors
	 */
	public static Route getserFavActors = (request, response) -> {
		response.status(200);
		response.type("application/json");
		Response resp = new Response();
		resp.setTotal_pages(1);
		resp.setTotal_results(1);
		resp.setStatus_code(0);
		resp.setPage(1);
		resp.setMessage("mensaje");
		resp.setData(gson.toJson(new User("Kun","Aguero")));
		return gson.toJson(resp);
	};

	/**
	 * Returns the intersection between the favourite movies from the two users
	 */
	public static Route getUserIntersection = (request, response) -> {
		response.status(200);
		response.type("application/json");
		Response resp = new Response();
		resp.setTotal_pages(1);
		resp.setTotal_results(1);
		resp.setStatus_code(0);
		resp.setPage(1);
		resp.setMessage("mensaje");
		resp.setData(gson.toJson(new User("Kun","Aguero")));
		return gson.toJson(resp);
	};

	public static Route getRankingActor = (request, response) -> {
		response.status(200);
		response.type("application/json");
		Response resp = new Response();
		resp.setTotal_pages(1);
		resp.setTotal_results(1);
		resp.setStatus_code(0);
		resp.setPage(1);
		resp.setMessage("mensaje");
		resp.setData(gson.toJson(new User("Kun","Aguero")));
		return gson.toJson(resp);
	};

	/**
	 * Registers a user
	 */
	public static Route registerUser = (request, response) -> {
		response.status(200);
		response.type("application/json");
		Response resp = new Response();
		resp.setTotal_pages(1);
		resp.setTotal_results(1);
		resp.setStatus_code(0);
		resp.setPage(1);
		resp.setMessage("mensaje");
		resp.setData(gson.toJson(new User("Kun","Aguero")));
		return gson.toJson(resp);
	};

	/**
	 * Login
	 */
	public static Route login = (request, response) -> {
		response.status(200);
		response.type("application/json");
		Response resp = new Response();
		resp.setTotal_pages(1);
		resp.setTotal_results(1);
		resp.setStatus_code(0);
		resp.setPage(1);
		resp.setMessage("mensaje");
		resp.setData(gson.toJson(new User("Kun","Aguero")));
		return gson.toJson(resp);
	};

	/**
	 * Logout
	 */
	public static Route logout = (request, response) -> {
		response.status(200);
		response.type("application/json");
		Response resp = new Response();
		resp.setTotal_pages(1);
		resp.setTotal_results(1);
		resp.setStatus_code(0);
		resp.setPage(1);
		resp.setMessage("mensaje");
		resp.setData(gson.toJson(new User("Kun","Aguero")));
		return gson.toJson(resp);
	};

	/**
	 * Search for a movie, actor or movie-actor.
	 * The type must be 'actor', 'movie' and 'movie-actor'
	 */
	public static Route searchBy = (request, response) -> {
		response.status(200);
		response.type("application/json");
		Response resp = new Response();
		resp.setTotal_pages(1);
		resp.setTotal_results(1);
		resp.setStatus_code(0);
		resp.setPage(1);
		resp.setMessage("mensaje");
		resp.setData(gson.toJson(new User("Kun","Aguero")));
		return gson.toJson(resp);
	};

	/**
	 * Creates a new list of favourite movies
	 */
	public static Route createNewList = (request, response) -> {
		response.status(200);
		response.type("application/json");
		Response resp = new Response();
		resp.setTotal_pages(1);
		resp.setTotal_results(1);
		resp.setStatus_code(0);
		resp.setPage(1);
		resp.setMessage("mensaje");
		resp.setData(gson.toJson(new User("Kun","Aguero")));
		return gson.toJson(resp);
	};

	/**
	 * Returns the favourite movies
	 */
	public static Route getFavMovies = (request, response) -> {
		response.status(200);
		response.type("application/json");
		Response resp = new Response();
		resp.setTotal_pages(1);
		resp.setTotal_results(1);
		resp.setStatus_code(0);
		resp.setPage(1);
		resp.setMessage("mensaje");
		resp.setData(gson.toJson(new User("Kun","Aguero")));
		return gson.toJson(resp);
	};

	/**
	 * Updates a list information
	 */
	public static Route updateFavMoviesList = (request, response) -> {
		response.status(200);
		response.type("application/json");
		Response resp = new Response();
		resp.setTotal_pages(1);
		resp.setTotal_results(1);
		resp.setStatus_code(0);
		resp.setPage(1);
		resp.setMessage("mensaje");
		resp.setData(gson.toJson(new User("Kun","Aguero")));
		return gson.toJson(resp);
	};

	/**
	 * Deletes a list of movies
	 */
	public static Route deleteFavMoviesList = (request, response) -> {
		response.status(200);
		response.type("application/json");
		Response resp = new Response();
		resp.setTotal_pages(1);
		resp.setTotal_results(1);
		resp.setStatus_code(0);
		resp.setPage(1);
		resp.setMessage("mensaje");
		resp.setData(gson.toJson(new User("Kun","Aguero")));
		return gson.toJson(resp);
	};

	/**
	 * Adds a movie to the list
	 */
	public static Route addMovieToList = (request, response) -> {
		response.status(200);
		response.type("application/json");
		Response resp = new Response();
		resp.setTotal_pages(1);
		resp.setTotal_results(1);
		resp.setStatus_code(0);
		resp.setPage(1);
		resp.setMessage("mensaje");
		resp.setData(gson.toJson(new User("Kun","Aguero")));
		return gson.toJson(resp);
	};

	/**
	 * Removes a movie from the list
	 */
	public static Route deleteMovieFromList = (request, response) -> {
		response.status(200);
		response.type("application/json");
		Response resp = new Response();
		resp.setTotal_pages(1);
		resp.setTotal_results(1);
		resp.setStatus_code(0);
		resp.setPage(1);
		resp.setMessage("mensaje");
		resp.setData(gson.toJson(new User("Kun","Aguero")));
		return gson.toJson(resp);
	};

	/**
	 * Returns a ranking based on a list
	 */
	public static Route getRankingFromList = (request, response) -> {
		response.status(200);
		response.type("application/json");
		Response resp = new Response();
		resp.setTotal_pages(1);
		resp.setTotal_results(1);
		resp.setStatus_code(0);
		resp.setPage(1);
		resp.setMessage("mensaje");
		resp.setData(gson.toJson(new User("Kun","Aguero")));
		return gson.toJson(resp);
	};

	/**
	 * Marks an actor as favourite
	 */
	public static Route addActorToList = (request, response) -> {
		response.status(200);
		response.type("application/json");
		Response resp = new Response();
		resp.setTotal_pages(1);
		resp.setTotal_results(1);
		resp.setStatus_code(0);
		resp.setPage(1);
		resp.setMessage("mensaje");
		resp.setData(gson.toJson(new User("Kun","Aguero")));
		return gson.toJson(resp);
	};

	/**
	 * Unmarks an actor as favourite
	 */
	public static Route deleteActorFromList = (request, response) -> {
		response.status(200);
		response.type("application/json");
		Response resp = new Response();
		resp.setTotal_pages(1);
		resp.setTotal_results(1);
		resp.setStatus_code(0);
		resp.setPage(1);
		resp.setMessage("mensaje");
		resp.setData(gson.toJson(new User("Kun","Aguero")));
		return gson.toJson(resp);
	};

	/**
	 * Returns a movie by its id
	 */
	public static Route getMovieById = (request, response) -> {
		response.status(200);
		response.type("application/json");
		Response resp = new Response();
		resp.setTotal_pages(1);
		resp.setTotal_results(1);
		resp.setStatus_code(0);
		resp.setPage(1);
		resp.setMessage("mensaje");
		resp.setData(gson.toJson(new User("Kun","Aguero")));
		return gson.toJson(resp);
	};

	/**
	 * Returns recommendations for the user
	 */
	public static Route getRecommendedMovies = (request, response) -> {
		response.status(200);
		response.type("application/json");
		Response resp = new Response();
		resp.setTotal_pages(1);
		resp.setTotal_results(1);
		resp.setStatus_code(0);
		resp.setPage(1);
		resp.setMessage("mensaje");
		resp.setData(gson.toJson(new User("Kun","Aguero")));
		return gson.toJson(resp);
	};

	/**
	 * Returns an actor by its id
	 */
	public static Route getActorById = (request, response) -> {
		response.status(200);
		response.type("application/json");
		Response resp = new Response();
		resp.setTotal_pages(1);
		resp.setTotal_results(1);
		resp.setStatus_code(0);
		resp.setPage(1);
		resp.setMessage("mensaje");
		resp.setData(gson.toJson(new User("Kun","Aguero")));
		return gson.toJson(resp);
	};
}
