package ar.com.tacs.grupo5.frba.utn.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.tacs.grupo5.frba.utn.dao.repository.FavMovieRepository;
import ar.com.tacs.grupo5.frba.utn.entity.FavMovieEntity;
import ar.com.tacs.grupo5.frba.utn.entity.MovieEntity;
import ar.com.tacs.grupo5.frba.utn.models.FavMovie;
import ar.com.tacs.grupo5.frba.utn.models.Movie;

@Component
public class MovieMapper implements GenericMapper<MovieEntity, Movie>{
	
	@Autowired
	private FavMovieRepository favMovieRepo;
	
	@Autowired
	private FavMovieMapper favMovieMapper;
	
	@Override
	public Movie entityToDto(MovieEntity entity) {
		Movie movie = new Movie();
		movie.setId(entity.getId());
		movie.setTitle(entity.getTitle());
		Set<FavMovie> favMovies = new HashSet<>();
		for (FavMovieEntity FavMovieEnt : entity.getFavMovies()) {
			favMovies.add(favMovieMapper.entityToDto(FavMovieEnt));
		}
		movie.setFavMovies(favMovies);
		return movie;
	}

	@Override
	public MovieEntity dtoToEntity(Movie dto) {
		MovieEntity movieEntity = new MovieEntity();
		movieEntity.setId(dto.getId());
		movieEntity.setTitle(dto.getTitle());
		Set<FavMovieEntity> favMovies = new HashSet<>();
		for (FavMovie favMovie : dto.getFavMovies()) {
			favMovies.add(favMovieMapper.dtoToEntity(favMovie));
		}
		movieEntity.setFavMovies(favMovies);
		return movieEntity;
	}

}
