package ar.com.tacs.grupo5.frba.utn.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import ar.com.tacs.grupo5.frba.utn.entity.FavActorEntity;
import ar.com.tacs.grupo5.frba.utn.models.User;

public interface UserDao {
	List<User> getAllUsers();

	User getUserById(String id);

	User saveUser(User user);
	
	User findByUserNameAndPass(String userName,String pass);
	
	User findByUserName(String userName);
	
	Page<FavActorEntity> getFavActors(String userId, int page);
	
	void addFavActor(String idUser,String idActor);
	void deleteFavActor(String idUser,String idActor);
	
}
