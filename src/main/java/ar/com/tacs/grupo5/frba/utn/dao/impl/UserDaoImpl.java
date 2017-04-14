package ar.com.tacs.grupo5.frba.utn.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.com.tacs.grupo5.frba.utn.dao.FavMoviesDao;
import ar.com.tacs.grupo5.frba.utn.dao.FavMoviesDaoImpl;
import ar.com.tacs.grupo5.frba.utn.dao.UserDao;
import ar.com.tacs.grupo5.frba.utn.dao.repository.UserRepository;
import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;
import ar.com.tacs.grupo5.frba.utn.models.User;

@Repository
public class UserDaoImpl implements UserDao{
	
	private UserRepository userRepository;

	@Autowired
	public UserDaoImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public List<User> getAllUsers() {
		return mapUserList(userRepository.findAll());
	}

	@Override
	public User getUserById(String id) {
		return mapUser(userRepository.findOne(id));
	}
	
	private List<User> mapUserList(Iterable<UserEntity> userEntites)
	{
		List<User> users = new ArrayList<>();
		for (UserEntity userEntity : userEntites) {
			users.add(mapUser(userEntity));
		}
		return users;
	}
	
	private User mapUser(UserEntity userEntity)
	{
		FavMoviesDao favMoviesDao = new FavMoviesDaoImpl();
		
		User user = new User();
		user.setId(userEntity.getId());
		user.setUserName(userEntity.getUserName());
		user.setFavMovies(favMoviesDao.mapFavMovies(userEntity.getFavMovies()));
		return user;
	}

	@Override
	public User saveUser(User user) {
		UserEntity userEntity = new UserEntity();
		userEntity.setId(user.getId());
		userEntity.setUserName(user.getUserName());
		return mapUser(userRepository.save(userEntity));
	}
}
