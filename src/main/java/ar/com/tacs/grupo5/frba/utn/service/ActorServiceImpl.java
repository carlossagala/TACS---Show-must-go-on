package ar.com.tacs.grupo5.frba.utn.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import ar.com.tacs.grupo5.frba.utn.exceptions.BadRequest;
import ar.com.tacs.grupo5.frba.utn.exceptions.ResourceNotFound;
import ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.Actor;
import ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.Image;
import ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.Movie;

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
	public List<Image> getActorImages(String id) throws ResourceNotFound, BadRequest {
		String requestUrl = "https://api.themoviedb.org/3/person/" + id + "/images?api_key=" + appKey;
		
		ResponseEntity<String> response;
		
		try{
			response = restTemplate.exchange(requestUrl, HttpMethod.GET, null, String.class);
		}
		catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NOT_FOUND)
				throw new ResourceNotFound();
			else
				throw new BadRequest(e.getStatusText());
		}
		
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
	public List<Movie> getActorMovies(String id) throws ResourceNotFound, BadRequest {
		String requestUrl = "https://api.themoviedb.org/3/person/" + id + "/movie_credits?api_key=" + appKey;
		
		ResponseEntity<String> response;
		
		try{
			response = restTemplate.exchange(requestUrl, HttpMethod.GET, null, String.class);
		}
		catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NOT_FOUND)
				throw new ResourceNotFound();
			else
				throw new BadRequest(e.getStatusText());
		}
		
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
	public Actor getDetailActor(String id) throws ResourceNotFound, BadRequest {
		String requestUrl = "https://api.themoviedb.org/3/person/" + id + "?api_key=" + appKey;
		
		ResponseEntity<String> response;
		
		try{
			response = restTemplate.exchange(requestUrl, HttpMethod.GET, null, String.class);
		}
		catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NOT_FOUND)
				throw new ResourceNotFound();
			else
				throw new BadRequest(e.getStatusText());
		}
		
		logger.info("se recibio el siguiente archivo de json" + response.getBody());
		Actor actor = gson.fromJson(response.getBody(), Actor.class);
		actor.setImage(getActorImages(id));
		actor.setMovies(getActorMovies(id));
		return actor;
	}

	
	//TODO: tener en cuenta que este llamado a la api devuelve paginas
	//https://www.themoviedb.org/documentation/api/discover
	@Override
	public List<Movie> getMoviesWithActors(List<String> actorsId) {
		
		
		//287,819 (Bard pitt, edward norton)
		
		List<Movie> movies = new ArrayList<>();
		actorsId.forEach(id-> movies.addAll(getActorMovies(id)));
		
		List<Movie> moviesWithTwoOrMoreActors = movies.stream().filter(movie->repeteableMovie(movie,movies)).collect(Collectors.toList());
		
		List<Movie> moviesWithOutRepeteables = new ArrayList<>(); 
		moviesWithTwoOrMoreActors.stream().forEach(m ->{
			if(notRepeateableInList(m,moviesWithOutRepeteables)){
				moviesWithOutRepeteables.add(m);
		}
			
		});
		
		
		return moviesWithOutRepeteables;
		
		
	}
	
	private Boolean repeteableMovie(Movie movie,List<Movie> movies){
		int count = 0;
		List<Movie> countRepeteables =  movies.stream().filter(m ->
			m.getId().equals(movie.getId())).collect(Collectors.toList());
		count = countRepeteables.size();
		return (count > 1);
	}
	
	private boolean notRepeateableInList( Movie m,List<Movie> movies){
		boolean bool = movies.stream().anyMatch(movie-> movie.getId().equals(m.getId()));
		
		return !(bool);
		
	}
	
	
	
	private String convertIdsToString(List<String> actorsId) {
		return String.join(",", actorsId);
	}

	@Override
	public Actor getActor(String id) throws ResourceNotFound, BadRequest {
		String requestUrl = "https://api.themoviedb.org/3/person/" + id + "?api_key=" + appKey;
		
		ResponseEntity<String> response;
		
		try{
			response = restTemplate.exchange(requestUrl, HttpMethod.GET, null, String.class);
		}
		catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NOT_FOUND)
				throw new ResourceNotFound();
			else
				throw new BadRequest(e.getStatusText());
		}

		logger.info("se recibio el siguiente archivo de json" + response.getBody());
		Actor actor = gson.fromJson(response.getBody(), Actor.class);
		return actor;
	}





}
