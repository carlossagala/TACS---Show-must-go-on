package ar.com.tacs.grupo5.frba.utn.config.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;

import ar.com.tacs.grupo5.frba.utn.entity.FavMoviesEntity;
import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;

public class FavMoviesCascadeListener extends AbstractMongoEventListener<FavMoviesEntity>{

	@Autowired
	private MongoOperations mongoOperations;
	
	@Override
	public void onAfterSave(AfterSaveEvent<FavMoviesEntity> event) {
		UserEntity userEntity = event.getSource().getUser();
		userEntity.getFavMovies().add(event.getSource());
		mongoOperations.save(userEntity);
		super.onAfterSave(event);
	}
	
	@Override
	public void onBeforeDelete(BeforeDeleteEvent<FavMoviesEntity> event) {
		FavMoviesEntity favMovies = (FavMoviesEntity) event.getDBObject();
		UserEntity user = favMovies.getUser();
		user.getFavActors().remove(favMovies);
		mongoOperations.save(user);
		super.onBeforeDelete(event);
	}
	
}
