package ar.com.tacs.grupo5.frba.utn.models;

public class Movie {
	private String id;
	private String favMovieId;
	
	public Movie(){		
	}
	
	public Movie(String id, String favMovieId)
	{
		this.id = id;
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


}
