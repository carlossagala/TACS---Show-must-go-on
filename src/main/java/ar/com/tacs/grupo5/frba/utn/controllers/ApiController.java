package ar.com.tacs.grupo5.frba.utn.controllers;

import java.util.ArrayList;

import ar.com.tacs.grupo5.frba.utn.exceptions.NotAuthorized;
import ar.com.tacs.grupo5.frba.utn.models.*;
import java.util.Arrays;
import java.util.Collection;
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
import ar.com.tacs.grupo5.frba.utn.service.UserServiceImpl;
import spark.Request;
import spark.Route;
import spark.utils.CollectionUtils;

@Component
public class ApiController {

	private static final int PAGE_SIZE = 20;
	private static Logger logger = LoggerFactory.getLogger(ApiController.class);
	private ApiService apiService;
	private Gson gson;
	private UserServiceImpl userService;
	private JWTUtils jwtUtils;

	@Autowired
	public ApiController(ApiService apiService, UserServiceImpl userService, Gson gson, JWTUtils jwtUtils) {
		super();
		this.apiService = apiService;
		this.userService = userService;
		this.gson = gson;
		this.jwtUtils = jwtUtils;
	}

		
	public Route getGenericResponse = (request, response) -> {
		response.status(200);
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
		User user = autenticar(request);
		response.status(200);

		PagedResponse resp = new PagedResponse();
		List<User> users = new ArrayList<>();
		users = userService.getAllUsers();
		resp.setMessage("ok");
		resp.setStatusCode(0);
		resp.setPage(1);
		resp.setTotalPages(1);
		resp.setTotalResults(users.size());
		resp.setData(users);
		return resp;
	};

	/**
	 * Returns the user with the specified Id
	 */
	public Route getUser = (request, response) -> {
		logger.info(request.pathInfo());

		response.status(200);
		PagedResponse resp = new PagedResponse();
		resp.setTotalPages(1);
		resp.setTotalResults(1);
		resp.setStatusCode(0);
		resp.setPage(1);
		resp.setMessage("ok");
		resp.setData(userService.getUserById(request.attribute("id")));
		return resp;
	};

	/**
	 * Returns the user's favourite movies
	 */
	public Route getUserFavMovies = (request, response) -> {
		response.status(200);

		PagedResponse resp = new PagedResponse();
		List<FavMovie> favMovies = userService.getUserFavMovies(request.attribute("id"));
		resp.setData(favMovies);
		resp.setTotalPages(favMovies.size()/PAGE_SIZE);
		resp.setTotalResults(favMovies.size());
		resp.setStatusCode(0);
		resp.setPage(1);
		resp.setMessage("ok");
		return resp;
	};

	/**
	 * Returns the user's favourite actors
	 */
	public Route getUserFavActors = (request, response) -> {
		User user = autenticar(request);
		
		PagedResponse resp = new PagedResponse();

		List<String> ids = userService.getFavActors(user.getId());
		if(CollectionUtils.isEmpty(ids)){
			response.status(404);
		}else{
			response.status(200);
			setPagedResults(resp,ids);
			resp.setData(ids);
		}
		return resp;
	};
	/**
	 * Returns the user's favourite actors
	 */
	public Route getAdminUserFavActors = (request, response) -> {
		User user = autenticar(request);
		validateAuthorization(user);
		String idUser = request.params(":id");

		PagedResponse resp = new PagedResponse();

		List<String> ids = userService.getFavActors(idUser);
		if(CollectionUtils.isEmpty(ids)){
			response.status(404);
		}else{
			response.status(200);
			setPagedResults(resp,ids);
			resp.setData(ids);
		}

		return resp;
	};

	/**
	 * Returns the intersection between the favourite movies from the two users
	 */
	public Route getUserIntersection = (request, response) -> {
		response.status(200);

		PagedResponse resp = new PagedResponse();
		resp.setTotalPages(1);
		resp.setTotalResults(1);
		resp.setStatusCode(0);
		resp.setPage(1);
		resp.setMessage("ok");
		resp.setData(Arrays.asList(new Movie("1", "Matrix", "image.jpg", "", "", Arrays.asList("")),
				new Movie("2", "Back to the Future", "image.jpg", "", "", Arrays.asList(""))));
		return resp;
	};

	public Route getRankingActor = (request, response) -> {
		response.status(200);

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
		return resp;
	};

	/**
	 * Registers a user
	 */
	public Route registerUser = (request, response) -> {
		Response resp = new Response();
		resp.setStatusCode(0);
		resp.setMessage("ok");
		ObjectMapper oMapper = new ObjectMapper();
		User userDto = oMapper.readValue(request.body(), User.class);
		resp.setData(userService.saveUser(userDto));
		return resp;
	};

	/**
	 * Login
	 */
	public Route login = (request, response) -> {
		response.status(200);

		LoginRequest req = (LoginRequest) gson.fromJson(request.body(), LoginRequest.class);
		User user = userService.findByUserNameAndPass(req.getUserName(), req.getPassword());
		if (user == null) {
			response.status(401);
			return null;
		}
		return new LoginResponse(jwtUtils.generateToken(user));
	};

