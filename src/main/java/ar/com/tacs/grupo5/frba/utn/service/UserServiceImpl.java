package ar.com.tacs.grupo5.frba.utn.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.tacs.grupo5.frba.utn.dao.FavMoviesDao;
import ar.com.tacs.grupo5.frba.utn.dao.UserDao;
import ar.com.tacs.grupo5.frba.utn.models.FavMovie;
import ar.com.tacs.grupo5.frba.utn.models.Movie;
import ar.com.tacs.grupo5.frba.utn.models.User;

@Component
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private FavMoviesDao favMoviesDao;

	@Autowired
	public UserServiceImpl(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public User getUserById(String id) {
		return userDao.getUserById(id);
	}

	@Override
	public User saveUser(User user) {
		return userDao.saveUser(user);
	}

	@Override
	public User findByUserNameAndPass(String userName, String pass) {
		return userDao.findByUserNameAndPass(userName, pass);
	}

	@Override
	public User findByUserName(String userName) {
		return userDao.findByUserName(userName);
	}

	@Override
	public Set<FavMovie> getUserFavMovies(String id) 
	{
		User user = getUserById(id);
		Set<FavMovie> favMovies = user.getFavMovies();
		return favMovies != null? favMovies : new HashSet();
	}

	/**
	 * Servicio que compara dos listas de peliculas
	 * @param id1: Id de la primera lista a intersectar
	 * @param id2: Id de la segunda lista a intersectar
	 * @return: Devuelve aquellas peliculas que estén en ambas
	 */
	public List<Movie> getListIntersection(String id1, String id2) {
		FavMovie favMovieOne = favMoviesDao.getFavMovie(id1);
		FavMovie favMovieTwo = favMoviesDao.getFavMovie(id2);
		return favMovieOne.getMovies().stream().filter(favMovieTwo.getMovies()::contains).collect(Collectors.toList());
	}

	public FavMovie createNewFavMovieList(String title, User user) {
		FavMovie favMovie = new FavMovie();
		favMovie.setName(title);
		favMovie.setUserId(user.getId());
		FavMovie createdFavMovie = favMoviesDao.saveFavMovie(favMovie);
		user.getFavMovies().add(createdFavMovie);
		userDao.saveUser(user);
		return createdFavMovie;
	}

}
