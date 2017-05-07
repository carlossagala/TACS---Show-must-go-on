package ar.com.tacs.grupo5.frba.utn.dao;

import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.data.domain.Page;

import ar.com.tacs.grupo5.frba.utn.entity.FavActorEntity;
import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;

public interface FavActorDao {

	void addFavActor(UserEntity user, String idActor) throws ResourceNotFoundException;

	void deleteFavActor(UserEntity user, String idActor) throws ResourceNotFoundException;

	Page<FavActorEntity> getFavActors(UserEntity user, int page);

	Long countByUser(UserEntity dtoToEntity);
	
	

}
