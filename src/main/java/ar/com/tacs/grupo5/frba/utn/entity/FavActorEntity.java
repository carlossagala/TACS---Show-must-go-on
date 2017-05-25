package ar.com.tacs.grupo5.frba.utn.entity;



import javax.annotation.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="FAV_ACTORS")
public class FavActorEntity {
	@Id
	@Generated(value = { "system-uuid" })
	private String id;
	
	
	@DBRef
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
