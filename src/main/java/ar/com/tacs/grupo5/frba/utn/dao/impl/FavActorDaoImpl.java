package ar.com.tacs.grupo5.frba.utn.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.com.tacs.grupo5.frba.utn.dao.FavActorDao;
import ar.com.tacs.grupo5.frba.utn.dao.repository.FavActorRepository;
import ar.com.tacs.grupo5.frba.utn.entity.FavActorEntity;
import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;

@Repository
@Transactional
public class FavActorDaoImpl implements FavActorDao{
	
	private FavActorRepository favActorRepository;
	
	@Value("${page.size}")
	private int pageSize;
	
	@Autowired
	public FavActorDaoImpl(FavActorRepository favActorRepository) {
		this.favActorRepository = favActorRepository;
	}
	
	

	@Override
	@Transactional
	public void addFavActor(UserEntity user, String idActor) throws ResourceNotFoundException {
		FavActorEntity entity = new FavActorEntity();
		entity.setActorId(idActor);
		entity.setUserEntity(user);
		favActorRepository.save(entity);
	}

	@Override
	public void deleteFavActor(UserEntity user, String idActor) throws ResourceNotFoundException {
		favActorRepository.deleteByUserEntityAndActorId(user, idActor);
	}

	@Override
	public Page<FavActorEntity> getFavActors(UserEntity user, int page) {
		return favActorRepository.findByUserEntity(user, new PageRequest(page, pageSize));
	}

}
