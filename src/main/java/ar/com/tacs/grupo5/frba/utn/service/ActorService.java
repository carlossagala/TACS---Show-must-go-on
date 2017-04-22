package ar.com.tacs.grupo5.frba.utn.service;

import java.util.List;


import ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.Actor;
import ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.Image;
import ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.Movie;




public interface ActorService {
	
		public List<Image> getActorImages(String id);
		public List<Movie> getActorMovies(String id);
		public Actor getDetailActor(String id);
		public List<Movie> getMoviesWithActors(List<String> id);
		
}
