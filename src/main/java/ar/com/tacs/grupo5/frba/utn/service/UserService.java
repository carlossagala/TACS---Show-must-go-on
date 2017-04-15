package ar.com.tacs.grupo5.frba.utn.service;

import java.util.List;

import ar.com.tacs.grupo5.frba.utn.models.FavMovie;
import ar.com.tacs.grupo5.frba.utn.models.User;

public interface UserService {
	
	List<User> getAllUsers();
	
	User getUserById(String id);
	
	User saveUser(User user);
	
	List<FavMovie> getUserFavMovies(String id);	
	User findByUserNameAndPass(String userName, String pass);
	
	User findByUserName(String userName);

}
