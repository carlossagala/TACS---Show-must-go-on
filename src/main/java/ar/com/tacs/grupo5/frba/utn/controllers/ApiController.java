package ar.com.tacs.grupo5.frba.utn.controllers;

import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.delete;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.path;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.staticFiles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import ar.com.tacs.grupo5.frba.utn.exceptions.BadRequest;
import ar.com.tacs.grupo5.frba.utn.exceptions.NotAuthorized;
import ar.com.tacs.grupo5.frba.utn.exceptions.ResourceNotFound;
import ar.com.tacs.grupo5.frba.utn.models.FavMovies;
import ar.com.tacs.grupo5.frba.utn.models.LoginRequest;
import ar.com.tacs.grupo5.frba.utn.models.LoginResponse;
import ar.com.tacs.grupo5.frba.utn.models.Movie;
import ar.com.tacs.grupo5.frba.utn.models.PagedResponse;
import ar.com.tacs.grupo5.frba.utn.models.Response;
import ar.com.tacs.grupo5.frba.utn.models.User;
import ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.Search;
import ar.com.tacs.grupo5.frba.utn.service.ActorService;
import ar.com.tacs.grupo5.frba.utn.service.FavMoviesService;
import ar.com.tacs.grupo5.frba.utn.service.MovieService;
import ar.com.tacs.grupo5.frba.utn.service.SearchService;
import ar.com.tacs.grupo5.frba.utn.service.UserService;
import spark.Request;
import spark.ResponseTransformer;
import spark.Route;

@Component
public class ApiController {

	private static final String MEDIA_TYPE = "application/json";

	@Autowired
	ResponseTransformer responseTransformer;
	
	@Value("${server.port}")
	private String serverPort;
	@Value("${page.size}")
	private int pageSize;
	
	@PostConstruct
    public void init() {
		sparkInit(this,Integer.valueOf(serverPort),responseTransformer);
    }
	public static void sparkInit(ApiController apiController,int port, ResponseTransformer responseTransformer) {
		port(port);
		staticFiles.location("/public");
		exception(NotAuthorized.class, (exception, request, response) -> {
			response.status(401);
			response.body("Not Authorized");
		});
		exception(BadRequest.class, (exception, request, response) -> {
			response.status(400);
			String message = "Bad Request";
			if(exception.getMessage()!=null){
				message = message+": "+exception.getMessage();
			}
			response.body(message);
		});
		
		
		
		before("/api/*", (request, response) -> {
			String path = request.pathInfo();
			if (!(path.equals("/api/user/login/") || path.equals("/api/user/register/"))){
				try {
					apiController.authenticate(request);
				} catch (Exception e) {
					halt(401, "No se encuentra autenticado");
				}
		}});
		after("/api/*", (request, response) -> {
			response.type(MEDIA_TYPE);
		});

		
		//TODO: falta probar 
//		before("/api/users/:id*", (request, response) -> {
//			try {
//				apiController.validateAuthorization(apiController.autenticar(request));
//			} catch (Exception e) {
//				halt(403, "No tiene los permisos suficientes");
//			}
//		});
		
		
		path("/api", () -> {
			path("/users", () -> {
				get("/", MEDIA_TYPE,apiController.getUsers,responseTransformer);
				get("/:id/",MEDIA_TYPE, apiController.getUser,responseTransformer);
				get("/:id/favmovies/",MEDIA_TYPE, apiController.getUserFavMovies,responseTransformer);
				get("/:id/intersection/:id2/",MEDIA_TYPE, apiController.getListIntersection,responseTransformer);
				get("/:id/favactors/",MEDIA_TYPE, apiController.getAdminUserFavActors,responseTransformer);
				get("/ranking/actors/",MEDIA_TYPE, apiController.getRankingActor,responseTransformer);
			});
			path("/user", () -> {
				post("/register/",MEDIA_TYPE, apiController.registerUser,responseTransformer);
				post("/login/",MEDIA_TYPE, apiController.login,responseTransformer);
				post("/logout/",MEDIA_TYPE, apiController.logout,responseTransformer);
			});
			path("/search", () -> {
				get("/movie/:query/", MEDIA_TYPE,apiController.searchByMovie,responseTransformer);
				get("/actor/:query/", MEDIA_TYPE,apiController.searchByActor,responseTransformer);
				get("/full/:query/", MEDIA_TYPE,apiController.searchByFull,responseTransformer);
			});
			path("/favmovies", () -> {
				post("/", MEDIA_TYPE,apiController.createNewList,responseTransformer);
				get("/:id/",MEDIA_TYPE, apiController.getFavMovieDetail,responseTransformer);
				put("/:id/",MEDIA_TYPE, apiController.updateFavMoviesDetail,responseTransformer);
				delete("/:id/",MEDIA_TYPE, apiController.deleteFavMoviesList,responseTransformer);
				post("/:id/movies/",MEDIA_TYPE, apiController.addMovieToList,responseTransformer);
				delete("/:id/movies/:movie_id/",MEDIA_TYPE, apiController.deleteMovieFromList,responseTransformer);
				get("/:id/intersection/:id2/",MEDIA_TYPE, apiController.getListIntersection,responseTransformer);
				get("/:id/ranking/",MEDIA_TYPE, apiController.getRankingFromList,responseTransformer);

			});
			path("/favactors", () -> {
				get("/",MEDIA_TYPE, apiController.getUserFavActors,responseTransformer);
				post("/",MEDIA_TYPE, apiController.addActorToList,responseTransformer);
				delete("/:id/",MEDIA_TYPE, apiController.deleteActorFromList,responseTransformer);
			});
			path("/movie", () -> {
				get("/:id/",MEDIA_TYPE, apiController.getMovieById,responseTransformer);
			});
			path("/movies", () -> {
				get("/recommended/",MEDIA_TYPE, apiController.getRecommendedMovies,responseTransformer);
			});
			path("/actor", () -> {
				get("/:id/",MEDIA_TYPE, apiController.getActorById,responseTransformer);
			});
		});
	}
	private static Logger logger = LoggerFactory.getLogger(ApiController.class);
	private SearchService searchService;
	private Gson gson;
	private MovieService movieService;
	private UserService userService;
	private ActorService actorService;
	private FavMoviesService favMoviesService;
	private JWTUtils jwtUtils;

