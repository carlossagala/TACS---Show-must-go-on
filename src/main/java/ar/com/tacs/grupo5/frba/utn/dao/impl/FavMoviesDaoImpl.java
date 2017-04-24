package ar.com.tacs.grupo5.frba.utn.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.com.tacs.grupo5.frba.utn.dao.FavMoviesDao;
import ar.com.tacs.grupo5.frba.utn.dao.repository.FavMovieRepository;
import ar.com.tacs.grupo5.frba.utn.entity.FavMovieEntity;
import ar.com.tacs.grupo5.frba.utn.mapper.FavMovieMapper;
import ar.com.tacs.grupo5.frba.utn.models.FavMovie;

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

	@Override
	public FavMovieEntity findOne(String id) {
		return this.favMovieRepository.findOne(id);
	}

	@Override
	@Transactional
	public void save(FavMovieEntity fm) {
		this.favMovieRepository.save(fm);
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
