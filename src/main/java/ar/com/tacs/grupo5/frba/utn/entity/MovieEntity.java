package ar.com.tacs.grupo5.frba.utn.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MovieEntity {
	@Id
	private String id;
	private String title;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "favmovie_id", referencedColumnName = "id")
	private FavMovieEntity favMovie;
	
	public MovieEntity() {
		super();
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public FavMovieEntity getFavMovie() {
		return favMovie;
	}

	public void setFavMovie(FavMovieEntity favMovie) {
		this.favMovie = favMovie;
	}
}
