package ar.com.tacs.grupo5.frba.utn.models;

import java.util.List;

public class Movie {
	private String id;
	private String title;
	private String image;
	private String review;
	private String description;
	private List<String> actors;
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
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<String> getActors() {
		return actors;
	}
	public void setActors(List<String> actors) {
		this.actors = actors;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Movie(String id, String title, String image, String review, String description, List<String> actors) {
		super();
		this.id = id;
		this.title = title;
		this.image = image;
		this.review = review;
		this.description = description;
		this.actors = actors;
	}

}
