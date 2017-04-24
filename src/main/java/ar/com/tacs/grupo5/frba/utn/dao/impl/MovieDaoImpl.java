package ar.com.tacs.grupo5.frba.utn.dao.impl;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.com.tacs.grupo5.frba.utn.dao.FavMoviesDao;
import ar.com.tacs.grupo5.frba.utn.dao.MovieDao;
import ar.com.tacs.grupo5.frba.utn.dao.repository.FavMovieRepository;
import ar.com.tacs.grupo5.frba.utn.dao.repository.MovieRepository;
import ar.com.tacs.grupo5.frba.utn.entity.FavMovieEntity;
import ar.com.tacs.grupo5.frba.utn.entity.MovieEntity;
import ar.com.tacs.grupo5.frba.utn.mapper.MovieMapper;
import ar.com.tacs.grupo5.frba.utn.models.Movie;

@Repository
@Transactional
public class MovieDaoImpl implements MovieDao {

	@Autowired
	private EntityManager em;
	@Autowired
	private FavMovieRepository favMovieRepository;
	@Autowired
	private FavMoviesDao favMoviesDao;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private MovieMapper movieMapper;

	@Override
	public Movie getMovie(String idFavMovie,String idMovie) {
		MovieEntity movieEnt = movieRepository.findByIdMovieAndFavMovie(idMovie, favMoviesDao.findOne(idFavMovie));
		return movieMapper.entityToDto(movieEnt);
	}

	@Override
	public Movie saveMovie(Movie movie) {
		MovieEntity movieEnt = movieMapper.dtoToEntity(movie);
		Movie createdMovie = movieMapper.entityToDto(movieRepository.save(movieEnt));
		return createdMovie;
	}

	@Override
	@Transactional
	public void deleteMovie(Movie movie) {
		FavMovieEntity favMovieEntity = favMoviesDao.findOne(movie.getFavMovieId());
		MovieEntity movieEntity = movieRepository.findByIdMovieAndFavMovie(movie.getMovieId(),favMovieEntity);
//		MovieEntity movieEntityItem = favMovieEntity.getMovies().stream().filter(x->x.getId().equals(movieEntity.getId())).findAny().orElse(null);
//		favMovieEntity.getMovies().remove(movieEntityItem);
		movieRepository.delete(movieEntity);
//		favMovieRepository.save(favMovieEntity);
		em.flush();
	}
	
}
