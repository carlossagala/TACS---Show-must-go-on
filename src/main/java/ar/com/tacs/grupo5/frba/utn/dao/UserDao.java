package ar.com.tacs.grupo5.frba.utn.dao;

import java.util.List;

import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;

public interface UserDao {
	List<UserEntity> getAllUsers();

	UserEntity getUserById(String id);

	UserEntity saveUser(UserEntity user);
	
	UserEntity findByUserNameAndPass(String userName,String pass);
	
	UserEntity findByUserName(String userName);
	
//	Page<FavActorEntity> getFavActors(String userId, int page);
//	void addFavActor(String idUser,String idActor);
//	void deleteFavActor(String idUser,String idActor);
//	boolean deleteFavMovies(String idUser, String idFavMovies);
	
}
