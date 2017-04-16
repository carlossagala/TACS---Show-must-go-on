package ar.com.tacs.grupo5.frba.utn.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
public class MovieEntity {
	@Id
	private String id;
	private String title;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "favmovie_id", referencedColumnName = "id")
	private Set<FavMovieEntity> favMovies;
	
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

	public Set<FavMovieEntity> getFavMovies() {
		return favMovies;
	}

	public void setFavMovies(Set<FavMovieEntity> favMovies) {
		this.favMovies = favMovies;
	}
}
