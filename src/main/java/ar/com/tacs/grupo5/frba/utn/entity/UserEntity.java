package ar.com.tacs.grupo5.frba.utn.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserEntity {

	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String nickname;

	public UserEntity() {
		super();
	}

	public UserEntity(String id, String firstName, String lastName, String nickname) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nickname = nickname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
