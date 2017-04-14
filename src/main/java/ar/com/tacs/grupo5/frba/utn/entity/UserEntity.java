package ar.com.tacs.grupo5.frba.utn.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class UserEntity {
	@Id
	private String id;
	private String userName;
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
	private List<FavMovieEntity> favMovies;

	public UserEntity() {
		super();
	}

	public UserEntity(String id, String userName) {
		super();
		this.id = id;
		this.userName = userName;
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
	
	public List<FavMovieEntity> getFavMovies() {
		return this.favMovies;
	}
	
	public void setFavMovies(List<FavMovieEntity> fav) {
		this.favMovies = fav;
	}
}
