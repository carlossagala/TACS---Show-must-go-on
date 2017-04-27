package ar.com.tacs.grupo5.frba.utn.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import ar.com.tacs.grupo5.frba.utn.entity.FavMoviesEntity;
import ar.com.tacs.grupo5.frba.utn.entity.MovieEntity;

public interface MovieRepository extends PagingAndSortingRepository<MovieEntity, String>{
	MovieEntity findByIdMovieAndFavMovie(String idMovie,FavMoviesEntity favMovieEntity);
	Page<FavMoviesEntity> findByFavMovie(FavMoviesEntity favMovieEntity,Pageable pageable);
	Long removeByIdMovie(String idMovie);
}
