package ar.com.tacs.grupo5.frba.utn.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
		return favMovieMapper.entityToDto(favMovieRepository.saveAndFlush(favMovieMapper.dtoToEntity(favMovie)));
	}

}