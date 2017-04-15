package ar.com.tacs.grupo5.frba.utn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ar.com.tacs.grupo5.frba.utn.dao.UserDao;
import ar.com.tacs.grupo5.frba.utn.models.FavMovie;
import ar.com.tacs.grupo5.frba.utn.models.User;

@Component
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;

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
//		RestTemplate restTemplate = new RestTemplate();
//		String requestUrl = "https://api.themoviedb.org/3/movie/550?api_key=bd9625fb42f6141aac0823a396d406ba&language=en-US"; 
//		ResponseEntity<String> response = restTemplate.exchange(requestUrl, HttpMethod.GET, null, String.class);
//		System.out.println(response.getBody());
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
	public List<FavMovie> getUserFavMovies(String id) 
	{
		return getUserById(id).getFavMovies();
	}

	@Override
	public List<String> getFavActors(String userId) {
		return userDao.getFavActors(userId);
	}

	@Override
	public void addFavActor(String idUser, String idActor) {
		userDao.addFavActor(idUser, idActor);		
	}

	@Override
	public void deleteFavActor(String idUser, String idActor) {
		userDao.deleteFavActor(idUser, idActor);
	}

}
