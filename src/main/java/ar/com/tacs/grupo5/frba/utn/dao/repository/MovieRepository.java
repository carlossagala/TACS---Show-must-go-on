package ar.com.tacs.grupo5.frba.utn.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.tacs.grupo5.frba.utn.entity.MovieEntity;
import ar.com.tacs.grupo5.frba.utn.models.Movie;

public interface MovieRepository extends JpaRepository<MovieEntity, String>{
	
}
