package ar.com.tacs.grupo5.frba.utn.dao;

import java.util.List;

import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.data.domain.Page;

import ar.com.tacs.grupo5.frba.utn.entity.FavActorEntity;
import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;
import ar.com.tacs.grupo5.frba.utn.exceptions.ResourceNotFound;

public interface FavActorDao {

	void addFavActor(UserEntity user, String idActor) throws ResourceNotFoundException;

	void deleteFavActor(UserEntity user, String idActor) throws ResourceNotFound;

	Page<FavActorEntity> getFavActors(UserEntity user, int page);

	Long countByUser(UserEntity dtoToEntity);
	
	List<FavActorEntity> findByUserEntity(UserEntity UserEntity);


}
