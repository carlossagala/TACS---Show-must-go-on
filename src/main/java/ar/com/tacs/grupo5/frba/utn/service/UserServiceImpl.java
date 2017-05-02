package ar.com.tacs.grupo5.frba.utn.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ar.com.tacs.grupo5.frba.utn.dao.UserDao;
import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;
import ar.com.tacs.grupo5.frba.utn.mapper.UserMapper;
import ar.com.tacs.grupo5.frba.utn.models.User;
@Component
@Transactional
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	private UserMapper userMapper; 
	
	@Autowired
	public UserServiceImpl(UserDao userDao, UserMapper userMapper) {
		super();
		this.userDao = userDao;
		this.userMapper = userMapper;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		List<UserEntity> allUsers = userDao.getAllUsers();
		for (UserEntity userEntity : allUsers) {
			users.add(userMapper.entityToDto(userEntity));
		}
		return users;
	}

	@Override
	public User getUserById(String id) {
		UserEntity userEntity = userDao.getUserById(id);
		return userMapper.entityToDto(userEntity);
	}

	@Override
	public User saveUser(User user) {
		UserEntity userEntity = userMapper.dtoToEntity(user);
		UserEntity savedUser = userDao.saveUser(userEntity);
		return userMapper.entityToDto(savedUser);
	}

	@Override

	public User findByUserNameAndPass(String userName, String pass) {
		UserEntity userEntity = userDao.findByUserNameAndPass(userName, pass);
		return userMapper.entityToDto(userEntity);
	}

	@Override
	public User findByUserName(String userName) {
		UserEntity userEntity = userDao.findByUserName(userName);
		return userMapper.entityToDto(userEntity);
	}

}
