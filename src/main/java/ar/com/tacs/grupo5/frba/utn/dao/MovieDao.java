package ar.com.tacs.grupo5.frba.utn.dao;

import ar.com.tacs.grupo5.frba.utn.entity.MovieEntity;
import ar.com.tacs.grupo5.frba.utn.models.Movie;

public interface MovieDao {

	MovieEntity getMovie(String idFavMovie,String idMovie);
	
	void saveMovie(MovieEntity movieEntity);

	void deleteMovie(ar.com.tacs.grupo5.frba.utn.models.Movie movie);
	
}
