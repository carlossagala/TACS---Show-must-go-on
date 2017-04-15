package ar.com.tacs.grupo5.frba.utn.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FavActor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	private String userId;
	private String actorId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getActorId() {
		return actorId;
	}

	public void setActorId(String actorId) {
		this.actorId = actorId;
	}

	public FavActor(String id, String userId, String actorId) {
		super();
		this.id = id;
		this.userId = userId;
		this.actorId = actorId;
	}

	public FavActor() {
		super();
	}

}
