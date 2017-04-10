package ar.com.tacs.grupo5.frba.utn;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.staticFiles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ar.com.tacs.grupo5.frba.utn.controllers.ApiController;

@SpringBootApplication
@PropertySource("classpath:application.properties")
@ComponentScan({ "ar.com.tacs.grupo5.frba.utn" })
public class App {
	private static Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(App.class);
		ApiController apiController = ctx.getBean(ApiController.class);
		Environment environment = ctx.getBean(Environment.class);
		String port = environment.getProperty("server.port");
		sparkInit(apiController,Integer.valueOf(port));
		logger.info("La aplicación levantó correctamente y escucha en el puerto " + port);
	}

	public static void sparkInit(ApiController apiController,int port) {
		port(port);
		staticFiles.location("/public");

		path("/api", () -> {
			get("/hello/", apiController.helloWorld);
			path("/users", () -> {
				get("/", apiController.getUsers);
				get("/:id/", apiController.getUser);
				get("/:id/favmovies/", apiController.getUserFavMovies);
				get("/:id/favactors/", apiController.getserFavActors);
				get("/:id/intersection/:id2/", apiController.getUserIntersection);
				get("/ranking/actor/", apiController.getRankingActor);
			});
			path("/user", () -> {
				post("/register/", apiController.registerUser);
				post("/login/", apiController.login);
				post("/logout/", apiController.logout);
			});
			path("/search", () -> {
				get("/:type/", apiController.searchBy);
			});
			path("/favmovies", () -> {
				post("/", apiController.createNewList);
				get("/:id/", apiController.getFavMovieDetail);
				put("/:id/", apiController.updateFavMoviesDetail);
				delete("/:id/", apiController.deleteFavMoviesList);
				post("/:id/movies/", apiController.addMovieToList);
				delete("/:id/movies/:movie_id/", apiController.deleteMovieFromList);
				get("/:id/intersection/:id2/", apiController.getUserIntersection);
				get("/:id/ranking/", apiController.getRankingFromList);

			});
			path("/favactors", () -> {
				post("/", apiController.addActorToList);
				delete("/:id/", apiController.deleteActorFromList);
			});
			path("/movie", () -> {
				get("/:id/", apiController.getMovieById);
			});
			path("/movies", () -> {
				get("/recommended/", apiController.getRecommendedMovies);
			});
			path("/actor", () -> {
				get("/:id/", apiController.getActorById);
			});
		});
	}

	@Bean
	public Gson gson() {
		return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
	}


}
