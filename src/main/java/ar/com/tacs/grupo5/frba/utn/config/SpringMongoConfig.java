package ar.com.tacs.grupo5.frba.utn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

import ar.com.tacs.grupo5.frba.utn.config.event.FavActorCascadeListener;
import ar.com.tacs.grupo5.frba.utn.config.event.FavMoviesCascadeListener;
import ar.com.tacs.grupo5.frba.utn.config.event.MovieCascadeListener;

@Configuration
public class SpringMongoConfig extends AbstractMongoConfiguration {

	@Override
	public String getDatabaseName() {
		return "show_must_go_on";
	}

	@Override
	@Bean
	public Mongo mongo() throws Exception {
		return new MongoClient("127.0.0.1");
	}
	
	@Bean
    public FavActorCascadeListener cascadingFavActor() {
        return new FavActorCascadeListener();
    }
	
	@Bean
    public FavMoviesCascadeListener cascadingFavMovies() {
        return new FavMoviesCascadeListener();
    }
	
	@Bean
    public MovieCascadeListener cascadingMovies() {
        return new MovieCascadeListener();
    }

}
