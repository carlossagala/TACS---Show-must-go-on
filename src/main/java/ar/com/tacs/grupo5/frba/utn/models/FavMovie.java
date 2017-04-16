package ar.com.tacs.grupo5.frba.utn.models;

import java.util.Set;

public class FavMovie {
	private String id;
	private String name;
	private String userId;
	private Set<Movie> movies;
	
	public FavMovie()
	{
		
	}
	
	public FavMovie(String name, String userId) {
		super();
		this.name = name;
		this.userId = userId;
	}
	
	public FavMovie(String id, String name, String userId, Set<Movie> movies) {
		super();
		this.id = id;
		this.name = name;
		this.userId = userId;
		this.movies = movies;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Set<Movie> getMovies() {
		return movies;
	}
	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}
	
}
