package ar.com.tacs.grupo5.frba.utn.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.com.tacs.grupo5.frba.utn.dao.UserDao;
import ar.com.tacs.grupo5.frba.utn.dao.repository.UserRepository;
import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{
	
	@Value("${page.size}")
	private int pageSize;
	
	private UserRepository userRepository;
	
	@Autowired
	public UserDaoImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public List<UserEntity> getAllUsers() {
		List<UserEntity> usersFound = (List<UserEntity>) userRepository.findAll();
		return usersFound;
	}
	
	@Override
	public Page<UserEntity> getAllUsersWithPage(int page) {
		Page<UserEntity> usersFound = userRepository.findAll(new PageRequest(page, pageSize));
		return usersFound;
	}

	@Override
	public UserEntity getUserById(String id) {
		return userRepository.findOne(id);
	}
	
	
	@Override
	@Transactional
	public UserEntity saveUser(UserEntity user) {
		return userRepository.save(user);
	}

	@Override
	public UserEntity findByUserNameAndPass(String userName, String pass) {
		return userRepository.findByUserNameAndPass(userName,pass);
	}

	@Override
	public UserEntity findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
