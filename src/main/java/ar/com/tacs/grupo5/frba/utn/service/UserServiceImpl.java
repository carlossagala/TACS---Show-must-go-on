package ar.com.tacs.grupo5.frba.utn.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.tacs.grupo5.frba.utn.dao.UserDao;
import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;
import ar.com.tacs.grupo5.frba.utn.mapper.UserMapper;
import ar.com.tacs.grupo5.frba.utn.models.User;
@Component
public class UserServiceImpl implements UserService {
	
//	@Autowired
//	private FavMovieRepository favMovieRepository;
//	@Autowired
//	private FavMovieMapper favMovieMapper;

	private UserDao userDao;
	private UserMapper userMapper; 
	
//	@Autowired
//	private FavMoviesDao favMoviesDao;

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

//	@Override
//	@Transactional
//	public Set<FavMovie> getUserFavMovies(String id) 
//	{
//		Page<FavMovieEntity> pageFavMovies = favMovieRepository.findByUser(userRepository.findOne(id), new PageRequest(0, 20));
//
//		return pageFavMovies.getContent() != null? pageFavMovies.getContent().stream().map(x->favMovieMapper.entityToDto(x)).collect(Collectors.toSet()) : new HashSet();
//	}

//	/**
//	 * Servicio que compara dos listas de peliculas
//	 * @param id1: Id de la primera lista a intersectar
//	 * @param id2: Id de la segunda lista a intersectar
//	 * @return: Devuelve aquellas peliculas que estén en ambas
//	 */
//	public List<Movie> getListIntersection(String id1, String id2) {
//		FavMovie favMovieOne = favMoviesDao.getFavMovie(id1);
//		FavMovie favMovieTwo = favMoviesDao.getFavMovie(id2);
//		return favMovieOne.getMovies().stream().filter(favMovieTwo.getMovies()::contains).collect(Collectors.toList());
//	}
	
//	public FavMovie createNewFavMovieList(String title, User user) {
//		FavMovie favMovie = new FavMovie();
//		favMovie.setName(title);
//		favMovie.setUserId(user.getId());
//		FavMovie createdFavMovie = favMoviesDao.saveFavMovie(favMovie);
//		return createdFavMovie;
//	}

//	@Override
//	public void getFavActors(String userId,int page, PagedResponse resp) {
//		if(userId==null){
//			resp.setTotalResults(0L);
//			return;
//		}
//		Page<FavActorEntity> favActorsPage = userDao.getFavActors(userId,page-1);
//		if(favActorsPage==null){
//			resp.setTotalResults(0L);
//			return;
//		}
//		resp.setData(favActorsPage.getContent().stream().map(FavActorEntity::getActorId).collect(Collectors.toList()));
//		resp.setPage(page);
//		resp.setTotalPages(favActorsPage.getTotalPages());
//		resp.setTotalResults(favActorsPage.getTotalElements());
//		return ;
//	}
//
//	@Override
//	public void addFavActor(String idUser, String idActor) {
//		userDao.addFavActor(idUser, idActor);		
//	}
//
//	@Override
//	public void deleteFavActor(String idUser, String idActor) {
//		userDao.deleteFavActor(idUser, idActor);
//	}
//
//	@Override
//	public List<String> getFavActorsId(String userId, int page) {
//		Page<FavActorEntity> favActorsPage = userDao.getFavActors(userId,page-1);
//		
//		List<String> ids = favActorsPage.getContent().stream().map(FavActorEntity::getActorId).collect(Collectors.toList());
//		return ids;
//	}

}