	@Autowired
	public ApiController(UserService userService,SearchService searchService,ActorService actorService, MovieService movieService, FavMoviesService favMoviesService, Gson gson, JWTUtils jwtUtils) {
		super();
		this.userService = userService;
		this.searchService = searchService;
		this.actorService = actorService;
		this.movieService = movieService;
		this.favMoviesService = favMoviesService;
		this.gson = gson;
		this.jwtUtils = jwtUtils;
	}

		
	public Route getGenericResponse = (request, response) -> {
		response.status(200);
		PagedResponse resp = new PagedResponse();
		resp.setTotalPages(1);
		resp.setTotalResults(1L);
		resp.setPage(getPage(request));
		
		resp.setData("data");
		ObjectMapper oMapper = new ObjectMapper();
		return oMapper.writeValueAsBytes(resp);
	};

	/**
	 * Returns all users
	 */
	public Route getUsers = (request, response) -> {
		response.status(200);
		response.type(MEDIA_TYPE);
		PagedResponse resp = new PagedResponse();
		List<User> users = new ArrayList<>();
		users = userService.getAllUsers();
		
		resp.setPage(getPage(request));
		resp.setTotalPages(1);
		resp.setTotalResults(Long.valueOf(users.size()));
		resp.setData(users);
		return resp;
	};

	/**
	 * Returns the user with the specified Id
	 */
	public Route getUser = (request, response) -> {
		logger.info(request.pathInfo());

		response.status(200);
		Response resp = new Response();
		
		resp.setData(userService.getUserById(request.attribute("id")));
		return resp;
	};

	/**
	 * Returns the user's favourite movies
	 */
	public Route getUserFavMovies = (request, response) -> {
		return response;
//		response.status(200);
//		String id = request.params(":id");
//		PagedResponse resp = new PagedResponse();
//
//		Set<FavMovie> favMovies = userService.getUserFavMovies(id);
//		resp.setData(favMovies);
//		setPagedResults(resp, favMovies);
//		resp.setPage(getPage(request));
//		
//		return resp;
	};

