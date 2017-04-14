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
import spark.ResponseTransformer;

@SpringBootApplication
@PropertySource("classpath:application.properties")
@ComponentScan({ "ar.com.tacs.grupo5.frba.utn" })
public class App {
	private static final String MEDIA_TYPE = "application/json";
	private static Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(App.class);
		ApiController apiController = ctx.getBean(ApiController.class);
		ResponseTransformer responseTransformer = ctx.getBean(ResponseTransformer.class);
		
		Environment environment = ctx.getBean(Environment.class);
		String port = environment.getProperty("server.port");
		sparkInit(apiController,Integer.valueOf(port),responseTransformer);
		logger.info("La aplicación levantó correctamente y escucha en el puerto " + port);
	}

	public static void sparkInit(ApiController apiController,int port, ResponseTransformer responseTransformer) {
		port(port);
		staticFiles.location("/public");

		path("/api", () -> {
			get("/hello/", MEDIA_TYPE,apiController.helloWorld,responseTransformer);
			path("/users", () -> {
				get("/", MEDIA_TYPE,apiController.getUsers,responseTransformer);
				get("/:id/",MEDIA_TYPE, apiController.getUser,responseTransformer);
				get("/:id/favmovies/",MEDIA_TYPE, apiController.getUserFavMovies,responseTransformer);
				get("/:id/favactors/",MEDIA_TYPE, apiController.getserFavActors,responseTransformer);
				get("/:id/intersection/:id2/",MEDIA_TYPE, apiController.getUserIntersection,responseTransformer);
				get("/ranking/actor/",MEDIA_TYPE, apiController.getRankingActor,responseTransformer);
			});
			path("/user", () -> {
				post("/register/",MEDIA_TYPE, apiController.registerUser,responseTransformer);
				post("/login/",MEDIA_TYPE, apiController.login,responseTransformer);
				post("/logout/",MEDIA_TYPE, apiController.logout,responseTransformer);
			});
			path("/search", () -> {
				get("/:type/", MEDIA_TYPE,apiController.searchBy,responseTransformer);
			});
			path("/favmovies", () -> {
				post("/", MEDIA_TYPE,apiController.createNewList,responseTransformer);
				get("/:id/",MEDIA_TYPE, apiController.getFavMovieDetail,responseTransformer);
				put("/:id/",MEDIA_TYPE, apiController.updateFavMoviesDetail,responseTransformer);
				delete("/:id/",MEDIA_TYPE, apiController.deleteFavMoviesList,responseTransformer);
				post("/:id/movies/",MEDIA_TYPE, apiController.addMovieToList,responseTransformer);
				delete("/:id/movies/:movie_id/",MEDIA_TYPE, apiController.deleteMovieFromList,responseTransformer);
				get("/:id/intersection/:id2/",MEDIA_TYPE, apiController.getUserIntersection,responseTransformer);
				get("/:id/ranking/",MEDIA_TYPE, apiController.getRankingFromList,responseTransformer);

			});
			path("/favactors", () -> {
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

	@Bean
	public Gson gson() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		return gsonBuilder.create();
	}


}
