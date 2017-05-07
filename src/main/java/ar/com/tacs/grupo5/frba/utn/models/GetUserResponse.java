package ar.com.tacs.grupo5.frba.utn.models;

public class GetUserResponse {

	private String id;
	private String userName;
	private String lastAccess;
	private Long cantFavMovies;
	private Long cantFavActors;
	private String nivel;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getCantFavMovies() {
		return cantFavMovies;
	}

	public void setCantFavMovies(Long cantFavMovies) {
		this.cantFavMovies = cantFavMovies;
	}

	public Long getCantFavActors() {
		return cantFavActors;
	}

	public void setCantFavActors(Long cantFavActors) {
		this.cantFavActors = cantFavActors;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	
	public String getLastAccess() {
		return lastAccess;
	}

	public void setLastAccess(String lastAccess) {
		this.lastAccess = lastAccess;
	}

	public GetUserResponse(String id, String userName, Long cantFavMovies, Long cantFavActors, String nivel,String ultimoAcceso) {
		super();
		this.id = id;
		this.userName = userName;
		this.cantFavMovies = cantFavMovies;
		this.cantFavActors = cantFavActors;
		this.nivel = nivel;
		this.lastAccess = ultimoAcceso;
	}


}
