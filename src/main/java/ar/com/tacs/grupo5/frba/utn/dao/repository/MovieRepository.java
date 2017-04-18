package ar.com.tacs.grupo5.frba.utn.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import ar.com.tacs.grupo5.frba.utn.entity.FavMovieEntity;
import ar.com.tacs.grupo5.frba.utn.entity.MovieEntity;

public interface MovieRepository extends PagingAndSortingRepository<MovieEntity, String>{
	MovieEntity findByIdMovie(String idMovie);
	Page<FavMovieEntity> findByFavMovie(FavMovieEntity favMovieEntity,Pageable pageable);
	Long removeByIdMovie(String idMovie);
}
