package ar.com.tacs.grupo5.frba.utn.models;

public class Movie {
	private String id;
	private String favMovieId;
	private String movieId;
	
	public Movie(){		
	}
	
	public Movie(String movieId, String favMovieId)
	{
		this.movieId = movieId;
		this.favMovieId = favMovieId;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFavMovieId() {
		return favMovieId;
	}

	public void setFavMovieId(String favMovieId) {
		this.favMovieId = favMovieId;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}


}
