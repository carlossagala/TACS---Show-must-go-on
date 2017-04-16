package ar.com.tacs.grupo5.frba.utn.models;

import java.util.Set;

public class Movie {
	private String id;
	private String title;
	private Set<FavMovie> favMovies;
	
	public Movie(){		
	}
		
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Movie(String id, String title) {
		super();
		this.id = id;
		this.title = title;
	}

	public Set<FavMovie> getFavMovies() {
		return favMovies;
	}

	public void setFavMovies(Set<FavMovie> favMovies) {
		this.favMovies = favMovies;
	}


}
