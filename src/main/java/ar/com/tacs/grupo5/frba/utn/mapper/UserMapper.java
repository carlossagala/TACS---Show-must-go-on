package ar.com.tacs.grupo5.frba.utn.mapper;

import java.util.HashSet;

import org.springframework.stereotype.Component;

import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;
import ar.com.tacs.grupo5.frba.utn.models.User;

@Component
public class UserMapper implements GenericMapper<UserEntity, User> {

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
		return userEnt;
	}

}
