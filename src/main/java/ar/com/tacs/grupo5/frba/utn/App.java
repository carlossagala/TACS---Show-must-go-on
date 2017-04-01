package ar.com.tacs.grupo5.frba.utn;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.put;

import ar.com.tacs.grupo5.frba.utn.controllers.ApiController;

public class App {
	private static final int PORT = 8080;

	public static void main(String[] args) {

		port(PORT);

		path("/api", () -> {

			path("/users", () -> {
				get("/", ApiController.getGenericResponse);
				get("/:id/", ApiController.getGenericResponse);
				get("/:id/favmovies/", ApiController.getGenericResponse);
				get("/:id/favactors/", ApiController.getGenericResponse);
				get("/:id/intersection/:id2/", ApiController.getGenericResponse);
				get("/ranking/actor/", ApiController.getGenericResponse);
			});
			path("/user", () -> {
				post("/register/", ApiController.getGenericResponse);
				post("/login/", ApiController.getGenericResponse);
				post("/logout/", ApiController.getGenericResponse);
			});
			path("/search", () -> {
				get("/:type/", ApiController.getGenericResponse);
			});
			path("/favmovies", () -> {
				post("/", ApiController.getGenericResponse);
				get("/:id/", ApiController.getGenericResponse);
				put("/:id/", ApiController.getGenericResponse);
				delete("/:id/", ApiController.getGenericResponse);
				post("/:id/movies/", ApiController.getGenericResponse);
				delete("/:id/movies/:movie_id/", ApiController.getGenericResponse);
				get("/:id/intersection/:id2/", ApiController.getGenericResponse);
				get("/:id/ranking/", ApiController.getGenericResponse);

			});
			path("/favactors", () -> {
				post("/", ApiController.getGenericResponse);
				delete("/:id/", ApiController.getGenericResponse);
			});
			path("/movie", () -> {
				get("/:id/", ApiController.getGenericResponse);
				get("/recommended/", ApiController.getGenericResponse);
			});
			path("/actor", () -> {
				get("/:id/", ApiController.getGenericResponse);
			});
		});
		System.out.println("La aplicación levantó correctamente y escucha en el puerto " + PORT);
	}
}