	/**
	 * Returns the user's favourite actors
	 */
	public Route getUserFavActors = (request, response) -> {
		return response;
//		User user = authenticate(request);
//		
//		PagedResponse resp = new PagedResponse();
//		int page = getPage(request);
//		userService.getFavActors(user.getId(),page,resp);
//		if(resp.getTotalResults()==0){
//			response.status(404);
//		}else{
//			response.status(200);
//		}
//		return resp;
	};
	/**
	 * Returns the user's favourite actors
	 */
	public Route getAdminUserFavActors = (request, response) -> {
		return response;
//		User user = authenticate(request);
//		validateAuthorization(user);
//		String idUser = request.params(":id");
//		int page = getPage(request);
//		PagedResponse resp = new PagedResponse();
//		userService.getFavActors(idUser,page,resp);
//		if(resp.getTotalResults()==0){
//			response.status(404);
//		}else{
//			response.status(200);
//		}
//		return resp;
	};

	private Integer getPage(Request request) {
		Integer page = null;
		try{
			String paramPage = request.queryParams("page");
			if(paramPage==null){
				page = 1;
			}else{
				page = Integer.valueOf(paramPage);
			}
		}catch(Exception e){
			throw new BadRequest("page invalid");
		}
		if(page<1){
			throw new BadRequest("page must be greater than 0");
		}
		return page;
	}

	/**
	 * Returns the intersection between the favourite movies from the two users
	 */
	public Route getListIntersection = (request, response) -> {
		return response;
//		response.status(200);
//
//		PagedResponse resp = new PagedResponse();
//		resp.setTotalPages(1);
//		resp.setTotalResults(1L);
//		resp.setPage(getPage(request));
//		
//		resp.setData(userService.getListIntersection(request.attribute("id"), request.attribute("id2")));
//		return resp;
	};
	
	
	public Route getRankingActor = (request, response) -> {
		return response;
//		response.status(200);
//
//		PagedResponse resp = new PagedResponse();
//		resp.setTotalPages(1);
//		resp.setTotalResults(1L);
//		resp.setPage(getPage(request));
//		
//
//		List<User> users = userService.getAllUsers();
//		
//		List<String> actoresId = new ArrayList<>();
//		
//		users.forEach(u->actoresId.addAll(userService.getFavActorsId(u.getId(), getPage(request)))); 
//		
//		List<ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.Actor > actores = new ArrayList<>();
//		
//		actoresId.forEach(id -> actores.add(actorService.getDetailActor(id)));
//		
//		HashMap<String, Integer > ranking = new HashMap<String,Integer>();
//		
//		actores.forEach(a -> cargarRanking(a,ranking));
//
//		resp.setData(ranking);
//		return resp;
	};

	/**
	 * Registers a user
	 */
	public Route registerUser = (request, response) -> {
		Response resp = new Response();
		
		User userDto = (User) gson.fromJson(request.body(), User.class);
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

		Response resp = new Response();
		
		resp.setData(new User("1", "Kun"));
		return resp;
	};

	/**
	 * Search for a movie
	 */
	public Route searchByMovie = (request, response) -> {
		response.status(200);
		Search search = searchService.searchByMovie(request.params(":query"));
		PagedResponse resp = new PagedResponse();
		resp.setTotalPages(search.getTotal_pages());
		resp.setTotalResults(Long.valueOf(search.getTotal_results()));
		resp.setPage(search.getPage());
		resp.setData(search.getResult());
		return resp;
	};
	
	/**
	 * Search for a actor 
	 */
	
	public Route searchByActor = (request, response) -> {
		response.status(200);
		Search search = searchService.searchByActor(request.params(":query"));
		PagedResponse resp = new PagedResponse();
		resp.setTotalPages(search.getTotal_pages());
		resp.setTotalResults(Long.valueOf(search.getTotal_results()));
		resp.setPage(search.getPage());
		resp.setData(search.getResult());
		return resp;
	};
	
	
	/**
	 * Search for movie-actor.
	 */
	public Route searchByFull = (request, response) -> {
		response.status(200);
		Search search = searchService.searchMulti(request.params(":query"));
		PagedResponse resp = new PagedResponse();
		resp.setTotalPages(0);
		resp.setTotalResults(Long.valueOf(search.getTotal_results()));
		resp.setPage(search.getPage());
		resp.setData(search.getResult());
		return resp;
	};

