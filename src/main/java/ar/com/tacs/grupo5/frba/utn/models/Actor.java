package ar.com.tacs.grupo5.frba.utn.models;

import java.util.List;

public class Actor {
	
	public Actor(String id, String name,String image, String biography, List<String> movies) {
		super();
		this.id = id;
		this.name = name;
		//this.image = image;
		this.biography = biography;
		//this.movies = movies;
	}
	private String id;
	private String name;
	private List<Image> image;
	private String biography;
	private List<Movie> movies;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getBiography() {
		return biography;
	}
	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Image> getImage() {
		return image;
	}
	public void setImage(List<Image> image) {
		this.image = image;
	}
	public List<Movie> getMovies() {
		return movies;
	}
	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
	

}
