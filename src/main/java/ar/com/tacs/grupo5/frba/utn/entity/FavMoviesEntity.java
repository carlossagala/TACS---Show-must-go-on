package ar.com.tacs.grupo5.frba.utn.entity;

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
