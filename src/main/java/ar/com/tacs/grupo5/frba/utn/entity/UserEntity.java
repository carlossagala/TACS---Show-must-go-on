package ar.com.tacs.grupo5.frba.utn.entity;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Generated;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="USERS")
public class UserEntity {
	@Id
	//TODO: Ver el tema de los ids autogenerados
	@Generated(value = { "system-uuid" })
	private String id;
	
	//TODO: ver como hacer para que sea unico
	private String userName;
	private String pass;
	private String nivel;
	private String lastAccess;

	@DBRef
	private Set<FavMoviesEntity> favMovies;
	
	@DBRef
	private Set<FavActorEntity> favActors;

	public UserEntity() {
		super();
		favMovies = new HashSet<FavMoviesEntity>();
		favActors = new HashSet<FavActorEntity>();
	}

	public UserEntity(String id, String userName, String pass, String nivel) {
		super();
		this.id = id;
		this.userName = userName;
		this.setPass(pass);
		this.setNivel(nivel);
		favMovies = new HashSet<FavMoviesEntity>();
		favActors = new HashSet<FavActorEntity>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	
	public Set<FavMoviesEntity> getFavMovies() {
		return this.favMovies;
	}
	
	public void setFavMovies(Set<FavMoviesEntity> fav) {
		this.favMovies = fav;
	}

	public Set<FavActorEntity> getFavActors() {
		return favActors;
	}

	public void setFavActors(Set<FavActorEntity> favActors) {
		this.favActors = favActors;
	}

	public String getLastAccess() {
		return lastAccess;
	}

	public void setLastAccess(String lastAccess) {
		this.lastAccess = lastAccess;
	}
}
