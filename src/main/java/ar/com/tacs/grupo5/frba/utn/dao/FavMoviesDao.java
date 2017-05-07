package ar.com.tacs.grupo5.frba.utn.dao;

import java.util.HashSet;

import ar.com.tacs.grupo5.frba.utn.entity.FavMoviesEntity;
import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;
import ar.com.tacs.grupo5.frba.utn.exceptions.ResourceNotFound;

public interface FavMoviesDao {

	FavMoviesEntity findOne(String id);
	FavMoviesEntity getFavMovie(String id);
	FavMoviesEntity saveFavMovie(FavMoviesEntity favMovie);
	HashSet<FavMoviesEntity> getFavMoviesByUser(UserEntity user);
	void deleteFavMovies(UserEntity user, String idFavMovies) throws ResourceNotFound;
	Long countByUser(UserEntity dtoToEntity);
}
