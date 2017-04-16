package ar.com.tacs.grupo5.frba.utn.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.tacs.grupo5.frba.utn.entity.FavMovieEntity;
import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;
import ar.com.tacs.grupo5.frba.utn.models.FavMovie;
import ar.com.tacs.grupo5.frba.utn.models.User;

@Component
public class UserMapper implements GenericMapper<UserEntity, User> {

	@Autowired
	private FavMovieMapper favMovieMapper;

	@Override
	public User entityToDto(UserEntity entity) {
		if(entity==null){
			return null;
		}
		User user = new User();
		user.setId(entity.getId());
		user.setNivel(entity.getNivel());
		user.setPass(entity.getPass());
		user.setUserName(entity.getUserName());
		user.setFavMovies(new HashSet<>());
		Set<FavMovieEntity> favMoviesEnt = entity.getFavMovies();
		if(favMoviesEnt != null && favMoviesEnt.size()>0)
		{
			for (FavMovieEntity favMovieEntity : favMoviesEnt) {
				user.getFavMovies().add(favMovieMapper.entityToDto(favMovieEntity));
			}
		}
		return user;
	}

	@Override
	public UserEntity dtoToEntity(User dto) {
		UserEntity userEnt = new UserEntity();
		userEnt.setId(dto.getId());
		userEnt.setNivel(dto.getNivel());
		userEnt.setPass(dto.getPass());
		userEnt.setUserName(dto.getUserName());
		userEnt.setFavMovies(new HashSet<>());
		Set<FavMovie> favMovies = dto.getFavMovies();
		if(favMovies != null && favMovies.size()>0)
		{
			for (FavMovie favMovie : favMovies) {
				userEnt.getFavMovies().add(favMovieMapper.dtoToEntity(favMovie));
			}
		}
		return userEnt;
	}

}
