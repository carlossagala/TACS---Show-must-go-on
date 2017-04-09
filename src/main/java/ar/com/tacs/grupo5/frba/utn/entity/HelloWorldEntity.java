package ar.com.tacs.grupo5.frba.utn.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class HelloWorldEntity {

	// constructor vacio es necesario para JPA
	public HelloWorldEntity() {
		super();
	}

	public HelloWorldEntity(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Id
	private String id;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
