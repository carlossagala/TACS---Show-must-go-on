package ar.com.tacs.grupo5.frba.utn.dao;

import ar.com.tacs.grupo5.frba.utn.models.FavMovie;

public interface MovieDao {

	public FavMovie getFavMovieList(String id);

	public FavMovie saveFavMovie(FavMovie favMovie);
}
