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

import ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.Actor;
import ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.Image;
import ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.Movie;
import ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.Reviews;
import ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.Search;
import ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.SearchResult;
import ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.SearchResultActor;
import ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.SearchResultMovie;

@Component
public class ActorServiceImpl implements ActorService {

	private static Logger logger = LoggerFactory.getLogger(ActorServiceImpl.class);

	private String appKey = "bd9625fb42f6141aac0823a396d406ba";

	private RestTemplate restTemplate;

	private Gson gson;

	@Autowired
	public ActorServiceImpl() {
		super();
		this.restTemplate = new RestTemplate();
		this.gson = new Gson();
	}

	@Override
	public List<Image> getActorImages(String id) {
		String requestUrl = "https://api.themoviedb.org/3/person/" + id + "/images?api_key=" + appKey;
		ResponseEntity<String> response = restTemplate.exchange(requestUrl, HttpMethod.GET, null, String.class);
		logger.info("se recibio el siguiente archivo de json" + response.getBody());

		JsonParser jsonParser = new JsonParser();

		JsonObject jsonObject = jsonParser.parse(response.getBody()).getAsJsonObject();

		JsonElement profiles = jsonObject.get("profiles");

		Type listType = new TypeToken<ArrayList<Image>>() {
		}.getType();
		List<Image> images = gson.fromJson(profiles, listType);

		return images;
	}

	@Override
	public List<Movie> getActorMovies(String id) {
		String requestUrl = "https://api.themoviedb.org/3/person/" + id + "/movie_credits?api_key=" + appKey;
		ResponseEntity<String> response = restTemplate.exchange(requestUrl, HttpMethod.GET, null, String.class);
		logger.info("se recibio el siguiente archivo de json" + response.getBody());

		JsonParser jsonParser = new JsonParser();

		JsonObject jsonObject = jsonParser.parse(response.getBody()).getAsJsonObject();

		JsonElement movies = jsonObject.get("cast");

		Type listType = new TypeToken<ArrayList<Movie>>() {
		}.getType();
		List<Movie> works = gson.fromJson(movies, listType);

		return works;
	}

	@Override
	public Actor getDetailActor(String id) {
		String requestUrl = "https://api.themoviedb.org/3/person/" + id + "?api_key=" + appKey;
		ResponseEntity<String> response = restTemplate.exchange(requestUrl, HttpMethod.GET, null, String.class);
		logger.info("se recibio el siguiente archivo de json" + response.getBody());
		Actor actor = gson.fromJson(response.getBody(), Actor.class);
		actor.setImage(getActorImages(id));
		actor.setMovies(getActorMovies(id));
		return actor;
	}

	



}