	/**
	 * Logout
	 */
	public Route logout = (request, response) -> {
		response.status(200);

		PagedResponse resp = new PagedResponse();
		resp.setTotalPages(1);
		resp.setTotalResults(1);
		resp.setStatusCode(0);
		resp.setPage(1);
		resp.setMessage("ok");
		resp.setData(new User("1", "Kun"));
		return resp;
	};

	/**
	 * Search for a movie, actor or movie-actor. The type must be 'actor',
	 * 'movie' and 'movie-actor'
	 */
	public Route searchBy = (request, response) -> {
		response.status(200);

		PagedResponse resp = new PagedResponse();
		resp.setTotalPages(1);
		resp.setTotalResults(1);
		resp.setStatusCode(0);
		resp.setPage(1);
		resp.setMessage("ok");
		resp.setData(Arrays.asList(new Search("123", "actor", "Emma Stone")));
		return resp;
	};

	/**
	 * Creates a new list of favourite movies
	 */
	public Route createNewList = (request, response) -> {
		response.status(200);

		Response resp = new Response();
		resp.setStatusCode(0);
		resp.setMessage("ok");
		return resp;
	};

	/**
	 * Returns the favourite movies
	 */
	public Route getFavMovieDetail = (request, response) -> {
		response.status(200);

		Response resp = new Response();
		resp.setStatusCode(0);
		resp.setMessage("ok");

		FavMovie f1 = new FavMovie();
		f1.setId("1");
		f1.setName("Top 10 Horror Movies");

		resp.setData(f1);
		return resp;
	};

	/**
	 * Updates a list information
	 */
	public Route updateFavMoviesDetail = (request, response) -> {
		response.status(200);

		Response resp = new Response();
		resp.setStatusCode(0);
		resp.setMessage("ok");
		return resp;
	};

	/**
	 * Deletes a list of movies
	 */
	public Route deleteFavMoviesList = (request, response) -> {
		response.status(200);

		Response resp = new Response();
		resp.setStatusCode(0);
		resp.setMessage("ok");
		return resp;
	};

	/**
	 * Adds a movie to the list
	 */
	public Route addMovieToList = (request, response) -> {
		response.status(200);

		Response resp = new Response();
		resp.setStatusCode(0);
		resp.setMessage("ok");
		return resp;
	};

	/**
	 * Removes a movie from the list
	 */
	public Route deleteMovieFromList = (request, response) -> {
		response.status(200);

		Response resp = new Response();
		resp.setStatusCode(0);
		resp.setMessage("ok");
		return resp;
	};

	/**
	 * Returns a ranking based on a list
	 */
	public Route getRankingFromList = (request, response) -> {
		response.status(200);

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
		return resp;
	};

	/**
	 * Marks an actor as favourite
	 */
	public Route addActorToList = (request, response) -> {
		response.status(200);

		Response resp = new Response();
		resp.setStatusCode(0);
		resp.setMessage("ok");
		return resp;
	};

	/**
	 * Unmarks an actor as favourite
	 */
	public Route deleteActorFromList = (request, response) -> {
		response.status(200);

		Response resp = new Response();
		resp.setStatusCode(0);
		resp.setMessage("ok");
		return resp;
	};

	/**
	 * Returns a movie by its id
	 */
	public Route getMovieById = (request, response) -> {
		response.status(200);

		Response resp = new Response();
		resp.setStatusCode(0);
		resp.setMessage("ok");
		resp.setData(new Movie("1", "Matrix", "image.jpg", "", "", Arrays.asList("")));
		return resp;
	};

	/**
	 * Returns recommendations for the user
	 */
	public Route getRecommendedMovies = (request, response) -> {
		response.status(200);

		PagedResponse resp = new PagedResponse();
		resp.setTotalPages(1);
		resp.setTotalResults(2);
		resp.setStatusCode(0);
		resp.setPage(1);
		resp.setMessage("ok");
		resp.setData(Arrays.asList(new Movie("1", "Matrix", "image.jpg", "", "", Arrays.asList("")),
				new Movie("2", "Back to the Future", "image.jpg", "", "", Arrays.asList(""))));
		return resp;
	};

	/**
	 * Returns an actor by its id
	 */
	public Route getActorById = (request, response) -> {
		response.status(200);

		Response resp = new Response();
		resp.setStatusCode(0);
		resp.setMessage("ok");
		resp.setData(new Actor("1", "imagen.jpg", "Margot Robbie", "", Arrays.asList("123456")));
		return resp;
	};

	public Route helloWorld = (request, response) -> {
		response.status(200);
		Response resp = new Response();
		resp.setStatusCode(0);
		resp.setMessage("ok");
		resp.setData(apiService.helloWorld());
		return resp;
	};

	private User autenticar(Request request) {
		String token = request.headers(jwtUtils.getHeader());

		if (token == null || jwtUtils.isTokenExpired(token)) {
			throw new NotAuthorized();
		}
		User user = userService.findByUserName(jwtUtils.getUsernameFromToken(token));

		if (user == null) {
			throw new NotAuthorized();
		}
		return user;
	}
	private void validateAuthorization(User user){
		if(!"admin".equals(user.getNivel())){
			throw new NotAuthorized();
		}
	}
	private void setPagedResults(PagedResponse resp, Collection collection) {
		resp.setTotalPages(collection.size()/PAGE_SIZE);
		resp.setTotalResults(collection.size());
	}
}
