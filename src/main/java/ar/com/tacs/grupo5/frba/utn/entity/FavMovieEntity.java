package ar.com.tacs.grupo5.frba.utn.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class FavMovieEntity {
	@Id
	@GeneratedValue
	private String id;
	private String name;
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
	private List<MovieEntity> movies;

	public FavMovieEntity() {
		super();
	}	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MovieEntity> getMovies() {
		return movies;
	}

	public void setMovies(List<MovieEntity> movies) {
		this.movies = movies;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
}
