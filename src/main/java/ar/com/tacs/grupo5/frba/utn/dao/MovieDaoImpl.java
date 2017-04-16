package ar.com.tacs.grupo5.frba.utn.dao;

import org.springframework.stereotype.Repository;

import ar.com.tacs.grupo5.frba.utn.dao.repository.MovieRepository;
import ar.com.tacs.grupo5.frba.utn.entity.MovieEntity;
import ar.com.tacs.grupo5.frba.utn.mapper.MovieMapper;
import ar.com.tacs.grupo5.frba.utn.models.Movie;

@Repository
public class MovieDaoImpl implements MovieDao {
	
	private MovieRepository movieRepository;
	
	private MovieMapper movieMapper;

	@Override
	public Movie getMovie(String id) {
		MovieEntity movieEnt = movieRepository.findOne(id);
		return movieMapper.entityToDto(movieEnt);
	}

	@Override
	public Movie saveMovie(Movie movie) {
		MovieEntity movieEnt = movieMapper.dtoToEntity(movie);
		Movie createdMovie = movieMapper.entityToDto(movieRepository.saveAndFlush(movieEnt));
		return createdMovie;
	}
	

}
