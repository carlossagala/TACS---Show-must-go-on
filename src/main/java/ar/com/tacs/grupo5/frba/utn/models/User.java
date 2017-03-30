package ar.com.tacs.grupo5.frba.utn.models;

public class User {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public User(String name){
		this.name = name; 
	}
}
