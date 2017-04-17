package ar.com.tacs.grupo5.frba.utn.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.tacs.grupo5.frba.utn.dao.repository.UserRepository;
import ar.com.tacs.grupo5.frba.utn.entity.FavMovieEntity;
import ar.com.tacs.grupo5.frba.utn.entity.MovieEntity;
import ar.com.tacs.grupo5.frba.utn.models.FavMovie;
import ar.com.tacs.grupo5.frba.utn.models.Movie;

@Component
public class FavMovieMapper implements GenericMapper<FavMovieEntity, FavMovie>{
	
	@Autowired
	private MovieMapper movieMapper;
	@Autowired
	private UserRepository userRepo;

	@Override
	public FavMovie entityToDto(FavMovieEntity entity) {
		if (entity == null)
			return null;
		
		FavMovie favMovie = new FavMovie();
		favMovie.setId(entity.getId());
		favMovie.setName(entity.getName());
		favMovie.setUserId(entity.getUser().getId());
		favMovie.setMovies(new java.util.HashSet<>());
		Set<MovieEntity> moviesEnt = entity.getMovies();
		if(moviesEnt != null && !moviesEnt.isEmpty())
		{
			for (MovieEntity movieEntity : moviesEnt) {
				favMovie.getMovies().add(movieMapper.entityToDto(movieEntity));
			}
		}
		return favMovie;
	}

	@Override
	public FavMovieEntity dtoToEntity(FavMovie dto) {
		FavMovieEntity favMovieEnt = new FavMovieEntity();
		favMovieEnt.setId(dto.getId());
		favMovieEnt.setName(dto.getName());
		favMovieEnt.setUser(userRepo.findOne(dto.getUserId()));
		favMovieEnt.setMovies(new HashSet<>());
		Set<Movie> movies = dto.getMovies();
		if(movies != null && !movies.isEmpty())
		{
			for (Movie movie : movies) {
				favMovieEnt.getMovies().add(movieMapper.dtoToEntity(movie));
			}
		}
		return favMovieEnt;
	}

}
