package ar.com.tacs.grupo5.frba.utn.dao;

import java.util.List;

import ar.com.tacs.grupo5.frba.utn.entity.MovieEntity;
import ar.com.tacs.grupo5.frba.utn.models.Movie;

public interface MovieDao {
	public List<Movie> mapMovies(List<MovieEntity> movieList);
}
