package ar.com.tacs.grupo5.frba.utn.service;

import java.util.List;

import ar.com.tacs.grupo5.frba.utn.exceptions.ResourceNotFound;
import ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.Actor;
import ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.Images;
import ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.Movie;
import ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.Reviews;

public interface MovieService {

	public Reviews getReviews(String id);

	public Images getImages(String id);

	public Movie getMovieDetail(String id) throws ResourceNotFound;

	public List<Actor> getMovieActors(String id);

	void addMovie(String idFavMovie, String movieId) throws ResourceNotFound;

	void removeMovie(String idFavMovie, String movieId) throws ResourceNotFound;
	
	

}
