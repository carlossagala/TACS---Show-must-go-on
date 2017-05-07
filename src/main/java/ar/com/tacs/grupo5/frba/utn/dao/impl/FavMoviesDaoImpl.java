package ar.com.tacs.grupo5.frba.utn.dao.impl;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.com.tacs.grupo5.frba.utn.dao.FavMoviesDao;
import ar.com.tacs.grupo5.frba.utn.dao.repository.FavMoviesRepository;
import ar.com.tacs.grupo5.frba.utn.entity.FavMoviesEntity;
import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;
import ar.com.tacs.grupo5.frba.utn.exceptions.ResourceNotFound;

@Repository
@Transactional
public class FavMoviesDaoImpl implements FavMoviesDao {
	
	@Value("${page.size}")
	private int pageSize;
	
	@Autowired
	private EntityManager em;
	
	private FavMoviesRepository favMoviesRepository;

	@Autowired
	public FavMoviesDaoImpl(FavMoviesRepository favMoviesRepository) {
		this.favMoviesRepository = favMoviesRepository;
	}

	@Override
	public FavMoviesEntity getFavMovie(String id) {
		return favMoviesRepository.findOne(id);
	}

	@Override
	public FavMoviesEntity saveFavMovie(FavMoviesEntity favMovie) {
		FavMoviesEntity fav = favMoviesRepository.save(favMovie);
		em.flush();
		return fav; 
	}

	@Override
	public FavMoviesEntity findOne(String id) {
		return this.favMoviesRepository.findOne(id);
	}

	@Override
	public Page<FavMoviesEntity> getFavMoviesByUser(UserEntity user, int page) {
		return this.favMoviesRepository.findByUser(user, new PageRequest(page, pageSize));		
	}

	@Override
	public void deleteFavMovies(UserEntity user, String idFavMovies) throws ResourceNotFound {
		FavMoviesEntity favMov = favMoviesRepository.findOne(idFavMovies);
		if (favMov == null)
			throw new ResourceNotFound();
		favMoviesRepository.delete(favMov);
	}

}
