package ar.com.tacs.grupo5.frba.utn.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, String>{
	UserEntity findByUserNameAndPass(String userName,String pass);
	UserEntity findByUserName(String userName);
}
