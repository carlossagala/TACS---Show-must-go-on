package ar.com.tacs.grupo5.frba.utn.models;

import java.util.HashSet;
import java.util.Set;

public class User {

	private String id;
	private String userName;
	private Set<FavMovies> favMovies;
	private String pass;
	private String nivel;

	public User() {
	}

	public User(String id, String userName) {
		super();
		this.id = id;
		this.userName = userName;
	}

	public User(String userName, String pass, String nivel) {
		super();
		this.userName = userName;
		this.pass = pass;
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

	public Set<FavMovies> getFavMovies() {
		return favMovies;
	}

	public void setFavMovies(Set<FavMovies> favMovies) {
		this.favMovies = favMovies;
	}

}
