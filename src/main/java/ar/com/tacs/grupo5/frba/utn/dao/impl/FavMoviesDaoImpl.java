package ar.com.tacs.grupo5.frba.utn.dao.impl;

import java.util.HashSet;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.com.tacs.grupo5.frba.utn.dao.FavMoviesDao;
import ar.com.tacs.grupo5.frba.utn.dao.repository.FavMoviesRepository;
import ar.com.tacs.grupo5.frba.utn.entity.FavMoviesEntity;
import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;
import ar.com.tacs.grupo5.frba.utn.mapper.FavMoviesMapper;
import ar.com.tacs.grupo5.frba.utn.models.FavMovies;

@Repository
public class FavMoviesDaoImpl implements FavMoviesDao {
	@Autowired
	private EntityManager em;
	
	private FavMoviesRepository favMovieRepository;

	public FavMoviesRepository getFavMovieRepository() {
		return favMovieRepository;
	}

	public FavMoviesDaoImpl(FavMoviesRepository favMovieRepository) {
		this.favMovieRepository = favMovieRepository;
	}

	@Override
	public FavMoviesEntity getFavMovie(String id) {
		return favMovieRepository.findOne(id);
	}

	@Override
	public FavMoviesEntity saveFavMovie(FavMoviesEntity favMovie) {
		FavMoviesEntity fav = favMovieRepository.save(favMovie);
		em.flush();
		return fav; 
	}

	@Override
	public FavMoviesEntity findOne(String id) {
		return this.favMovieRepository.findOne(id);
	}

	@Override
	public HashSet<FavMoviesEntity> getFavMoviesByUser(UserEntity user) {
		return this.favMovieRepository.findByUser(user);		
	}

//	@Override
//	@Transactional
//	public void save(FavMoviesEntity fm) {
//		this.favMovieRepository.save(fm);
//	}

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
