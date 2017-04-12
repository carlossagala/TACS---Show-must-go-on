package ar.com.tacs.grupo5.frba.utn.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import ar.com.tacs.grupo5.frba.utn.models.Actor;
import ar.com.tacs.grupo5.frba.utn.models.FavMovie;
import ar.com.tacs.grupo5.frba.utn.models.Movie;
import ar.com.tacs.grupo5.frba.utn.models.PagedResponse;
import ar.com.tacs.grupo5.frba.utn.models.Response;
import ar.com.tacs.grupo5.frba.utn.models.Search;
import ar.com.tacs.grupo5.frba.utn.models.User;
import ar.com.tacs.grupo5.frba.utn.service.ApiService;
import ar.com.tacs.grupo5.frba.utn.service.UserService;
import ar.com.tacs.grupo5.frba.utn.service.UserServiceImpl;
import spark.Route;
@Component
public class ApiController {

	private static Logger logger = LoggerFactory.getLogger(ApiController.class);
	private Gson gson;
	private ApiService apiService;
	private UserServiceImpl userService;

	@Autowired
	public ApiController(Gson gson, ApiService apiService, UserServiceImpl userService) {
		super();
		this.gson = gson;
		this.apiService = apiService;
		this.userService = userService;
	}


	public  Route getGenericResponse = (request, response) -> {
		response.status(200);
		response.type("application/json");
		PagedResponse resp = new PagedResponse();
		resp.setTotalPages(1);
		resp.setTotalResults(1);
		resp.setStatusCode(0);
		resp.setPage(1);
		resp.setMessage("ok");
		resp.setData("data");
		ObjectMapper oMapper = new ObjectMapper();
		return oMapper.writeValueAsBytes(resp);
	};

	/**
	 * Returns all users
	 */
	public Route getUsers = (request, response) -> {
		response.status(200);
		response.type("application/json");
		PagedResponse resp = new PagedResponse();
		List<User> users = new ArrayList<>();
		users = userService.getAllUsers();
		resp.setMessage("ok");
		resp.setStatusCode(0);
		resp.setPage(1);
		resp.setTotalPages(1);
		resp.setTotalResults(users.size());
		resp.setData(users);
		return gson.toJson(resp);
	};

	/**
	 * Returns the user with the specified Id
	 */
	public  Route getUser = (request, response) -> {
		logger.info(request.pathInfo());
		String responseJson = null;

		response.status(200);
		response.type("application/json");
		PagedResponse resp = new PagedResponse();
		resp.setTotalPages(1);
		resp.setTotalResults(1);
		resp.setStatusCode(0);
		resp.setPage(1);
		resp.setMessage("ok");
		resp.setData(userService.getUserById(request.attribute("id")));
		responseJson = gson.toJson(resp);
		logger.info(responseJson);
		return responseJson;
	};

	/**
	 * Returns the user's favourite movies
	 */
	public  Route getUserFavMovies = (request, response) -> {
		response.status(200);
		response.type("application/json");
		PagedResponse resp = new PagedResponse();
		resp.setTotalPages(1);
		resp.setTotalResults(2);
		resp.setStatusCode(0);
		resp.setPage(1);
		resp.setMessage("ok");
		
		List<FavMovie> favMovies= new ArrayList<>();
		FavMovie f1 = new FavMovie();
		f1.setId("1");
		f1.setName("Top 10 Horror Movies");
		favMovies.add(f1);
		FavMovie f2 = new FavMovie();
		f2.setId("2");
		f2.setName("Best Kubrik Movies");
		favMovies.add(f2);
		
		resp.setData(favMovies);
		return gson.toJson(resp);
	};

	/**
	 * Returns the user's favourite actors
	 */
	public  Route getserFavActors = (request, response) -> {
		response.status(200);
		response.type("application/json");
		PagedResponse resp = new PagedResponse();
		resp.setTotalPages(1);
		resp.setTotalResults(1);
		resp.setStatusCode(0);
		resp.setPage(1);
		resp.setMessage("ok");
		
		List <Actor> favActors = new ArrayList<Actor>();
		
		favActors.add(new Actor("22", "Angelina Jolie", "image.png", "", Arrays.asList("")));
		favActors.add(new Actor("23", "Brad Pitt", "image.png", "", Arrays.asList("")));
		resp.setData(favActors);
		return gson.toJson(resp);
	};

