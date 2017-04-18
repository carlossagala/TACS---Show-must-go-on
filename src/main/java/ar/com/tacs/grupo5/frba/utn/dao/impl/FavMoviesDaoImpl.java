package ar.com.tacs.grupo5.frba.utn.dao.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.com.tacs.grupo5.frba.utn.dao.FavMoviesDao;
import ar.com.tacs.grupo5.frba.utn.dao.repository.FavMovieRepository;
import ar.com.tacs.grupo5.frba.utn.mapper.FavMovieMapper;
import ar.com.tacs.grupo5.frba.utn.models.FavMovie;
import ar.com.tacs.grupo5.frba.utn.models.Movie;

@Repository
public class FavMoviesDaoImpl implements FavMoviesDao {
	
	@Autowired
	private FavMovieMapper favMovieMapper;
	
	private FavMovieRepository favMovieRepository;

	public FavMovieRepository getFavMovieRepository() {
		return favMovieRepository;
	}

	public FavMoviesDaoImpl(FavMovieRepository favMovieRepository) {
		this.favMovieRepository = favMovieRepository;
	}

	@Override
	public FavMovie getFavMovie(String id) {
		return favMovieMapper.entityToDto(favMovieRepository.findOne(id));
	}

	@Override
	public FavMovie saveFavMovie(FavMovie favMovie) {
		return favMovieMapper.entityToDto(favMovieRepository.save(favMovieMapper.dtoToEntity(favMovie)));
	}

	/*@Override
	@Transactional
	public boolean deleteFavMovie(FavMovie favMovie) {
		MovieDao movDao = new MovieDaoImpl();
		
		for(Movie mov : favMovie.getMovies()){
			movDao.deleteMovie(mov);
		}
		
		try {
			favMovieRepository.delete(favMovieMapper.dtoToEntity(favMovie));
		}
		catch(IllegalArgumentException e) {
			return false;
		}
		return true;
	}*/

}
