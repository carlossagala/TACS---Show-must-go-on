package ar.com.tacs.grupo5.frba.utn.models;

public class SearchResultActor implements SearchResult{
	
	private String id;
	private String type = "actor";
	private String profile_path;
	private boolean adult;
	private String name;
	private String popularity;
	public String getProfile_path() {
		return profile_path;
	}
	public void setProfile_path(String profile_path) {
		this.profile_path = profile_path;
	}
	public boolean isAdult() {
		return adult;
	}
	public void setAdult(boolean adult) {
		this.adult = adult;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPopularity() {
		return popularity;
	}
	public void setPopularity(String popularity) {
		this.popularity = popularity;
	}
	

	public void setType(String type){
		this.type = type;
	}
	

	public String getType(){
		return this.type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
