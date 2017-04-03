package ar.com.tacs.grupo5.frba.utn;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.staticFiles;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ar.com.tacs.grupo5.frba.utn.controllers.ApiController;

public class App {
	private static final int PORT = 8080;

	public static void main(String[] args) {
		config();
		port(PORT);
		
		staticFiles.location("/public");
		path("/api", () -> {

			path("/users", () -> {
				get("/", ApiController.getUsers);
				get("/:id/", ApiController.getUser);
				get("/:id/favmovies/", ApiController.getUserFavMovies);
				get("/:id/favactors/", ApiController.getserFavActors);
				get("/:id/intersection/:id2/", ApiController.getUserIntersection);
				get("/ranking/actor/", ApiController.getRankingActor);
			});
			path("/user", () -> {
				post("/register/", ApiController.registerUser);
				post("/login/", ApiController.login);
				post("/logout/", ApiController.logout);
			});
			path("/search", () -> {
				get("/:type/", ApiController.searchBy);
			});
			path("/favmovies", () -> {
				post("/", ApiController.createNewList);
				get("/:id/", ApiController.getFavMovieDetail);
				put("/:id/", ApiController.updateFavMoviesDetail);
				delete("/:id/", ApiController.deleteFavMoviesList);
				post("/:id/movies/", ApiController.addMovieToList);
				delete("/:id/movies/:movie_id/", ApiController.deleteMovieFromList);
				get("/:id/intersection/:id2/", ApiController.getUserIntersection);
				get("/:id/ranking/", ApiController.getRankingFromList);

			});
			path("/favactors", () -> {
				post("/", ApiController.addActorToList);
				delete("/:id/", ApiController.deleteActorFromList);
			});
			path("/movie", () -> {
				get("/:id/", ApiController.getMovieById);
			});
			path("/movies", () -> {
				get("/recommended/", ApiController.getRecommendedMovies);
			});
			path("/actor", () -> {
				get("/:id/", ApiController.getActorById);
			});
		});
		System.out.println("La aplicación levantó correctamente y escucha en el puerto " + PORT);
	}

	private static void config() {
		Gson gson = new GsonBuilder()
			    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
			    .create();
		ApiController.gson = gson;
	}
}
