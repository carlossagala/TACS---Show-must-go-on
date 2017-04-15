package ar.com.tacs.grupo5.frba.utn.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MovieEntity {
	@Id
	private String id;
	private String title;
	
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
}
