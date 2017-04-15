package ar.com.tacs.grupo5.frba.utn.dao;

import org.springframework.stereotype.Repository;

import ar.com.tacs.grupo5.frba.utn.dao.repository.FavMovieRepository;
import ar.com.tacs.grupo5.frba.utn.entity.FavMovieEntity;
import ar.com.tacs.grupo5.frba.utn.mapper.FavMovieMapper;
import ar.com.tacs.grupo5.frba.utn.models.FavMovie;

@Repository
public class MovieDaoImpl implements MovieDao {
	
	private FavMovieRepository favMovieRepository;
	
	private FavMovieMapper favMovieMapper;

	@Override
	public FavMovie getFavMovieList(String id) {
		return favMovieMapper.entityToDto(favMovieRepository.findOne(id));
	}

	@Override
	public FavMovie saveFavMovie(FavMovie favMovie) {
		FavMovieEntity favMovieEntity = favMovieMapper.dtoToEntity(favMovie);
		return favMovieMapper.entityToDto(favMovieRepository.saveAndFlush(favMovieEntity));
	}
}
