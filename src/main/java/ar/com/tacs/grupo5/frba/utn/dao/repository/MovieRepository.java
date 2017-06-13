package ar.com.tacs.grupo5.frba.utn.dao.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import ar.com.tacs.grupo5.frba.utn.entity.FavMoviesEntity;
import ar.com.tacs.grupo5.frba.utn.entity.MovieEntity;

public interface MovieRepository extends PagingAndSortingRepository<MovieEntity, String>{
	MovieEntity findByIdMovieAndFavMovie(String idMovie,FavMoviesEntity favMovieEntity);
	List<MovieEntity> findByFavMovie(FavMoviesEntity favMovieEntity);
	Long removeByIdMovie(String idMovie);
}
