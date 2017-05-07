package ar.com.tacs.grupo5.frba.utn.mapper;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.tacs.grupo5.frba.utn.dao.repository.UserRepository;
import ar.com.tacs.grupo5.frba.utn.entity.FavMoviesEntity;
import ar.com.tacs.grupo5.frba.utn.entity.MovieEntity;
import ar.com.tacs.grupo5.frba.utn.models.FavMovies;

@Component
public class FavMoviesMapper implements GenericMapper<FavMoviesEntity, FavMovies>{
	
	private MovieMapper movieMapper;
	private UserRepository userRepo;
	
	@Autowired
	public FavMoviesMapper(MovieMapper movieMapper, UserRepository userRepo) {
		this.movieMapper = movieMapper;
		this.userRepo = userRepo;
	}

	@Override
	@Transactional
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
		return favMovieEnt;
	}

}
