package ar.com.tacs.grupo5.frba.utn.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.com.tacs.grupo5.frba.utn.dao.FavMoviesDao;
import ar.com.tacs.grupo5.frba.utn.dao.MovieDao;
import ar.com.tacs.grupo5.frba.utn.dao.repository.MovieRepository;
import ar.com.tacs.grupo5.frba.utn.entity.FavMoviesEntity;
import ar.com.tacs.grupo5.frba.utn.entity.MovieEntity;
import ar.com.tacs.grupo5.frba.utn.exceptions.ResourceNotFound;
import ar.com.tacs.grupo5.frba.utn.models.Movie;

@Repository
@Transactional
public class MovieDaoImpl implements MovieDao {

	@Autowired
	private FavMoviesDao favMoviesDao;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	public MovieDaoImpl(MovieRepository movieRepository, FavMoviesDao favMoviesDao)
	{
		this.movieRepository = movieRepository;
		this.favMoviesDao = favMoviesDao;
	}
	
	@Override
	public MovieEntity getMovie(String idFavMovie,String idMovie) {
		MovieEntity movieEnt = movieRepository.findByIdMovieAndFavMovie(idMovie, favMoviesDao.findOne(idFavMovie));
		return movieEnt;
	}

	@Override
	public void saveMovie(MovieEntity movieEntity) {
		MovieEntity movieEntityExistente = movieRepository.findByIdMovieAndFavMovie(movieEntity.getIdMovie(),movieEntity.getFavMovie());
		if(movieEntityExistente==null){
			movieRepository.save(movieEntity);
		}
	}

	@Override
	@Transactional
	public void deleteMovie(Movie movie) {
		FavMoviesEntity favMovieEntity = favMoviesDao.findOne(movie.getFavMovieId());
		if(favMovieEntity==null){
			throw new ResourceNotFound();
		}
		MovieEntity movieEntity = movieRepository.findByIdMovieAndFavMovie(movie.getMovieId(),favMovieEntity);
		if(movieEntity==null){
			throw new ResourceNotFound();
		}
		movieRepository.delete(movieEntity);
	}

	@Override
	public List<MovieEntity> getMoviesByFavMovie(FavMoviesEntity favMovie) {
		List<MovieEntity> findByFavMovie = movieRepository.findByFavMovie(favMovie);
		return findByFavMovie;
	}
	
}
