package ar.com.tacs.grupo5.frba.utn.dao.repository;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import ar.com.tacs.grupo5.frba.utn.entity.FavMoviesEntity;
import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;
import ar.com.tacs.grupo5.frba.utn.models.FavMovies;
import ar.com.tacs.grupo5.frba.utn.models.User;

public interface FavMoviesRepository extends PagingAndSortingRepository<FavMoviesEntity, String>{
	Page<FavMoviesEntity> findByUser(UserEntity user,Pageable pageable);
    Long countByUser(UserEntity user);
	Set<FavMoviesEntity> findByUser(UserEntity user);
}
