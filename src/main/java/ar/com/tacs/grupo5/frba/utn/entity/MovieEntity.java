package ar.com.tacs.grupo5.frba.utn.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class MovieEntity {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "favmovie_id", referencedColumnName = "id")
	private FavMovieEntity favMovie;
	private String idMovie;


	public MovieEntity() {
		super();
	}
	
	public MovieEntity(FavMovieEntity favMovie)
	{
		this.favMovie = favMovie;
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

	public String getIdMovie() {
		return idMovie;
	}

	public void setIdMovie(String idMovie) {
		this.idMovie = idMovie;
	}

}
