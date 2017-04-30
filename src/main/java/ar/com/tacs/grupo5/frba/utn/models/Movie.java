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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((movieId == null) ? 0 : movieId.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (movieId == null) {
			if (other.movieId != null)
				return false;
		} else if (!movieId.equals(other.movieId))
			return false;
		return true;
	}
}
