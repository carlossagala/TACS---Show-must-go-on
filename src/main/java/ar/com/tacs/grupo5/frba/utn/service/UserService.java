package ar.com.tacs.grupo5.frba.utn.service;

import java.util.List;

import org.springframework.data.domain.Page;

import ar.com.tacs.grupo5.frba.utn.models.User;

public interface UserService {

	List<User> getAllUsers();
	
	Page<User> getAllUsersWithPage(int page);

	User getUserById(String id);

	User saveUser(User user);

	User findByUserNameAndPass(String userName, String pass);

	User findByUserName(String userName);

}