	/**
	 * Creates a new list of favourite movies
	 */
	public Route createNewList = (request, response) -> {
		return response;
//		response.status(201);
//
//		Response resp = new Response();
//		
//		User user = authenticate(request);
//		String name = null;
//		try{
//			@SuppressWarnings("unchecked")
//			Map<String,Object> requestMap = gson.fromJson(request.body(), HashMap.class);
//			name = (String)requestMap.get("name");
//		}catch(Exception e){
//			response.status(400);
//			return "Bad Request: Parametro name en el body es obligatorio";
//		}
//		FavMovie favMovie = userService.createNewFavMovieList(name,user);
//		if(favMovie!=null){
//			response.status(201);
//		}else{
//			response.status(404);
//		}
//		return resp;
	};

	/**
	 * Returns the favourite movies
	 */
	public Route getFavMovieDetail = (request, response) -> {
		Response resp = new Response();
		
		User user = authenticate(request);
		
		String idFavMovie = request.params(":id");
		
		try
		{
			FavMovies favMovie = favMoviesService.getFavMovieDetail(idFavMovie);
			resp.setData(favMovie);
			response.status(200);
			
		}
		catch (ResourceNotFound e) {
			response.status(404);
			resp.setMessage("Not Found: La lista de películas favoritas no existe");
		}
	
		return resp;
	};

	/**
	 * Updates a list information
	 */
	public Route updateFavMoviesDetail = (request, response) -> {
		String newTitle;
		Response resp = new Response();
		
		User user = authenticate(request);
		
		String idFavMovie = request.params(":id");
		
		try{
			@SuppressWarnings("unchecked")
			Map<String,Object> requestMap = gson.fromJson(request.body(), HashMap.class);
			newTitle = (String)requestMap.get("newTitle");
		}catch(Exception e){
			response.status(400);
			return "Bad Request: Parametro newTitle en el body es obligatorio";
		}
				
		try
		{
			FavMovies favMovie = favMoviesService.updateFavMovie(newTitle,idFavMovie);
			resp.setData(favMovie);
			response.status(200);
			
		}
		catch (ResourceNotFound e) {
			response.status(404);
			resp.setMessage("Not Found: La lista de películas favoritas no existe");
		}
	
		return resp;
	};

	/**
	 * Deletes a list of movies
	 */
	public Route deleteFavMoviesList = (request, response) -> {
		Response resp = new Response();
		
		User user = authenticate(request);
		
		String idFavMovie = request.params(":id");
		
		try
		{
			if (favMoviesService.deleteFavMovie(idFavMovie)) {
				response.status(200);
				resp.setMessage("ok");
			}
			else {
				response.status(500);
				resp.setMessage("Internal Error");
			}
		}
		catch (ResourceNotFound e) {
			response.status(404);
			resp.setMessage("Not Found: La lista de películas favoritas no existe");
		}
	
		return resp;
	};

	/**
	 * Adds a movie to the list
	 */
	public Route addMovieToList = (request, response) -> {
		User user = authenticate(request);
		String idFavMovie = request.params(":id");
		Map<String,Object> requestMap = gson.fromJson(request.body(), HashMap.class);
		String movieId = (String)requestMap.get("id");
		Movie movie = movieService.addMovie(idFavMovie, movieId);
		response.status(200);
		Response resp = new Response();
		resp.setData(movie);
		return resp;
	};

	/**
	 * Removes a movie from the list
	 */
	public Route deleteMovieFromList = (request, response) -> {
		User user = authenticate(request);
		String idFavMovie = request.params(":id");
		Map<String,Object> requestMap = gson.fromJson(request.body(), HashMap.class);
		String movieId = (String)requestMap.get("id");
		movieService.removeMovie(idFavMovie, movieId);
		response.status(200);
		Response resp = new Response();
		resp.setData("Se eliminó la pelicula de la lista");
		return resp;
	};
	
