package ar.com.tacs.grupo5.frba.utn.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import ar.com.tacs.grupo5.frba.utn.entity.FavActorEntity;
import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;

public interface FavActorRepository extends PagingAndSortingRepository<FavActorEntity, String>{
	Page<FavActorEntity> findByUserEntity(UserEntity UserEntity,Pageable pageable);
	void deleteByUserEntityAndActorId(UserEntity userEntity, String actorId);
}
