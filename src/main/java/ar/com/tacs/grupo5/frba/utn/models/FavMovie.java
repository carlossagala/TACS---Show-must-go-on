package ar.com.tacs.grupo5.frba.utn.models;

import java.util.List;

public class FavMovie {
	private String id;
	private String name;
	private List<Movie> movies;
	
	public List<Movie> getMovies() {
		return movies;
	}
	public void setMovies(List<Movie> movies) {
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
	
}
