package ar.com.tacs.grupo5.frba.utn.models;

import java.util.List;

public class Movie {
	private String id;
	private String title;
	private Images images;
	private Reviews reviews;
	private List<Actor> actors;
	private String overview;
	
	
	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public Images getImages() {
		return images;
	}

	public void setImages(Images images) {
		this.images = images;
	}

	public Reviews getReviews() {
		return reviews;
	}

	public void setReviews(Reviews reviews) {
		this.reviews = reviews;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Movie(String id, String title, String image, String review, String overview, List<String> actors) {
		super();
		this.id = id;
		this.title = title;
		// this.image = image;
		// this.review = review;
		this.overview = overview;
		//this.actors = actors;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

}
