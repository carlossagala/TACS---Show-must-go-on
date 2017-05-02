package ar.com.tacs.grupo5.frba.utn.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;
@Table(name="MOVIE",
	    uniqueConstraints=
		@UniqueConstraint(columnNames={"idmovie", "favmovie_id"})
	)
@Entity
public class MovieEntity {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "favmovie_id", referencedColumnName = "id")
	private FavMoviesEntity favMovie;
	private String idMovie;


	public MovieEntity() {
		super();
	}
	
	public MovieEntity(FavMoviesEntity favMovie)
	{
		this.favMovie = favMovie;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public FavMoviesEntity getFavMovie() {
		return favMovie;
	}

	public void setFavMovie(FavMoviesEntity favMovie) {
		this.favMovie = favMovie;
	}

	public String getIdMovie() {
		return idMovie;
	}

	public void setIdMovie(String idMovie) {
		this.idMovie = idMovie;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idMovie == null) ? 0 : idMovie.hashCode());
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
		MovieEntity other = (MovieEntity) obj;
		if (idMovie == null) {
			if (other.idMovie != null)
				return false;
		} else if (!idMovie.equals(other.idMovie))
			return false;
		return true;
	}
	
	

}
