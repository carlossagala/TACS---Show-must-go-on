package ar.com.tacs.grupo5.frba.utn.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class UserEntity {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",
	  strategy = "uuid")
	private String id;
	@Column(unique=true)
	private String userName;
	private String pass;
	private String nivel;
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch=FetchType.EAGER)
	private Set<FavMovieEntity> favMovies;
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch=FetchType.LAZY,mappedBy="userEntity")
	private List<FavActorEntity> favActors;

	public UserEntity() {
		super();
	}

	public UserEntity(String id, String userName, String pass, String nivel) {
		super();
		this.id = id;
		this.userName = userName;
		this.setPass(pass);
		this.setNivel(nivel);
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

	
	public Set<FavMovieEntity> getFavMovies() {
		return this.favMovies;
	}
	
	public void setFavMovies(Set<FavMovieEntity> fav) {
		this.favMovies = fav;
	}

	public List<FavActorEntity> getFavActors() {
		return favActors;
	}

	public void setFavActors(List<FavActorEntity> favActors) {
		this.favActors = favActors;
	}
}