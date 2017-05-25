package ar.com.tacs.grupo5.frba.utn.config.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;

import ar.com.tacs.grupo5.frba.utn.entity.FavActorEntity;
import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;

public class FavActorCascadeListener extends AbstractMongoEventListener<FavActorEntity> {
	
	@Autowired
	private MongoOperations mongoOperations;
	
	@Override
	public void onAfterSave(AfterSaveEvent<FavActorEntity> event) {
		UserEntity userEntity = event.getSource().getUserEntity();
		userEntity.getFavActors().add(event.getSource());
		mongoOperations.save(userEntity);
		super.onAfterSave(event);
	}

	@Override
	public void onBeforeDelete(BeforeDeleteEvent<FavActorEntity> event) {
		FavActorEntity favActor = (FavActorEntity) event.getDBObject();
		UserEntity user = favActor.getUserEntity();
		user.getFavActors().remove(favActor);
		mongoOperations.save(user);
		super.onBeforeDelete(event);
	}
	

}
