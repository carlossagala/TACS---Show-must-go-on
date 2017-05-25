package ar.com.tacs.grupo5.frba.utn.config.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;

import ar.com.tacs.grupo5.frba.utn.entity.FavMoviesEntity;
import ar.com.tacs.grupo5.frba.utn.entity.MovieEntity;

public class MovieCascadeListener extends AbstractMongoEventListener<MovieEntity> {
	
	@Autowired
	private MongoOperations mongoOperations;
	
	@Override
	public void onAfterSave(AfterSaveEvent<MovieEntity> event) {
		FavMoviesEntity favMovie = event.getSource().getFavMovie();
		favMovie.getMovies().add(event.getSource());
		mongoOperations.save(favMovie);
		super.onAfterSave(event);
	}
	
	@Override
	public void onBeforeDelete(BeforeDeleteEvent<MovieEntity> event) {
		MovieEntity movie = (MovieEntity) event.getDBObject();
		FavMoviesEntity favMovie = movie.getFavMovie();
		favMovie.getMovies().remove(movie);
		mongoOperations.save(favMovie);
		super.onBeforeDelete(event);
	}

}
