package ar.com.tacs.grupo5.frba.utn.models;

public class LoginResponse {
	private String token;
	private String nivel;
	private String userId;

	public LoginResponse() {
		super();
	}


	public LoginResponse(String token, String nivel, String userId) {
		super();
		this.token = token;
		this.nivel = nivel;
		this.userId = userId;
	}


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
