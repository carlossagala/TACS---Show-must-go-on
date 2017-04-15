package ar.com.tacs.grupo5.frba.utn.models;

public class Movie {
	private String id;
	private String title;
	private String favMovieId;
	
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

	public String getFavMovieId() {
		return favMovieId;
	}

	public void setFavMovieId(String favMovieId) {
		this.favMovieId = favMovieId;
	}

}
