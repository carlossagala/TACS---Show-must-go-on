package ar.com.tacs.grupo5.frba.utn.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.tacs.grupo5.frba.utn.entity.FavMoviesEntity;
import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;
import ar.com.tacs.grupo5.frba.utn.models.FavMovies;
import ar.com.tacs.grupo5.frba.utn.models.User;

@Component
public class UserMapper implements GenericMapper<UserEntity, User> {

	@Autowired
	private FavMoviesMapper favMovieMapper;

	@Override
	public User entityToDto(UserEntity entity) {
		if(entity==null){
			return null;
		}
		User user = new User();
		user.setId(entity.getId());
		user.setNivel(entity.getNivel());
		user.setPassword(entity.getPass());
		user.setUserName(entity.getUserName());
		user.setFavMovies(new HashSet<>());
		user.setLastAccess(entity.getLastAccess());
		Set<FavMoviesEntity> favMoviesEnt = entity.getFavMovies();
		if(favMoviesEnt != null && favMoviesEnt.size()>0)
		{
			for (FavMoviesEntity favMovieEntity : favMoviesEnt) {
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
		userEnt.setPass(dto.getPassword());
		userEnt.setUserName(dto.getUserName());
		userEnt.setLastAccess(dto.getLastAccess());

		userEnt.setFavMovies(new HashSet<>());
		Set<FavMovies> favMovies = dto.getFavMovies();
		if(favMovies != null && favMovies.size()>0)
		{
			for (FavMovies favMovie : favMovies) {
				userEnt.getFavMovies().add(favMovieMapper.dtoToEntity(favMovie));
			}
		}
		return userEnt;
	}

}
