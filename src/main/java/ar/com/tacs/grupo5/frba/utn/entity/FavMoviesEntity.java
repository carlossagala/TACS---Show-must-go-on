package ar.com.tacs.grupo5.frba.utn.entity;

import java.util.Set;

import javax.annotation.Generated;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="FAV_MOVIES")
public class FavMoviesEntity {
	@Id
	@Generated(value = { "system-uuid" })
	private String id;
	private String name;
	
	@DBRef
	private UserEntity user;
	
	@DBRef
	private Set<MovieEntity> movies;

	public FavMoviesEntity() {
		super();
	}

	public FavMoviesEntity(String name, UserEntity user) {
		this.name = name;
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<MovieEntity> getMovies() {
		return movies;
	}

	public void setMovies(Set<MovieEntity> movies) {
		this.movies = movies;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
}
