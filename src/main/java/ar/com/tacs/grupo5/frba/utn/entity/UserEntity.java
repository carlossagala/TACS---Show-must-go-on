package ar.com.tacs.grupo5.frba.utn.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="USER")
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
	private String lastAccess;

	@OneToMany(cascade = {CascadeType.ALL}, fetch=FetchType.LAZY,mappedBy="user")
	private Set<FavMoviesEntity> favMovies;
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch=FetchType.LAZY,mappedBy="userEntity")
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
