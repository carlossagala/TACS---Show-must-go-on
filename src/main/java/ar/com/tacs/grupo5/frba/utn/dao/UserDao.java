package ar.com.tacs.grupo5.frba.utn.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;

public interface UserDao {
	
	List<UserEntity> getAllUsers();
	
	Page<UserEntity> getAllUsersWithPage(int page);

	UserEntity getUserById(String id);

	UserEntity saveUser(UserEntity user);
	
	UserEntity findByUserNameAndPass(String userName,String pass);
	
	UserEntity findByUserName(String userName);
	
}