	/**
	 * Returns the intersection between the favourite movies from the two users
	 */
	public  Route getUserIntersection = (request, response) -> {
		response.status(200);
		response.type("application/json");
		PagedResponse resp = new PagedResponse();
		resp.setTotalPages(1);
		resp.setTotalResults(1);
		resp.setStatusCode(0);
		resp.setPage(1);
		resp.setMessage("ok");
		resp.setData(Arrays.asList(new Movie("1","Matrix","image.jpg","","",Arrays.asList("")),
				new Movie("2","Back to the Future","image.jpg","","",Arrays.asList(""))));
		return gson.toJson(resp);
	};

	public  Route getRankingActor = (request, response) -> {
		response.status(200);
		response.type("application/json");
		PagedResponse resp = new PagedResponse();
		resp.setTotalPages(1);
		resp.setTotalResults(1);
		resp.setStatusCode(0);
		resp.setPage(1);
		resp.setMessage("ok");
        List <Actor> favActors = new ArrayList<>();
		
		favActors.add(new Actor("22", "Angelina Jolie", "image.png", "", Arrays.asList("")));
		favActors.add(new Actor("23", "Brad Pitt", "image.png", "", Arrays.asList("")));
		resp.setData(favActors);
		return gson.toJson(resp);
	};

	/**
	 * Registers a user
	 */
	public  Route registerUser = (request, response) -> {
		Response resp = new Response();
		resp.setStatusCode(0);
		resp.setMessage("ok");
		ObjectMapper oMapper = new ObjectMapper();
		User userDto = oMapper.readValue(request.body(), User.class);
		resp.setData(userService.saveUser(userDto));
		return gson.toJson(resp);
	};

	/**
	 * Login
	 */
	public  Route login = (request, response) -> {
		response.status(200);
		response.type("application/json");
		PagedResponse resp = new PagedResponse();
		resp.setTotalPages(1);
		resp.setTotalResults(1);
		resp.setStatusCode(0);
		resp.setPage(1);
		resp.setMessage("ok");
		resp.setData(new User("1","Kun"));
		return gson.toJson(resp);
	};

	/**
	 * Logout
	 */
	public  Route logout = (request, response) -> {
		response.status(200);
		response.type("application/json");
		PagedResponse resp = new PagedResponse();
		resp.setTotalPages(1);
		resp.setTotalResults(1);
		resp.setStatusCode(0);
		resp.setPage(1);
		resp.setMessage("ok");
		resp.setData(new User("1","Kun"));
		return gson.toJson(resp);
	};

	/**
	 * Search for a movie, actor or movie-actor.
	 * The type must be 'actor', 'movie' and 'movie-actor'
	 */
	public  Route searchBy = (request, response) -> {
		response.status(200);
		response.type("application/json");
		PagedResponse resp = new PagedResponse();
		resp.setTotalPages(1);
		resp.setTotalResults(1);
		resp.setStatusCode(0);
		resp.setPage(1);
		resp.setMessage("ok");
		resp.setData(Arrays.asList(new Search("123","actor","Emma Stone")));
		return gson.toJson(resp);
	};

	/**
	 * Creates a new list of favourite movies
	 */
	public  Route createNewList = (request, response) -> {
		response.status(200);
		response.type("application/json");
		Response resp = new Response();
		resp.setStatusCode(0);
		resp.setMessage("ok");
		return gson.toJson(resp);
	};

	/**
	 * Returns the favourite movies
	 */
	public  Route getFavMovieDetail = (request, response) -> {
		response.status(200);
		response.type("application/json");
		Response resp = new Response();
		resp.setStatusCode(0);
		resp.setMessage("ok");
		
		FavMovie f1 = new FavMovie();
		f1.setId("1");
		f1.setName("Top 10 Horror Movies");
		
		resp.setData(f1);
		return gson.toJson(resp);
	};

