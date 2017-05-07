package ar.com.tacs.grupo5.frba.utn.models;

import java.util.HashSet;
import java.util.Set;

public class User {

	private String id;
	private String userName;
	private Set<FavMovies> favMovies;
	private String password;
	private String nivel;
	private String lastAccess;

	public User() {
	}

	public User(String id, String userName) {
		super();
		this.id = id;
		this.userName = userName;
	}

	public User(String userName, String password, String nivel,String lastAccess) {
		super();
		this.userName = userName;
		this.password = password;
		this.nivel = nivel;
		this.favMovies = new HashSet<>();
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public Set<FavMovies> getFavMovies() {
		return favMovies;
	}

	public void setFavMovies(Set<FavMovies> favMovies) {
		this.favMovies = favMovies;
	}

	public String getLastAccess() {
		return lastAccess;
	}

	public void setLastAccess(String lastAccess) {
		this.lastAccess = lastAccess;
	}

}
