package ar.com.tacs.grupo5.frba.utn.models;

import java.util.List;

public class Actor {
	
	public Actor(String id, String name,String image, String biography, List<String> movies) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.biography = biography;
		this.movies = movies;
	}
	private String id;
	private String name;
	private String image;
	private String biography;
	private List<String> movies;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getBiography() {
		return biography;
	}
	public void setBiography(String biography) {
		this.biography = biography;
	}
	public List<String> getMovies() {
		return movies;
	}
	public void setMovies(List<String> movies) {
		this.movies = movies;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
