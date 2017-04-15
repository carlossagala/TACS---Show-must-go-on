package ar.com.tacs.grupo5.frba.utn.service;

import java.util.List;

import ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.Actor;
import ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.Images;
import ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.Movie;
import ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.Reviews;
import ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.Search;

public interface MovieService {

	public Reviews getReviews(String id);

	public Images getImages(String id);

	public Movie getMovieDetail(String id);

	public List<Actor> getMovieActors(String id);
	
	

}
