package ar.com.tacs.grupo5.frba.utn.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="FAV_ACTOR")
public class FavActorEntity {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private UserEntity userEntity;
	private String actorId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getActorId() {
		return actorId;
	}

	public void setActorId(String actorId) {
		this.actorId = actorId;
	}

	public FavActorEntity() {
		super();
	}

	public FavActorEntity(UserEntity userEntity, String actorId) {
		super();
		this.userEntity = userEntity;
		this.actorId = actorId;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

}
