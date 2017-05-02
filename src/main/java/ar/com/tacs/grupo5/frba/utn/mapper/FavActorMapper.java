package ar.com.tacs.grupo5.frba.utn.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.tacs.grupo5.frba.utn.dao.repository.UserRepository;
import ar.com.tacs.grupo5.frba.utn.entity.FavActorEntity;
import ar.com.tacs.grupo5.frba.utn.models.FavActor;

@Component
public class FavActorMapper implements GenericMapper<FavActorEntity, FavActor>{
	
	private UserRepository userRepo;

	@Autowired
	public FavActorMapper(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public FavActor entityToDto(FavActorEntity entity) {
		if (entity == null)
			return null;
		FavActor favActor = new FavActor();
		favActor.setActorId(entity.getActorId());
		favActor.setId(entity.getId());
		favActor.setUserId(entity.getUserEntity().getId());
		return favActor;
	}

	@Override
	public FavActorEntity dtoToEntity(FavActor dto) {
		FavActorEntity entity = new FavActorEntity();
		entity.setActorId(dto.getActorId());
		entity.setId(dto.getId());
		entity.setUserEntity(userRepo.findOne(dto.getUserId()));
		return entity;
	}

	
	

}
