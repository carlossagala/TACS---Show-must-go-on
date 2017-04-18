package ar.com.tacs.grupo5.frba.utn.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.com.tacs.grupo5.frba.utn.dao.repository.MovieRepository;
import ar.com.tacs.grupo5.frba.utn.entity.MovieEntity;
import ar.com.tacs.grupo5.frba.utn.mapper.MovieMapper;
import ar.com.tacs.grupo5.frba.utn.models.Movie;

@Repository
@Transactional
public class MovieDaoImpl implements MovieDao {
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private MovieMapper movieMapper;

	@Override
	public Movie getMovie(String id) {
		MovieEntity movieEnt = movieRepository.findByIdMovie(id);
		return movieMapper.entityToDto(movieEnt);
	}

	@Override
	public Movie saveMovie(Movie movie) {
		MovieEntity movieEnt = movieMapper.dtoToEntity(movie);
		Movie createdMovie = movieMapper.entityToDto(movieRepository.save(movieEnt));
		return createdMovie;
	}

	@Override
	public void deleteMovie(Movie movie) {
//		return movieRepository.removeByIdMovie(movie.getMovieId());
		movieRepository.delete(movieRepository.findByIdMovie(movie.getMovieId()));
	}
	
}
