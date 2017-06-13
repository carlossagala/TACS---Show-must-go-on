package ar.com.tacs.grupo5.frba.utn.dao;

import java.util.List;

import ar.com.tacs.grupo5.frba.utn.entity.FavMoviesEntity;
import ar.com.tacs.grupo5.frba.utn.entity.MovieEntity;

public interface MovieDao {

	MovieEntity getMovie(String idFavMovie,String idMovie);
	
	void saveMovie(MovieEntity movieEntity);

	void deleteMovie(ar.com.tacs.grupo5.frba.utn.models.Movie movie);
	
	List<MovieEntity> getMoviesByFavMovie(FavMoviesEntity favMovie);
	
}
