package ar.com.tacs.grupo5.frba.utn.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.tacs.grupo5.frba.utn.dao.repository.UserRepository;
import ar.com.tacs.grupo5.frba.utn.entity.FavMoviesEntity;
import ar.com.tacs.grupo5.frba.utn.models.FavMovies;

@Component
public class FavMoviesMapper implements GenericMapper<FavMoviesEntity, FavMovies>{
	
	private UserRepository userRepo;
	
	@Autowired
	public FavMoviesMapper(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	@org.springframework.transaction.annotation.Transactional
	public FavMovies entityToDto(FavMoviesEntity entity) {
		if (entity == null)
			return null;
		
		FavMovies favMovie = new FavMovies();
		favMovie.setId(entity.getId());
		favMovie.setName(entity.getName());
		favMovie.setUserId(entity.getUser().getId());
		return favMovie;
	}

	@Override
	public FavMoviesEntity dtoToEntity(FavMovies dto) {
		FavMoviesEntity favMovieEnt = new FavMoviesEntity();
		favMovieEnt.setId(dto.getId());
		favMovieEnt.setName(dto.getName());
		favMovieEnt.setUser(userRepo.findOne(dto.getUserId()));
		return favMovieEnt;
	}

}
