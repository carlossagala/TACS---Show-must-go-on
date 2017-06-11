package ar.com.tacs.grupo5.frba.utn.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ar.com.tacs.grupo5.frba.utn.dao.UserDao;
import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;
import ar.com.tacs.grupo5.frba.utn.mapper.UserMapper;
import ar.com.tacs.grupo5.frba.utn.models.FavMovies;
import ar.com.tacs.grupo5.frba.utn.models.User;
import spark.utils.CollectionUtils;
@Component
@Transactional
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	private UserMapper userMapper; 
	private FavMoviesService favMoviesService;
	@Autowired
	public UserServiceImpl(UserDao userDao, UserMapper userMapper,FavMoviesService favMoviesService ) {
		super();
		this.userDao = userDao;
		this.userMapper = userMapper;
		this.favMoviesService = favMoviesService;
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
	
	public Page<User> getAllUsersWithPage(int page)
	{
		Page<UserEntity> userEntities = userDao.getAllUsersWithPage(page-1);
		List<User> users = new ArrayList<>();
		for (UserEntity userEntity : userEntities.getContent()) {
		
			if(userEntity.getNivel().equals("user")){
				User user = userMapper.entityToDto(userEntity);
				Set<FavMovies> listOfFavMovies = favMoviesService.getFavMoviesByUser(user);
				if(!CollectionUtils.isEmpty(listOfFavMovies)){
					user.setFavMovies(listOfFavMovies);
				}
				users.add(user);
			}
		}
		Page<User> usersPage = new PageImpl<>(users);
		return usersPage;
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
