package ar.com.tacs.grupo5.frba.utn.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import ar.com.tacs.grupo5.frba.utn.entity.FavMovieEntity;
import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;

public interface FavMovieRepository extends PagingAndSortingRepository<FavMovieEntity, String>{
	Page<FavMovieEntity> findByUser(UserEntity user,Pageable pageable);
}
