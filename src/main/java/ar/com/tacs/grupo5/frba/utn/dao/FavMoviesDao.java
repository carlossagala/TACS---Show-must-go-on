package ar.com.tacs.grupo5.frba.utn.dao;

import ar.com.tacs.grupo5.frba.utn.entity.FavMovieEntity;
import ar.com.tacs.grupo5.frba.utn.models.FavMovie;

public interface FavMoviesDao {

	void save(FavMovieEntity fm);
	FavMovieEntity findOne(String id);
	FavMovie getFavMovie(String id);
	
	FavMovie saveFavMovie(FavMovie favMovie);

	//boolean deleteFavMovie(FavMovie favMovie);
}
