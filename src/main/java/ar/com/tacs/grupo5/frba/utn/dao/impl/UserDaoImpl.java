package ar.com.tacs.grupo5.frba.utn.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.com.tacs.grupo5.frba.utn.dao.FavMoviesDao;
import ar.com.tacs.grupo5.frba.utn.dao.UserDao;
import ar.com.tacs.grupo5.frba.utn.dao.repository.UserRepository;
import ar.com.tacs.grupo5.frba.utn.entity.FavActor;
import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;
import ar.com.tacs.grupo5.frba.utn.models.User;

@Repository
public class UserDaoImpl implements UserDao{
	
	private UserRepository userRepository;
	private FavMoviesDao favMoviesDao;
	
	@Autowired
	public UserDaoImpl(UserRepository userRepository,FavMoviesDao favMoviesDao) {
		super();
		this.userRepository = userRepository;
		this.favMoviesDao = favMoviesDao;
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
		if(userEntity==null){
			return null;
		}
		User user = new User();
		user.setId(userEntity.getId());
		user.setUserName(userEntity.getUserName());
//		user.setFavMovies(favMoviesDao.mapFavMovies(userEntity.getFavMovies()));
		return user;
	}

	@Override
	@Transactional
	public User saveUser(User user) {
		UserEntity userEntity = new UserEntity();
		
		userEntity.setUserName(user.getUserName());
		userEntity.setNivel(user.getNivel());
		userEntity.setPass(user.getPass());

		return mapUser(userRepository.save(userEntity));
	}

	@Override
	public User findByUserNameAndPass(String userName, String pass) {
		return mapUser(userRepository.findByUserNameAndPass(userName,pass));
	}

	@Override
	public User findByUserName(String userName) {
		return mapUser(userRepository.findByUserName(userName));
	}

	@Override
	@Transactional
	public List<String> getFavActors(String userId) {
		if(userId==null){
			return new ArrayList<>();
		}
		UserEntity userEntity = userRepository.findOne(userId);
		if(userEntity==null){
			return new ArrayList<>();
		}
		List<FavActor> favActors= userEntity.getFavActors();
		if(favActors==null){
			return new ArrayList<>();
		}
		return favActors.stream().map(x->x.getActorId()).collect(Collectors.toList());
	}

}