	/**
	 * Updates a list information
	 */
	public  Route updateFavMoviesDetail = (request, response) -> {
		response.status(200);
		response.type("application/json");
		Response resp = new Response();
		resp.setStatusCode(0);
		resp.setMessage("ok");
		return gson.toJson(resp);
	};

	/**
	 * Deletes a list of movies
	 */
	public  Route deleteFavMoviesList = (request, response) -> {
		response.status(200);
		response.type("application/json");
		Response resp = new Response();
		resp.setStatusCode(0);
		resp.setMessage("ok");
		return gson.toJson(resp);
	};

	/**
	 * Adds a movie to the list
	 */
	public  Route addMovieToList = (request, response) -> {
		response.status(200);
		response.type("application/json");
		Response resp = new Response();
		resp.setStatusCode(0);
		resp.setMessage("ok");
		return gson.toJson(resp);
	};

	/**
	 * Removes a movie from the list
	 */
	public  Route deleteMovieFromList = (request, response) -> {
		response.status(200);
		response.type("application/json");
		Response resp = new Response();
		resp.setStatusCode(0);
		resp.setMessage("ok");
		return gson.toJson(resp);
	};

	/**
	 * Returns a ranking based on a list
	 */
	public  Route getRankingFromList = (request, response) -> {
		response.status(200);
		response.type("application/json");
		PagedResponse resp = new PagedResponse();
		resp.setTotalPages(1);
		resp.setTotalResults(1);
		resp.setStatusCode(0);
		resp.setPage(1);
		resp.setMessage("ok");
		
		List<Actor> favActors = new ArrayList<>();
		
		favActors.add(new Actor("22", "Angelina Jolie", "image.png", "", Arrays.asList("")));
		favActors.add(new Actor("23", "Brad Pitt", "image.png", "", Arrays.asList("")));
		
		resp.setData(favActors);
		return gson.toJson(resp);
	};

	/**
	 * Marks an actor as favourite
	 */
	public  Route addActorToList = (request, response) -> {
		response.status(200);
		response.type("application/json");
		Response resp = new Response();
		resp.setStatusCode(0);
		resp.setMessage("ok");
		return gson.toJson(resp);
	};

	/**
	 * Unmarks an actor as favourite
	 */
	public  Route deleteActorFromList = (request, response) -> {
		response.status(200);
		response.type("application/json");
		Response resp = new Response();
		resp.setStatusCode(0);
		resp.setMessage("ok");
		return gson.toJson(resp);
	};

	/**
	 * Returns a movie by its id
	 */
	public  Route getMovieById = (request, response) -> {
		response.status(200);
		response.type("application/json");
		Response resp = new Response();
		resp.setStatusCode(0);
		resp.setMessage("ok");
		resp.setData(new Movie("1","Matrix","image.jpg","","",Arrays.asList("")));
		return gson.toJson(resp);
	};

	/**
	 * Returns recommendations for the user
	 */
	public  Route getRecommendedMovies = (request, response) -> {
		response.status(200);
		response.type("application/json");
		PagedResponse resp = new PagedResponse();
		resp.setTotalPages(1);
		resp.setTotalResults(2);
		resp.setStatusCode(0);
		resp.setPage(1);
		resp.setMessage("ok");
		resp.setData(Arrays.asList(new Movie("1","Matrix","image.jpg","","",Arrays.asList("")),
					new Movie("2","Back to the Future","image.jpg","","",Arrays.asList(""))));
		return gson.toJson(resp);
	};

	/**
	 * Returns an actor by its id
	 */
	public  Route getActorById = (request, response) -> {
		response.status(200);
		response.type("application/json");
		Response resp = new Response();
		resp.setStatusCode(0);
		resp.setMessage("ok");
		resp.setData(new Actor("1","imagen.jpg","Margot Robbie","",Arrays.asList("123456")));
		return gson.toJson(resp);
	};
	
	public  Route helloWorld = (request, response) -> {
		response.status(200);
		response.type("application/json");
		Response resp = new Response();
		resp.setStatusCode(0);
		resp.setMessage("ok");
		resp.setData(apiService.helloWorld());
		return gson.toJson(resp);
	};
}
