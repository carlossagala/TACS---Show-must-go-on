package ar.com.tacs.grupo5.frba.utn.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.tacs.grupo5.frba.utn.dao.repository.FavMoviesRepository;
import ar.com.tacs.grupo5.frba.utn.entity.MovieEntity;
import ar.com.tacs.grupo5.frba.utn.models.Movie;

@Component
public class MovieMapper implements GenericMapper<MovieEntity, Movie>{
	
	@Autowired
	private FavMoviesRepository favMovieRepo;
	
	@Override
	public Movie entityToDto(MovieEntity entity) {
		if (entity == null)
			return null;
		
		Movie movie = new Movie();
		movie.setId(entity.getId());
		movie.setMovieId(entity.getIdMovie());
		movie.setFavMovieId(entity.getFavMovie().getId());
		return movie;
	}

	@Override
	public MovieEntity dtoToEntity(Movie dto) {
		MovieEntity movieEntity = new MovieEntity();
		movieEntity.setId(dto.getId());
		movieEntity.setIdMovie(dto.getMovieId());
		movieEntity.setFavMovie(favMovieRepo.findOne(dto.getFavMovieId()));
		return movieEntity;
	}

}
