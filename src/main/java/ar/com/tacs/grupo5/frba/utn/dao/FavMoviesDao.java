package ar.com.tacs.grupo5.frba.utn.dao;

import java.util.HashSet;

import ar.com.tacs.grupo5.frba.utn.entity.FavMoviesEntity;
import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;

public interface FavMoviesDao {

	//void save(FavMoviesEntity fm);
	FavMoviesEntity findOne(String id);
	FavMoviesEntity getFavMovie(String id);
	FavMoviesEntity saveFavMovie(FavMoviesEntity favMovie);
	HashSet<FavMoviesEntity> getFavMoviesByUser(UserEntity user);

	//boolean deleteFavMovie(FavMovie favMovie);
}
