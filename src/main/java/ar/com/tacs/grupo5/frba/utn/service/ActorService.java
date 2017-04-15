package ar.com.tacs.grupo5.frba.utn.service;

import java.util.List;

import ar.com.tacs.grupo5.frba.utn.models.Actor;
import ar.com.tacs.grupo5.frba.utn.models.Image;
import ar.com.tacs.grupo5.frba.utn.models.Movie;
import ar.com.tacs.grupo5.frba.utn.models.Search;



public interface ActorService {
	
		public List<Image> getActorImages(String id);
		public List<Movie> getActorMovies(String id);
		public Actor getDetailActor(String id);
		
}
