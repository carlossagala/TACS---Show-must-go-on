package ar.com.tacs.grupo5.frba.utn.entity;
import javax.annotation.Generated;import org.springframework.data.annotation.Id;import org.springframework.data.mongodb.core.mapping.DBRef;import org.springframework.data.mongodb.core.mapping.Document;
//@Table(name="MOVIE", uniqueConstraints= @UniqueConstraint(columnNames={"idmovie", "favmovie_id"}))//@Entity@Document(collection="MOVIES")public class MovieEntity {	@Id	//TODO: Ver el tema de los ids autogenerados	@Generated(value = { "system-uuid" })	private String id;	@DBRef
	private FavMoviesEntity favMovie;
	private String idMovie;
	public MovieEntity() {		super();	}
	public MovieEntity(FavMoviesEntity favMovie)	{		this.favMovie = favMovie;	}
	public String getId() {		return id;	}
	public void setId(String id) {		this.id = id;	}
	public FavMoviesEntity getFavMovie() {		return favMovie;	}
	public void setFavMovie(FavMoviesEntity favMovie) {		this.favMovie = favMovie;	}
	public String getIdMovie() {		return idMovie;	}
	public void setIdMovie(String idMovie) {		this.idMovie = idMovie;	}
	@Override	public int hashCode() {		final int prime = 31;		int result = 1;		result = prime * result + ((idMovie == null) ? 0 : idMovie.hashCode());
		return result;	}
	@Override	public boolean equals(Object obj) {		if (this == obj)			return true;		if (obj == null)			return false;
		if (getClass() != obj.getClass())			return false;
		MovieEntity other = (MovieEntity) obj;
		if (idMovie == null) {			if (other.idMovie != null)				return false;		} else if (!idMovie.equals(other.idMovie))			return false;		return true;	}}
