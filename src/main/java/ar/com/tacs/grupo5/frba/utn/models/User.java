package ar.com.tacs.grupo5.frba.utn.models;

import java.util.List;

public class User {
	
	private String id;
	private String userName;
	private List<FavMovie> favMovies;
	
	public User()
	{
		
	}
	
	public User(String id, String userName) {
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

	public List<FavMovie> getFavMovies() {
		return favMovies;
	}

	public void setFavMovies(List<FavMovie> favMovies) {
		this.favMovies = favMovies;
	}
	
	


}
