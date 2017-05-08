package ar.com.tacs.grupo5.frba.utn.dao;

import org.springframework.data.domain.Page;
import ar.com.tacs.grupo5.frba.utn.entity.FavMoviesEntity;import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;import ar.com.tacs.grupo5.frba.utn.exceptions.ResourceNotFound;

public interface FavMoviesDao {	FavMoviesEntity findOne(String id);
	FavMoviesEntity getFavMovie(String id);
	FavMoviesEntity saveFavMovie(FavMoviesEntity favMovie);
	Page<FavMoviesEntity> getFavMoviesByUser(UserEntity user, int page);
	void deleteFavMovies(String idFavMovies) throws ResourceNotFound;
	Long countByUser(UserEntity user);}