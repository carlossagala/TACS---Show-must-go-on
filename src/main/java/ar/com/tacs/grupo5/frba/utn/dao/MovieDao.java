package ar.com.tacs.grupo5.frba.utn.dao;

import ar.com.tacs.grupo5.frba.utn.models.Movie;

public interface MovieDao {

	Movie getMovie(String id);
	
	Movie saveMovie(Movie movie);

	ar.com.tacs.grupo5.frba.utn.models.Movie deleteMovie(ar.com.tacs.grupo5.frba.utn.models.Movie movie);
	
}
