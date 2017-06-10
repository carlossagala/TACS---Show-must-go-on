package ar.com.tacs.grupo5.frba.utn.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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
import ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.Search;
import ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.SearchResult;
import ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.SearchResultActor;
import ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.SearchResultMovie;

@Component
public class SearchServiceImpl implements SearchService {

	private static Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);

	private String appKey = "bd9625fb42f6141aac0823a396d406ba";

	private RestTemplate restTemplate;

	private Gson gson;

	@Autowired
	public SearchServiceImpl() {
		super();
		this.restTemplate = new RestTemplate();
		this.gson = new Gson();
	}
	
	
	
	@Override
	public Search searchByActor(String query) throws ResourceNotFound, BadRequest {
		String requestUrl = "https://api.themoviedb.org/3/search/person?api_key=" + appKey + "&language=es&page=1&query="+ query;
		
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
		Search search = gson.fromJson(response.getBody(), Search.class);
		
		JsonParser jsonParser = new JsonParser();
		JsonObject jsonObject = jsonParser.parse(response.getBody()).getAsJsonObject();
		JsonElement results = jsonObject.get("results");
		Type listType = new TypeToken<ArrayList<SearchResultActor>>() {
		}.getType();
		ArrayList<SearchResult> possibleActors = gson.fromJson(results, listType);
		search.setResult(possibleActors);
		return search;
	}

	@Override
	public Search searchByMovie(String query) throws ResourceNotFound, BadRequest {
		String requestUrl = "https://api.themoviedb.org/3/search/movie?api_key=" + appKey + "&language=es&page=1&query="+ query;
		
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
		Search search = gson.fromJson(response.getBody(), Search.class);
		
		JsonParser jsonParser = new JsonParser();
		JsonObject jsonObject = jsonParser.parse(response.getBody()).getAsJsonObject();
		JsonElement results = jsonObject.get("results");
		Type listType = new TypeToken<ArrayList<SearchResultMovie>>() {
		}.getType();
		ArrayList<SearchResult> possibleMovies = gson.fromJson(results, listType);
		search.setResult(possibleMovies);
		return search;
	}


	@Override
	public Search searchMulti(String query) {
		
		Search searchByActor = searchByActor(query);
		Search searchByMovie = searchByMovie(query);
		
		List<SearchResult> results;
		results= searchByActor.getResult();
		results.addAll(searchByMovie.getResult());
		Search search= new Search();
		
		search.setResult(results);
		search.setPage(1);
		search.setTotal_results(searchByActor.getTotal_results()+searchByMovie.getTotal_results());
				
		return search;
	}
	
	
	
	

}