	private void cargarRanking(ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.Actor actor,HashMap<String, Integer> ranking){
		
		if(ranking.get(actor.getName()) != null){
			Integer count = ranking.get(actor);
			count ++;
			ranking.put(actor.getName(),count);
		} else{
			ranking.put(actor.getName(), 1);
		}
		
		
	}
	
	/**
	 * Returns a ranking based on a list
	 */
	public Route getRankingFromList = (request, response) -> {
		return response;
//		User user = authenticate(request);
//		response.status(200);
//		String idList = request.params(":id");
//		PagedResponse resp = new PagedResponse();
//		resp.setTotalPages(1);
//		resp.setTotalResults(1L);
//		resp.setPage(getPage(request));
//		
//
//		Set<FavMovie> listOfFavMovies = userService.getUserFavMovies(user.getId()); 
//		FavMovie lista = listOfFavMovies.stream().filter(m ->  m.getId().equals(idList)).collect(Collectors.toList()).get(0);
//		
//		
//		List<ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.Actor> actores = new ArrayList<>();
//		
//		lista.getMovies().forEach(m -> actores.addAll(movieService.getMovieActors(m.getId())));
//		
//		HashMap<String, Integer > ranking = new HashMap<String,Integer>();
//		
//		actores.forEach(a -> cargarRanking(a,ranking));
//
//		resp.setData(ranking);
//		return resp;
	};

	/**
	 * Marks an actor as favourite
	 */
	public Route addActorToList = (request, response) -> {
		return response;
//		User user = authenticate(request);
//		String id =null;
//		try{
//			@SuppressWarnings("unchecked")
//			Map<String,Object> requestMap = gson.fromJson(request.body(), HashMap.class);
//			id = (String)requestMap.get("id");
//		}catch(Exception e){
//			response.status(400);
//			return "Bad Request: Parametro id en el body es obligatorio";
//		}
//		userService.addFavActor(user.getId(), id);
//		response.status(201);
//		return null;
	};

	/**
	 * Unmarks an actor as favourite
	 */
	public Route deleteActorFromList = (request, response) -> {
		return response;
//		User user = authenticate(request);
//		String id = request.params(":id");
//		userService.deleteFavActor(user.getId(), id);
//		response.status(200);
//		
//		return null;
	};

	/**
	 * Returns a movie by its id
	 */
	public Route getMovieById = (request, response) -> {
		response.status(200);
		String id = request.params(":id");
		Response resp = new Response();
		resp.setData(movieService.getMovieDetail(id));
		return resp;
	};

	/**
	 * Returns recommendations for the user
	 */
	public Route getRecommendedMovies = (request, response) -> {
		return response;
//		User user = authenticate(request);
//		response.status(200);
//
//		PagedResponse resp = new PagedResponse();
//		resp.setTotalPages(1);
//		resp.setTotalResults(2L);
//		resp.setPage(getPage(request));
//		
//	
//		
//		resp.setData(	actorService.getMoviesWithActors(userService.getFavActorsId(user.getId(),getPage(request) )));
////		resp.setData(Arrays.asList(new Movie("1", "Matrix", "image.jpg", "", "", Arrays.asList("")),
////				new Movie("2", "Back to the Future", "image.jpg", "", "", Arrays.asList(""))));
////		resp.setData(Arrays.asList(new Movie("1", "Matrix", "image.jpg", "", "", Arrays.asList("")),
////				new Movie("2", "Back to the Future", "image.jpg", "", "", Arrays.asList(""))));
//		return resp;
	};

	/**
	 * Returns an actor by its id
	 */
	public Route getActorById = (request, response) -> {
		response.status(200);
		String id = request.params(":id");
		Response resp = new Response();
		resp.setData(actorService.getDetailActor(id));
		return resp;
	};


	public User authenticate(Request request) {
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
	public void validateAuthorization(User user){
		if(!"admin".equals(user.getNivel())){
			throw new NotAuthorized();
		}
	}
	private void setPagedResults(PagedResponse resp, @SuppressWarnings("rawtypes") Collection collection) {
		resp.setTotalPages((int) Math.ceil((double)collection.size()/(double)pageSize));
		resp.setTotalResults(Long.valueOf(collection.size()));
	}
}
