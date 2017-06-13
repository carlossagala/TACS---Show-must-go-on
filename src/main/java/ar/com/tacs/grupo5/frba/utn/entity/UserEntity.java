package ar.com.tacs.grupo5.frba.utn.entity;

import javax.annotation.Generated;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="USERS")
public class UserEntity {
	@Id
	@Generated(value = { "system-uuid" })
	private String id;
	
	private String userName;
	private String pass;
	private String nivel;
	private String lastAccess;

	public UserEntity() {
		super();
	}

	public UserEntity(String id, String userName, String pass, String nivel) {
		super();
		this.id = id;
		this.userName = userName;
		this.setPass(pass);
		this.setNivel(nivel);
	}

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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
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
}
