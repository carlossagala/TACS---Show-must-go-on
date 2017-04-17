package ar.com.tacs.grupo5.frba.utn.dao;

import ar.com.tacs.grupo5.frba.utn.models.FavMovie;

public interface FavMoviesDao {

	FavMovie getFavMovie(String id);
	
	FavMovie saveFavMovie(FavMovie favMovie);

	boolean deleteFavMovie(FavMovie favMovie);
}
