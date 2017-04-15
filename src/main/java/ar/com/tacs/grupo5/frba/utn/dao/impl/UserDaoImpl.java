package ar.com.tacs.grupo5.frba.utn.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.com.tacs.grupo5.frba.utn.dao.UserDao;
import ar.com.tacs.grupo5.frba.utn.dao.repository.UserRepository;
import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;
import ar.com.tacs.grupo5.frba.utn.mapper.UserMapper;
import ar.com.tacs.grupo5.frba.utn.models.User;

@Repository
public class UserDaoImpl implements UserDao{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	public UserDaoImpl() {
		super();
	}

	@Override
	public List<User> getAllUsers() {
		List<UserEntity> usersFound = userRepository.findAll();
		List<User> users = new ArrayList<>();
		for (UserEntity userEnt : usersFound) {
			users.add(userMapper.entityToDto(userEnt));
		}
		return users;
	}

	@Override
	public User getUserById(String id) {
		return userMapper.entityToDto(userRepository.findOne(id));
	}
	
	
	@Override
	@Transactional
	public User saveUser(User user) {
		UserEntity userEntity = userMapper.dtoToEntity(user);
		return userMapper.entityToDto(userRepository.saveAndFlush(userEntity));
	}

	@Override
	public User findByUserNameAndPass(String userName, String pass) {
		return userMapper.entityToDto(userRepository.findByUserNameAndPass(userName,pass));
	}

	@Override
	public User findByUserName(String userName) {
		return userMapper.entityToDto(userRepository.findByUserName(userName));
	}

}
