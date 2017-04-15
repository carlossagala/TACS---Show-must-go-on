package ar.com.tacs.grupo5.frba.utn.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import ar.com.tacs.grupo5.frba.utn.models.Actor;
import ar.com.tacs.grupo5.frba.utn.models.Images;
import ar.com.tacs.grupo5.frba.utn.models.Movie;
import ar.com.tacs.grupo5.frba.utn.models.Reviews;

@Component
public class MovieServiceImpl implements MovieService {

	private static Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

	private String appKey = "bd9625fb42f6141aac0823a396d406ba";

	private RestTemplate restTemplate;

	private Gson gson;

	@Autowired
	public MovieServiceImpl() {
		super();
		this.restTemplate = new RestTemplate();
		this.gson = new Gson();
	}

	// TODO para esta estrega la peticion rest se hace sin leguaje
	// TODO: ver si lo hacemos solo que muestre contenido en español o tambien
	// en otro idioma
	@Override
	public Reviews getReviews(String id) {

		String requestUrl = "https://api.themoviedb.org/3/movie/" + id + "/reviews?api_key=" + appKey;
		ResponseEntity<String> response = restTemplate.exchange(requestUrl, HttpMethod.GET, null, String.class);
		logger.info("se recibio el siguiente archivo de json" + response.getBody());
		Reviews reviews = gson.fromJson(response.getBody(), Reviews.class);

		return reviews;

	}

	// TODO: se retornan todas las imagenes mque posee la pelicula
	@Override
	public Images getImages(String id) {
		String requestUrl = "https://api.themoviedb.org/3/movie/" + id + "/images?api_key=" + appKey + "&language=es";
		ResponseEntity<String> response = restTemplate.exchange(requestUrl, HttpMethod.GET, null, String.class);
		logger.info("se recibio el siguiente archivo de json" + response.getBody());
		Images images = gson.fromJson(response.getBody(), Images.class);

		return images;
	}

	@Override
	public List<Actor> getMovieActors(String id) {
		String requestUrl = "https://api.themoviedb.org/3/movie/" + id + "/credits?api_key=" + appKey;
		ResponseEntity<String> response = restTemplate.exchange(requestUrl, HttpMethod.GET, null, String.class);
		logger.info("se recibio el siguiente archivo de json" + response.getBody());
		JsonParser jsonParser = new JsonParser();

		JsonObject jsonObject = jsonParser.parse(response.getBody()).getAsJsonObject();

		JsonElement cast = jsonObject.get("cast");

		Type listType = new TypeToken<ArrayList<Actor>>() {
		}.getType();
		List<Actor> actors = gson.fromJson(cast, listType);
		return actors;
	}

	@Override
	public Movie getMovieDetail(String id) {
		String requestUrl = "https://api.themoviedb.org/3/movie/" + id + "?api_key=" + appKey + "&language=es";
		ResponseEntity<String> response = restTemplate.exchange(requestUrl, HttpMethod.GET, null, String.class);
		logger.info("se recibio el siguiente archivo de json" + response.getBody());
		Movie movie = gson.fromJson(response.getBody(), Movie.class);
		movie.setReviews(getReviews(id));
		movie.setImages(getImages(id));
		movie.setActors(getMovieActors(id));
		return movie;
	}

}
