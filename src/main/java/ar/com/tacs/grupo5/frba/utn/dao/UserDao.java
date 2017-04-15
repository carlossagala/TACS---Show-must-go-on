package ar.com.tacs.grupo5.frba.utn.dao;

import java.util.List;

import ar.com.tacs.grupo5.frba.utn.models.User;

public interface UserDao {
	List<User> getAllUsers();

	User getUserById(String id);

	User saveUser(User user);
	
	User findByUserNameAndPass(String userName,String pass);
	
	User findByUserName(String userName);

}
