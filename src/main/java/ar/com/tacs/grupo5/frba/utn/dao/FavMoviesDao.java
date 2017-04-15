package ar.com.tacs.grupo5.frba.utn.dao;

import java.util.List;

import ar.com.tacs.grupo5.frba.utn.entity.FavMovieEntity;
import ar.com.tacs.grupo5.frba.utn.models.FavMovie;

public interface FavMoviesDao {
	public List<FavMovie>mapFavMovies(List<FavMovieEntity> favMoviesEntity);
}
