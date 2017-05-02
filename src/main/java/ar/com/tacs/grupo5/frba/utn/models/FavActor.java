package ar.com.tacs.grupo5.frba.utn.models;

public class FavActor {
	
	private String id;
	private String userId;
	private String actorId;
	
	public FavActor()
	{
		
	}
	
	public FavActor(String id, String userId, String actorId)
	{
		super();
		this.id = id;
		this.userId = userId;
		this.actorId = actorId;
	}
	
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
	
	
	
	
}
