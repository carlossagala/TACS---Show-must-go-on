package ar.com.tacs.grupo5.frba.utn.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.tacs.grupo5.frba.utn.dao.repository.UserRepository;
import ar.com.tacs.grupo5.frba.utn.entity.FavMoviesEntity;
import ar.com.tacs.grupo5.frba.utn.entity.MovieEntity;
import ar.com.tacs.grupo5.frba.utn.models.FavMovies;
import ar.com.tacs.grupo5.frba.utn.models.Movie;

@Component
public class FavMoviesMapper implements GenericMapper<FavMoviesEntity, FavMovies>{
	
	@Autowired
	private MovieMapper movieMapper;
	@Autowired
	private UserRepository userRepo;

	@Override
	public FavMovies entityToDto(FavMoviesEntity entity) {
		if (entity == null)
			return null;
		
		FavMovies favMovie = new FavMovies();
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
	public FavMoviesEntity dtoToEntity(FavMovies dto) {
		FavMoviesEntity favMovieEnt = new FavMoviesEntity();
		favMovieEnt.setId(dto.getId());
		favMovieEnt.setName(dto.getName());
		favMovieEnt.setUser(userRepo.findOne(dto.getUserId()));
		favMovieEnt.setMovies(new HashSet<>());
//		Set<Movie> movies = dto.getMovies();
//		if(movies != null && !movies.isEmpty())
//		{
//			for (Movie movie : movies) {
//				favMovieEnt.getMovies().add(movieMapper.dtoToEntity(movie));
//			}
//		}
		return favMovieEnt;
	}

}
