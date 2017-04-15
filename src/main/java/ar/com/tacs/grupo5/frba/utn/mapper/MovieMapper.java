package ar.com.tacs.grupo5.frba.utn.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.tacs.grupo5.frba.utn.dao.repository.FavMovieRepository;
import ar.com.tacs.grupo5.frba.utn.entity.MovieEntity;
import ar.com.tacs.grupo5.frba.utn.models.Movie;

@Component
public class MovieMapper implements GenericMapper<MovieEntity, Movie>{
	
	@Autowired
	private FavMovieRepository favMovieRepo;
	
	@Override
	public Movie entityToDto(MovieEntity entity) {
		Movie movie = new Movie();
		movie.setId(entity.getId());
		movie.setTitle(entity.getTitle());
		movie.setFavMovieId(entity.getFavMovie().getId());
		return movie;
	}

	@Override
	public MovieEntity dtoToEntity(Movie dto) {
		MovieEntity movieEntity = new MovieEntity();
		movieEntity.setId(dto.getId());
		movieEntity.setTitle(dto.getTitle());
		movieEntity.setFavMovie(favMovieRepo.findOne(dto.getFavMovieId()));
		return movieEntity;
	}

}
